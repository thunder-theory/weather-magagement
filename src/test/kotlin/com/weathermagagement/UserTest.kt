package com.weathermagagement

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.weathermagagement.domain.repository.UserRepository
import com.weathermagagement.dto.LoginRequestDto
import com.weathermagagement.dto.SignupRequest
import com.weathermagagement.security.service.AuthService
import com.weathermagagement.service.UserService
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.mock.mockito.MockBean
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.web.WebAppConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.post
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.status
import java.net.http.HttpResponse

@SpringBootTest
@DataJpaTest
@AutoConfigureMockMvc
@WebAppConfiguration
@ContextConfiguration
class UserTest(

) {
    @Autowired
    @MockBean
    lateinit var userService: UserService

    @Autowired
    @MockBean
    lateinit var userRepository: UserRepository
    @Autowired
    @MockBean
    lateinit var authService: AuthService
    @Autowired
    @MockBean
    lateinit var mockMvc: MockMvc

    @AfterEach
    fun `유저를 모두 삭제한다`() {
        userRepository.deleteAll()
    }


    @Test
    fun `로그인을 하면 token을 발급한다`() {
        val signupRequest = SignupRequest(
            username = "test",
            password = "test",
            place = "chungju"
        )
        val loginRequestDto = LoginRequestDto(
            username = "test",
            password = "test"
        )
        if (mockMvc == null) {
            println("null입니다.")
        }

        mockMvc.perform(MockMvcRequestBuilders.get("/test")
            .accept(MediaType.APPLICATION_JSON))
//            .andDo(::println)
            .andExpect(status().isOk)

//        mockMvc.post("/signup") {
//            contentType = MediaType.APPLICATION_JSON
//            content = ObjectMapper().writeValueAsString(signupRequest)
//        }
//            .andDo { print() }
//            .andExpect { status { is2xxSuccessful() } }



        userService.signUp(signupRequest)

//        mockMvc.post("/login") {
//            contentType = MediaType.APPLICATION_JSON
//            content = ObjectMapper().writeValueAsString(loginRequestDto)
//        }
//            .andDo { print() }
//            .andExpect { status { is2xxSuccessful() } }

//        mockMvc.perform(MockMvcRequestBuilders.post("/login")
//            .accept(MediaType.APPLICATION_JSON)
//            .content(ObjectMapper().writeValueAsString(loginRequestDto))
//
//        ).andDo(::print)
//            .andExpect(status().isOk)
    }




}