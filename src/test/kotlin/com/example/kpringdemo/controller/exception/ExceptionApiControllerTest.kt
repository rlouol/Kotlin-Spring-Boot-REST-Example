package com.example.kpringdemo.controller.exception

import com.example.kpringdemo.model.http.UserRequest
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post
import org.springframework.test.web.servlet.result.MockMvcResultHandlers.print
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.util.LinkedMultiValueMap

@WebMvcTest
@AutoConfigureMockMvc
class ExceptionApiControllerTest {

  @Autowired
  lateinit var mockMvc: MockMvc

  @Test
  fun shouldGetHello() {
    mockMvc.perform(get("/api/exception/hello"))
      .andExpect(status().isOk)
      .andExpect(content().string("hello"))
      .andDo(print())
  }

  @Test
  fun testGet() {
    val queryParams = LinkedMultiValueMap<String, String>().apply {
      this.add("name", "박규민")
      this.add("age", "20")
    }

    mockMvc.perform(get("/api/exception")
        .queryParams(queryParams))
      .andExpect(status().isOk)
      .andExpect(content().string("박규민 20"))
      .andDo(print())
  }

  @Test
  fun testGetFailed() {
    val queryParams = LinkedMultiValueMap<String, String>().apply {
      this.add("name", "박규민")
      this.add("age", "5")
    }

    mockMvc.perform(get("/api/exception")
      .queryParams(queryParams))
      .andExpect(status().isBadRequest)
      .andExpect(content().contentType("application/json"))
      .andExpect(jsonPath("\$.result_code").value("FAIL"))
      .andExpect(jsonPath("\$.errors[0].field").value("age"))
      .andExpect(jsonPath("\$.errors[0].value").value("5"))
      .andDo(print())
  }

  @Test
  fun testPost() {
    val userRequest = UserRequest().apply {
      this.name = "박규민"
      this.age = 10
      this.phoneNumber = "010-1234-4321"
      this.address = "경기도 군포시"
      this.email = "111@gmail.com"
      this.createdAt = "2023-01-16 13:00:00"
    }

    val json = jacksonObjectMapper().writeValueAsString(userRequest)

    mockMvc.perform(post("/api/exception")
        .accept(MediaType.APPLICATION_JSON)
        .contentType("application/json")
        .content(json))
      .andExpect(status().isOk)
      .andExpect(jsonPath("\$.name").value("박규민"))
      .andExpect(jsonPath("\$.age").value("10"))
      .andExpect(jsonPath("\$.email").value("111@gmail.com"))
      .andExpect(jsonPath("\$.address").value("경기도 군포시"))
      .andExpect(jsonPath("\$.phone_number").value("010-1234-4321"))
      .andExpect(jsonPath("\$.created_at").value("2023-01-16 13:00:00"))
      .andDo(print())
  }

  @Test
  fun testPostFailed() {
    val userRequest = UserRequest().apply {
      this.name = "박규민"
      this.age = -1
      this.phoneNumber = "010-1234-4321"
      this.address = "경기도 군포시"
      this.email = "111@gmail.com"
      this.createdAt = "2023-01-16 13:00:00"
    }

    val json = jacksonObjectMapper().writeValueAsString(userRequest)

    mockMvc.perform(post("/api/exception")
      .accept(MediaType.APPLICATION_JSON)
      .contentType("application/json")
      .content(json))
      .andExpect(status().isBadRequest)
      .andExpect(jsonPath("\$.result_code").value("FAIL"))
      .andExpect(jsonPath("\$.http_status").value("400"))
      .andDo(print())
  }
}