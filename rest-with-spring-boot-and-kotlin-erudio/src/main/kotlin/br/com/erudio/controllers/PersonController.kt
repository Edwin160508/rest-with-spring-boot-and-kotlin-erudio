package br.com.erudio.controllers

import br.com.erudio.model.Person
import br.com.erudio.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.DeleteMapping
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/person")
class PersonController {

    @Autowired
    private lateinit var service: PersonService
    @GetMapping(
        produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    fun findAllPersons(): ResponseEntity<List<Person>> {
        return ResponseEntity.ok(service.findAll())
    }

    @GetMapping(value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    fun findPerson(@PathVariable(value = "id") id:Long): ResponseEntity<Person> {

        return ResponseEntity.ok(service.findById(id))
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    fun createPerson(@RequestBody person: Person): ResponseEntity<Person> {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(person))
    }
    @PutMapping(
        consumes = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE],
        produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    fun updatePerson(@RequestBody person: Person): ResponseEntity<Person> {

        return ResponseEntity.ok(service.update(person))
    }

    @DeleteMapping(value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE])
    fun deletePerson(@PathVariable(value = "id") id:Long): ResponseEntity<*> {

        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }
}