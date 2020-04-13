package sample.kotlin.spring.user

import org.springframework.data.jpa.domain.AbstractPersistable
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotEmpty


@Entity
class ApplicationUser (
    @NotEmpty
    var username: String,
    @NotEmpty
    var password: String
    ) : AbstractPersistable<Long>()