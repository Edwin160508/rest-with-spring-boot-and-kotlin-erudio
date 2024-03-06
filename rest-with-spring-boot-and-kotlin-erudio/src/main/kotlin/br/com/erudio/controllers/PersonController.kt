package br.com.erudio.controllers

import br.com.erudio.data.vo.v1.PersonVO
import br.com.erudio.services.PersonService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import br.com.erudio.utils.MediaType
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
@RequestMapping("/api/person/v1")
class PersonController {

    @Autowired
    private lateinit var service: PersonService
    @GetMapping(
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    fun findAllPersons(): ResponseEntity<List<PersonVO>> {
        return ResponseEntity.ok(service.findAll())
    }

    @GetMapping(value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    fun findPerson(@PathVariable(value = "id") id:Long): ResponseEntity<PersonVO> {

        return ResponseEntity.ok(service.findById(id))
    }

    @PostMapping(
        consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    fun createPerson(@RequestBody person: PersonVO): ResponseEntity<PersonVO> {

        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(person))
    }
    @PutMapping(
        consumes = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    fun updatePerson(@RequestBody person: PersonVO): ResponseEntity<PersonVO> {

        return ResponseEntity.ok(service.update(person))
    }

    @DeleteMapping(value = ["/{id}"],
        produces = [MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML, MediaType.APPLICATION_YAML])
    fun deletePerson(@PathVariable(value = "id") id:Long): ResponseEntity<*> {

        service.delete(id)
        return ResponseEntity.noContent().build<Any>()
    }
}