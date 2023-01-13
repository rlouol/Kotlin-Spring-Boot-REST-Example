package com.example.kpringdemo.controller.put

import com.example.kpringdemo.model.http.Result
import com.example.kpringdemo.model.http.UserRequest
import com.example.kpringdemo.model.http.UserResponse
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

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

  @PutMapping(path = ["put-mapping/object"])
  fun putMappingObject(@RequestBody userRequest: UserRequest): UserResponse {
    // 0. Response
    return UserResponse().apply {

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
    }
  }
}