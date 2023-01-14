package com.example.kpringdemo.controller.delete

import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.Min
import javax.validation.constraints.NotNull
import javax.validation.constraints.Size

@RestController
@RequestMapping("/api")
@Validated // 클래스에서 하위 메소드에 있는 Validation 어노테이션(NotNull, Size, Min. ..) 동작 활성화
class DeleteApiController {

  // 1. request param
  @DeleteMapping("/delete-mapping")
  fun deleteMapping(
    @RequestParam(value = "name") _name: String,

    @NotNull(message = "age가 null이 아니어야 합니다!")
    @Min(value = 20, message = "age가 20 이상이어야 합니다!")
    @RequestParam(name = "age") _age: Int,
  ): String {
    println(_name)
    println(_age)

    return "$_name $_age"
  }

  // 2. path variable
  @DeleteMapping("/delete-mapping/name/{name}/age/{age}")
  fun deleteMappingPath(
    @NotNull
    @Size(min = 2, max = 5, message = "name은 2~5글자여야 합니다!")  // 최소 2글자에서 5글자까지
    @PathVariable(value = "name") _name: String,

    @NotNull(message = "age가 null이 아니어야 합니다!")
    @Min(value = 20, message = "age가 20 이상이어야 합니다!")
    @PathVariable(name = "age") _age: Int,
  ): String {
    println(_name)
    println(_age)

    return "$_name $_age"
  }
}