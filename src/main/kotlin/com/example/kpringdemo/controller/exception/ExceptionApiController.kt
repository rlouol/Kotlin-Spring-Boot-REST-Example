package com.example.kpringdemo.controller.exception

import com.example.kpringdemo.model.http.ErrorResponse
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.annotation.Validated
import javax.validation.ConstraintViolationException
import javax.validation.constraints.Min
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size
import com.example.kpringdemo.model.http.Error
import com.example.kpringdemo.model.http.UserRequest
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.*
import java.time.LocalDateTime
import javax.servlet.http.HttpServletRequest
import javax.validation.Valid

// 전역적으로 적용
@RestController
@RequestMapping("/api/exception")
@Validated
class ExceptionApiController {

  @GetMapping("/error")
  fun hello() {
    throw RuntimeException("강제 RE 발생!")
  }

  @GetMapping("/hello")
  fun indexError(): String {
    val list = mutableListOf<String>()
    // val temp = list[0]

    return "hello"
  }

  @GetMapping("")
  fun get(
    @NotBlank
    @Size(min = 2, max = 6)
    @RequestParam name: String,

    @Min(10)
    @RequestParam age: Int
  ): String {
    println(name)
    println(age)

    return "$name $age"
  }

  @PostMapping("")
  fun post(@Valid @RequestBody userRequest: UserRequest): UserRequest {
    println(userRequest)
    return userRequest
  }

  // 현재 선언된 클래스 내부에서만 적용
  @ExceptionHandler(value = [IndexOutOfBoundsException::class])
  fun indexOutOfBoundException(e: IndexOutOfBoundsException): ResponseEntity<String> {
    println("controller exception handler")
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error!")
  }

  @ExceptionHandler(value = [ConstraintViolationException::class])
  fun constraintViolationException(e: ConstraintViolationException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
    // 1. 에러 분석
    val errors = mutableListOf<Error>()

    e.constraintViolations.forEach {
      val error = Error().apply {
        this.field = it.propertyPath.last().name
        this.message = it.message
        this.value = it.invalidValue
      }

      errors.add(error)
    }

    // 2. ErrorResponse
    val errorResponse = getErrorResponse(errors, request)

    // 3. ResponseEntity
    return ResponseEntity.status(400).body(errorResponse)
  }

  @ExceptionHandler(value = [MethodArgumentNotValidException::class])
  fun methodArgumentNotValidException(e: MethodArgumentNotValidException, request: HttpServletRequest): ResponseEntity<ErrorResponse> {
    // 1. 에러 분석
    val errors = mutableListOf<Error>()

    e.bindingResult.allErrors.forEach {
      val error = Error().apply {
        this.field = (it as FieldError).field
        this.message = it.defaultMessage
        this.value = it.rejectedValue
      }

      errors.add(error)
    }

    // 2. ErrorResponse
    val errorResponse = getErrorResponse(errors, request)

    // 3. ResponseEntity
    return ResponseEntity.status(400).body(errorResponse)
  }

  private fun getErrorResponse(errors: MutableList<Error>, request: HttpServletRequest): ErrorResponse {
    return ErrorResponse().apply {
      this.resultCode = "FAIL"
      this.httpStatus = HttpStatus.BAD_REQUEST.value().toString()
      this.httpMethod = request.method
      this.message = "요청에 에러가 발생하였습니다!"
      this.path = request.requestURI.toString()
      this.timestamp = LocalDateTime.now()
      this.errors = errors
    }
  }

}