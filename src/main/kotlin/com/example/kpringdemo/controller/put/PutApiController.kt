package com.example.kpringdemo.controller.put

import com.example.kpringdemo.model.http.Result
import com.example.kpringdemo.model.http.UserRequest
import com.example.kpringdemo.model.http.UserResponse
import org.springframework.http.ResponseEntity
import org.springframework.validation.BindingResult
import org.springframework.validation.FieldError
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.client.HttpServerErrorException.InternalServerError
import java.lang.StringBuilder
import javax.validation.Valid

@RestController
@RequestMapping("/api")
class PutApiController {

  @PutMapping("/put-mapping")
  fun putMapping(): String {
    return "put-mapping"
  }

  @RequestMapping(method = [RequestMethod.PUT], path = ["/request-mapping"])
  fun requestMapping(): String {
    return "request-mapping - put method"
  }

  // @Valid 는 해당 빈에 대해서만 검증한다.
  @PutMapping(path = ["put-mapping/object"])
  fun putMappingObject(@Valid @RequestBody userRequest: UserRequest, bindingResult: BindingResult): ResponseEntity<UserResponse> {

    val msg = StringBuilder()

    // Validation 결과를 BindingResult를 통해 가져올 수 있다.
    if(bindingResult.hasErrors()) {
      // 400 error
      for (error in bindingResult.allErrors) {
        val fieldError = error as FieldError
        val fieldMessage = error.defaultMessage
        msg.appendLine("${fieldError.field} : $fieldMessage")
      }

      return ResponseEntity.badRequest().body(UserResponse().apply {
        this.result = Result().apply {
          this.resultCode = "400"
          this.resultMessage = msg.toString()
        }
      })
    }

    // 0. Response
    return ResponseEntity.ok(UserResponse().apply {

      // 1. result
      this.result = Result().apply {
        this.resultCode = "OK"
        this.resultMessage = "성공"
      }
    }.apply {

      // 2. description
      this.description = "~~~~~~~~~"
    }.apply {

      // 3. user mutable list
      val userList = mutableListOf(
        UserRequest().apply {
          this.name = "a"
          this.age = 10
          this.email = "a@gmail.com"
          this.address = "a address"
          this.phoneNumber = "010-1234-4321"
        },
        UserRequest().apply {
          this.name = "b"
          this.age = 20
          this.email = "a@gmail.com"
          this.address = "b address"
          this.phoneNumber = "010-1234-1234"
        },
        userRequest
      )

      this.user = userList
    })
  }
}