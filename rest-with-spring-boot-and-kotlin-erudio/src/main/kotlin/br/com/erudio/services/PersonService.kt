package br.com.erudio.services

import br.com.erudio.exceptions.ResourceNotFoundException
import br.com.erudio.model.Person
import br.com.erudio.repositories.PersonRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class PersonService {
    private val logger = Logger.getLogger(PersonService::class.java.name)

    @Autowired
    private lateinit var repository: PersonRepository

    fun findAll():List<Person>{
        logger.info("Finding all persons.")
        val persons: MutableList<Person> = repository.findAll();
        return persons
    }
    fun findById(id:Long): Person{
        logger.info("Finding a person.by id ${id}.")

        return repository.findById(id).orElseThrow{ResourceNotFoundException("No records found for this ID!")}
    }

    fun create(person: Person): Person{
        logger.info("Create one person with name ${person.firstName}.")
        //Criar logica para salvar passando por validacoes

        return repository.save(person)
    }

    fun update(person: Person): Person{
        logger.info("Update a person, by ID ${person.id}.")
        val entity = repository.findById(person.id).orElseThrow{ResourceNotFoundException("No records found for this ID!")}
        //Criar logica para update passando por validacoes
        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return repository.save(entity)
    }

    fun delete(id: Long){
        logger.info("Delete a person by $id.")
        val entity = repository.findById(id).orElseThrow{ResourceNotFoundException("No records found for this ID!")}
        //Chamar delete por id
        repository.delete(entity)
    }

}