package sample.kotlin.spring.user

import org.springframework.data.jpa.domain.AbstractPersistable
import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
class ApplicationUser (
    @NotEmpty
    @Column(unique=true)
    val username: String,
    @NotEmpty
    var password: String,
    @NotEmpty
    @Column(unique=true)
    val email: String,
    @NotEmpty
    val name: String,
    @NotEmpty
    val surname: String
    ) : AbstractPersistable<Long>()