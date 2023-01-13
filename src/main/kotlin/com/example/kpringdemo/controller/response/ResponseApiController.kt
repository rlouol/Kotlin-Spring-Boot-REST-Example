package com.example.kpringdemo.controller.response

import com.example.kpringdemo.model.http.UserRequest
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/response")
class ResponseApiController {

  // 1. get 4xx
  // GET http://localhost:8080/api/response?age=10
  @GetMapping
  fun getMapping(@RequestParam age: Int?): ResponseEntity<String> {
//    // 1. age == null -> 400 error
//    if(age == null) {
//      return ResponseEntity.status(400).body("age 값이 누락되었습니다.")
//    }
//
//    // 2. age > 20 -> 400 error
//    if(age < 20) {
//      return ResponseEntity.status(400).body("age 값은 20이상 이어야 합니다.")
//    }
//    println(age)
//    return ResponseEntity.ok("OK")

    age?.let {
      // age is not null
      if(it < 20) {
        return ResponseEntity.status(400).body("age 값은 20이상 이어야 합니다.")
      }

      return ResponseEntity.ok("OK")
    }?: kotlin.run {
      return ResponseEntity.status(400).body("age 값이 누락되었습니다.")
    }
  }

  // 2. post 200
  @PostMapping
  fun postMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<Any> {
    return ResponseEntity.status(200).body(userRequest) // object mapper -> object -> json
  }

  // 3. put 201
  @PutMapping
  fun putMapping(@RequestBody userRequest: UserRequest?): ResponseEntity<UserRequest> {
    // 1. 기존 데이터가 없어서 새로 생성했다.
    return ResponseEntity.status(HttpStatus.CREATED).body(userRequest)
  }

  // 4. delete 500
  @DeleteMapping("/{id}")
  fun deleteMapping(@PathVariable id: Int): ResponseEntity<Any> {
    return ResponseEntity.status(500).body(null)
  }
}