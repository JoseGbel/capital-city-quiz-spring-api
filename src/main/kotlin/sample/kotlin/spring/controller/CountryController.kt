package sample.kotlin.spring.controller

import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import org.springframework.web.server.ResponseStatusException
import sample.kotlin.spring.model.entities.Country
import sample.kotlin.spring.model.repository.CountryRepository
import java.util.*
import javax.validation.Valid


@RestController
class CountryController(private val countryRepository: CountryRepository) {

    @GetMapping(value = ["/countries"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCountries() : Iterable<Country> {
        return countryRepository.findAll()
    }

    @GetMapping(value = ["/byContinent"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCountries(@RequestParam(value="continent")  continent : String) : Iterable<Country>? {
        return countryRepository.findByContinent(continent)
                .orElseThrow {
                    ResponseStatusException(HttpStatus.NOT_FOUND,
                            String.format("Invalid continent %s", continent))

                }
    }

    @GetMapping(value = ["/country/{id}"], produces = [MediaType.APPLICATION_JSON_VALUE])
    fun getCountry(@PathVariable id : Long) : Country {
        return countryRepository.findById(id).orElseThrow { ResponseStatusException(HttpStatus.NOT_FOUND,
                String.format("Invalid country id %s", id))
        }
    }



    @PostMapping(value = ["/country"], consumes = [MediaType.APPLICATION_JSON_VALUE],
            produces = [MediaType.APPLICATION_JSON_VALUE])
    fun createCountry(@Valid @RequestBody country: Country): Country {
        return countryRepository.save(country)
    }
}