package sample.kotlin.spring.user

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.lang.IllegalArgumentException

@RestController
@RequestMapping("/users")
class UserController(private val applicationUserRepository: ApplicationUserRepository,
                     private val bCryptPasswordEncoder: BCryptPasswordEncoder) {

    @PostMapping("/sign-up")
    fun signUp(@RequestBody user: ApplicationUser) : ResponseEntity<Boolean> {
        user.password = bCryptPasswordEncoder.encode(user.password)
        try{
            applicationUserRepository.save(user)
        } catch (e: IllegalArgumentException){
            return ResponseEntity.badRequest().body(false)
        }
        return ResponseEntity.status(HttpStatus.OK)
                .body(true)
    }
}