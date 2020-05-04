package sample.kotlin.spring.user

import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIT {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Test
    fun signUp() {
        val jsonUser = """{
                        "username": "Test",
                        "password": "123",
                        "email": "test@test.com",
                        "name": "my",
                        "surname": "test"
                        }"""

        mockMvc.perform(MockMvcRequestBuilders.post("/users/sign-up")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jsonUser)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andReturn()
    }

    @Test
    fun shouldReturnCorrectJSONIfEmailAlreadyExistAndUsernameDoesNotExist() {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/users/verify?email=hannahlhughes94@gmail.com&username=nonExistentUsername")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.usernameInDatabase").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.emailInDatabase").value(true))
                .andReturn()
    }

    @Test
    fun shouldReturnCorrectJSONIfEmailAlreadyExistAndUsernameAlreadyExist() {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/users/verify?email=hannahlhughes94@gmail.com&username=Hannah")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.emailInDatabase").value(true))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usernameInDatabase").value(true))
                .andReturn()
    }

    @Test
    fun shouldReturnCorrectJSONIfEmailDoesNotExistAndUsernameDoesNotExist() {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/users/verify?email=nonExistent@email.com&username=nonExistentUsername")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.emailInDatabase").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usernameInDatabase").value(false))
                .andReturn()
    }

    @Test
    fun shouldReturnCorrectJSONIfEmailDoesNotExistAndUsernameExist() {
        mockMvc.perform(MockMvcRequestBuilders
                .get("/users/verify?email=nonExistent@email.com&username=Hannah")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(MockMvcResultMatchers.jsonPath("$.emailInDatabase").value(false))
                .andExpect(MockMvcResultMatchers.jsonPath("$.usernameInDatabase").value(true))
                .andReturn()
    }
}