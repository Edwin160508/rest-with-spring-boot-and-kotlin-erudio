package br.com.erudio.services

import br.com.erudio.data.vo.v1.PersonVO
import br.com.erudio.exceptions.ResourceNotFoundException
import br.com.erudio.mapper.DozerMapper
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

    fun findAll():List<PersonVO>{
        logger.info("Finding all persons.")
        val persons: MutableList<Person> = repository.findAll();
        return DozerMapper.parseListObjects(persons, PersonVO::class.java)
    }
    fun findById(id:Long): PersonVO{
        logger.info("Finding a person.by id ${id}.")
        val person = repository.findById(id).orElseThrow{ResourceNotFoundException("No records found for this ID!")}

        return DozerMapper.parseObject(person, PersonVO::class.java)
    }

    fun create(person: PersonVO): PersonVO{
        logger.info("Create one person with name ${person.firstName}.")
        //Criar logica para salvar passando por validacoes
        val entity = DozerMapper.parseObject(person, Person::class.java)

        return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun update(person: PersonVO): PersonVO{
        logger.info("Update a person, by ID ${person.id}.")
        val entity = repository.findById(person.id).orElseThrow{ResourceNotFoundException("No records found for this ID!")}
        //Criar logica para update passando por validacoes
        entity.firstName = person.firstName
        entity.lastName = person.lastName
        entity.address = person.address
        entity.gender = person.gender

        return DozerMapper.parseObject(repository.save(entity), PersonVO::class.java)
    }

    fun delete(id: Long){
        logger.info("Delete a person by $id.")
        val entity = repository.findById(id).orElseThrow{ResourceNotFoundException("No records found for this ID!")}
        repository.delete(entity)
    }

}