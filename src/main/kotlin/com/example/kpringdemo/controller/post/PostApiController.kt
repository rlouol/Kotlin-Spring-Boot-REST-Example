package com.example.kpringdemo.controller.post

import com.example.kpringdemo.model.http.UserRequest
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class PostApiController {

  // 기본형
  @PostMapping("/post-mapping")
  fun postMapping(): String {
    return "post-mapping"
  }

  // 클래식
  @RequestMapping(method=[RequestMethod.POST], path = ["/request-mapping"])
  fun requestMapping(): String {
    return "request-mapping"
  }

  // RequestBody 포함
  // object mapper( json <-> object 변환)
  @PostMapping("/post-mapping/object")
  fun postMappingObject(@RequestBody userRequest: UserRequest): UserRequest {
    // json -> object
    println(userRequest)

    // object -> json
    return userRequest
  }

}