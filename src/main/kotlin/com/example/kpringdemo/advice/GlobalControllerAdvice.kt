package com.example.kpringdemo.advice

import com.example.kpringdemo.controller.exception.ExceptionApiController
import com.example.kpringdemo.controller.put.PutApiController
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

// @ControllerAdvice : MVC
@RestControllerAdvice(basePackageClasses = [PutApiController::class])
class GlobalControllerAdvice {

  @ExceptionHandler(value = [RuntimeException::class])
  fun exception(e: Exception): String {
    return "Server Error"
  }

  @ExceptionHandler(value = [IndexOutOfBoundsException::class])
  fun indexOutOfBoundException(e: IndexOutOfBoundsException): ResponseEntity<String> {
    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Index Error!")
  }
}