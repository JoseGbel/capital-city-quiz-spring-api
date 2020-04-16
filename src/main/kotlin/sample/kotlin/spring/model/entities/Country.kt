package sample.kotlin.spring.model.entities

import javax.persistence.*
import javax.validation.constraints.NotEmpty

@Entity
class Country (@NotEmpty val name : String,
               @NotEmpty val capitalCity: String,
               @NotEmpty val continent: String) : AbstractJpaPersistable<Long>()