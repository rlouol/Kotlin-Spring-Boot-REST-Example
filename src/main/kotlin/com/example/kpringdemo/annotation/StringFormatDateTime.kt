package com.example.kpringdemo.annotation

import com.example.kpringdemo.validator.StringFormatDateTimeValidator
import javax.validation.Constraint
import javax.validation.Payload
import kotlin.reflect.KClass

@Constraint(validatedBy = [StringFormatDateTimeValidator::class])
@Target(
  AnnotationTarget.FIELD,
  AnnotationTarget.PROPERTY_GETTER,
  AnnotationTarget.PROPERTY_SETTER
)
@Retention(AnnotationRetention.RUNTIME)
@MustBeDocumented
annotation class StringFormatDateTime(
  val pattern: String = "yyyy-MM-dd HH:mm:ss",
  val message: String = "시간형식이 유효하지 않습니다",

  // default Annotaion 설정
  val groups: Array<KClass<*>> = [],
  val payload: Array<KClass<out Payload >> = []
)