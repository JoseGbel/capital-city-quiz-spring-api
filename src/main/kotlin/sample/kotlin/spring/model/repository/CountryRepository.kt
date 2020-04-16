package sample.kotlin.spring.model.repository

import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository
import sample.kotlin.spring.model.entities.Country
import java.util.*

@Repository
interface CountryRepository : CrudRepository<Country, Long>{
    fun findByName(name: String) : Country
    fun findByContinent(continent: String) : Optional<List<Country>>
}