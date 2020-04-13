package sample.kotlin.spring.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import sample.kotlin.spring.model.entities.Meal
import sample.kotlin.spring.model.repository.MealRepository
import javax.validation.Valid


@RestController
class MealController(private val mealRepository: MealRepository) {

    @GetMapping(value = ["/meal"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getMeals() : Iterable<Meal> {
        return mealRepository.findAll()
    }

    @GetMapping(value = ["/meal/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getMeal(@PathVariable id : Long) : Meal {
        return mealRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Invalid meal id %s", id))
        }
    }

    @PostMapping(value = ["/meal"], consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createMeal(@Valid @RequestBody meal: Meal) : Meal {
        return mealRepository.save(meal)
    }
}