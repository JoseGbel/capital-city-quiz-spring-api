package sample.kotlin.spring.user

import com.jayway.jsonpath.JsonPath
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.content

@RunWith(SpringRunner::class)
@SpringBootTest
@AutoConfigureMockMvc
class UserControllerIT {

    @Autowired
    lateinit var mockMvc: MockMvc

    @Before
    fun setUp(){

    }

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
    fun shouldReturnTrueIfEmailAlreadyExist() {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/verify?email=hannahlhughes94@gmail.com")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(content().string("true"))
                .andReturn()
    }

    @Test
    fun shouldReturnFalseIfEmailDoesNotExist() {
        mockMvc.perform(MockMvcRequestBuilders.get("/users/verify?email=available@email.com")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk)
                .andExpect(content().string("false"))
                .andReturn()
    }
}