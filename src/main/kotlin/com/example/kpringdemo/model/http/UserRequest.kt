package com.example.kpringdemo.model.http

import com.fasterxml.jackson.databind.PropertyNamingStrategies
import com.fasterxml.jackson.databind.annotation.JsonNaming

// PropertyNamingStrategy.SnakeCaseStrategy deprecated
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy::class)
data class UserRequest(
  var name: String? = null,
  var age: Int? = null,
  var email: String? = null,
  var address: String? = null,
  var phoneNumber: String? = null // camel case => phone_number, @JsonProperty 로 매핑하는 방법이 있음.
)