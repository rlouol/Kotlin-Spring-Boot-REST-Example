package com.example.kpringdemo.model.http

import com.example.kpringdemo.annotation.StringFormatDateTime
import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.validation.constraints.AssertTrue
import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.NotEmpty
import javax.validation.constraints.Pattern
import javax.validation.constraints.PositiveOrZero
import javax.validation.constraints.Size

// PropertyNamingStrategy.SnakeCaseStrategy deprecated
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserRequest(
  @field:NotEmpty
  @field:Size(min = 2, max = 8)
  var name: String? = null,

  @field:PositiveOrZero // 0이상 양수 검증
  var age: Int? = null,

  @field:Email // Email 형식 검증
  var email: String? = null,

  @field:NotBlank // 공백을 검증
  var address: String? = null,

  @field:Pattern(regexp = "^\\d{2,3}-\\d{3,4}-\\d{4}\$") // 핸드폰 번호 패턴 검증
  var phoneNumber: String? = null,  // phone_number, @JsonProperty 로 매핑하는 방법이 있음.

  @StringFormatDateTime(pattern = "yyyy-MM-dd HH:mm:ss", message = "패턴이 올바르지 않습니다.")
  var createdAt: String? = null // created_at => yyyy-MM-dd HH:mm:ss  ex) 2022-10-12 13:00:00
) {

//  @AssertTrue(message = "생성일자는 yyyy-MM-dd HH:mm:ss 형식이어야 합니다! ")
//  private fun isValidCreatedAt(): Boolean {
//    return try {
//      LocalDate.parse(this.createdAt, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
//      true
//    } catch(e: Exception) {
//      false
//    }
//  }
}