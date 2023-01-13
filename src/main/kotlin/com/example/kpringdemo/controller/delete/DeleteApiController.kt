package com.example.kpringdemo.controller.delete

import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class DeleteApiController {

  // 1. request param
  @DeleteMapping("/delete-mapping")
  fun deleteMapping(
    @RequestParam(value = "name") _name: String,
    @RequestParam(name = "age") _age: Int,
  ): String {
    println(_name)
    println(_age)

    return "$_name $_age"
  }

  // 2. path variable
  @DeleteMapping("/delete-mapping/name/{name}/age/{age}")
  fun deleteMappingPath(
    @PathVariable(value = "name") _name: String,
    @PathVariable(name = "age") _age: Int,
  ): String {
    println(_name)
    println(_age)

    return "$_name $_age"
  }
}