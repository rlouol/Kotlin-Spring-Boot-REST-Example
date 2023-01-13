package com.example.kpringdemo.controller.get

import com.example.kpringdemo.model.http.UserRequest
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController         // REST API Controller 동작
@RequestMapping("/api") // http://localhost:8080/api
class GetApiController {

  // 형식 1
  @GetMapping(path= ["/hello", "/abcd"]) // GET http://localhost:8080/api/hello
  fun hello(): String {
    return "hello kotlin!"
  }

  // 형식 2
  @RequestMapping(method = [RequestMethod.GET], path = ["/request-mapping"])
  fun requestMapping(): String {
    return "request-mapping"
  }

  @GetMapping("/get-mapping/path-variable/{name}/{age}") // GET http://localhost:8080/api/get-mapping/path-variable/gyumipark/28
  fun pathVariable(@PathVariable name: String, @PathVariable age: Int): String {
    println("$name $age")
    return "$name $age"
  }

  @GetMapping("/get-mapping/path-variable2/{name}/{age}") // GET http://localhost:8080/api/get-mapping/path-variable/gyumipark/28
  fun pathVariable2(@PathVariable(value = "name") _name: String, @PathVariable(name = "age") age: Int): String {
    val name = "kotlin"

    println("$_name $age")
    return "$_name $age"
  }

  // http://localhost:8080/api/page?key=value&key2=value2
  @GetMapping("/get-mapping/query-param") // ?name=gyumin&age=28
  fun queryParam(
    @RequestParam(name="name") name: String,
    @RequestParam(value="age") age: Int,
  ): String {
    println("$name $age")
    return "$name $age"
  }

  // name, age, address, email
  // -
  // phoneNumber -> phonenumber, phone-number
  @GetMapping("/get-mapping/query-param/object")
  fun queryParamObject(userRequest: UserRequest): UserRequest {
    println(userRequest)
    return userRequest
  }


  @GetMapping("/get-mapping/query-param/map")
  fun queryParamMap(@RequestParam map: Map<String, Any>): Any? {
    println(map["phone-number"])
    return map["phone-number"]
  }
}