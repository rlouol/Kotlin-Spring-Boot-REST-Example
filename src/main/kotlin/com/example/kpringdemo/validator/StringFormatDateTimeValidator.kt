package com.example.kpringdemo.validator

import com.example.kpringdemo.annotation.StringFormatDateTime
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import javax.validation.ConstraintValidator
import javax.validation.ConstraintValidatorContext

/**
 * Validation을 사용하는 이유
 *
 * 1. 변수가 많아질 수록 유효성 검증하는 코드가 너무 길어지고 어디서 검증되었는지 찾기가 힘듦
 * 2. Service 로직에 방해가 되어 놓치게 되는 경우가 있음.
 * 3. 검증 로직이 변경되는 경우, 테스트 코드 등의 전체 로직이 흔들릴 수 있다.
 */
class StringFormatDateTimeValidator : ConstraintValidator<StringFormatDateTime, String> {

  private var pattern: String? = null

  // init으로 써도 될려나...
  override fun initialize(constraintAnnotation: StringFormatDateTime?) {
    this.pattern = constraintAnnotation?.pattern

    super.initialize(constraintAnnotation)
  }

  override fun isValid(value: String?, context: ConstraintValidatorContext?): Boolean {
    return try {
      LocalDate.parse(value, DateTimeFormatter.ofPattern(pattern))
      true
    } catch(e: Exception) {
      false
    }
  }

}