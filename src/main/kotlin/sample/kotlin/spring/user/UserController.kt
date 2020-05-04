package sample.kotlin.spring.user

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.web.bind.annotation.*
import java.lang.IllegalArgumentException

@RestController
@RequestMapping("/users")
class UserController(private val applicationUserRepository: ApplicationUserRepository,
                     private val userDetailsServiceImpl: UserDetailsServiceImpl,
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

    @GetMapping("/verify")
    fun verifyUserAlreadyExist(@RequestParam(value = "username") username: String,
                               @RequestParam(value = "email") email: String) : ResponseEntity<UserExistenceResponse> {
        val usernameResponse : ApplicationUser? = applicationUserRepository.findByUsername(username)
        val usernameInDatabase = usernameResponse != null

        val emailResponse : ApplicationUser? = applicationUserRepository.findByEmail(email)
        val emailInDatabase = emailResponse != null

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(UserExistenceResponse(usernameInDatabase, emailInDatabase))
    }
}