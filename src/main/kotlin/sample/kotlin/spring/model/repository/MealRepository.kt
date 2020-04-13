package sample.kotlin.spring.model.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import sample.kotlin.spring.model.entities.Meal
import java.util.*

@Repository
interface MealRepository : CrudRepository<Meal, Long>{
    fun findByName(name: String) : List<Meal>
}