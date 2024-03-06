package br.com.erudio.services

import br.com.erudio.controllers.PersonController
import br.com.erudio.data.vo.v1.PersonVO
import br.com.erudio.exceptions.ResourceNotFoundException
import br.com.erudio.mapper.DozerMapper
import br.com.erudio.model.Person
import br.com.erudio.repositories.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {
    private val logger = Logger.getLogger(PersonService::class.java.name)

    @Autowired
    private lateinit var repository: PersonRepository

    fun findAll():List<PersonVO>{
        logger.info("Finding all persons.")
        val persons: MutableList<Person> = repository.findAll();
        val vos = DozerMapper.parseListObjects(persons, PersonVO::class.java)
        for(person in vos){
            addSelfRefHateoas(person)
        }
        return vos
    }
    fun findById(id:Long): PersonVO{
        logger.info("Finding a person.by id ${id}.")
        val person = repository.findById(id).orElseThrow{ResourceNotFoundException("No records found for this ID!")}

        val personVO: PersonVO = DozerMapper.parseObject(person, PersonVO::class.java)

        addSelfRefHateoas(personVO)
        return personVO
    }

    fun create(person: PersonVO): PersonVO{
        logger.info("Create one person with name ${person.firstName}.")
        //Criar logica para salvar passando por validacoes
        val entity = DozerMapper.parseObject(person, Person::class.java)

        val personVO: PersonVO = DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
        addSelfRefHateoas(personVO)
        return personVO
    }

    fun update(person: PersonVO): PersonVO{
        logger.info("Update a person, by ID ${person.key}.")
        val entity = repository.findById(person.key).orElseThrow{ResourceNotFoundException("No records found for this ID!")}
        //Criar logica para update passando por validacoes
        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        val personVO: PersonVO = DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
        addSelfRefHateoas(personVO)
        return personVO
    }

    fun delete(id: Long){
        logger.info("Delete a person by $id.")
        val entity = repository.findById(id).orElseThrow{ResourceNotFoundException("No records found for this ID!")}
        repository.delete(entity)
    }

    private fun addSelfRefHateoas(personVO: PersonVO){
        val withSelfRef = linkTo(PersonController::class.java).slash(personVO.key)
            .withSelfRel()
        personVO.add(withSelfRef)
    }
}