package br.com.erudio.services

import br.com.erudio.model.Person
import org.springframework.stereotype.Service
import java.util.ArrayList
import java.util.concurrent.atomic.AtomicLong
import java.util.logging.Logger

@Service
class PersonService {
    private val counter:AtomicLong = AtomicLong()
    private val logger = Logger.getLogger(PersonService::class.java.name)

    fun findAll():List<Person>{
        logger.info("Finding all persons.")
        val persons: MutableList<Person> = ArrayList<Person>()
        for (index in 0..7) {
            val person = mockPerson(index)
            persons.add(person)
        }
        return persons
    }
    fun findById(id:Long): Person{
        logger.info("Finding a person.")

        //mock
        val person = mockPerson(0)
        return person
    }

    fun create(person: Person): Person{
        logger.info("Create a person.")
        //Criar logica para salvar passando por validacoes
        person.id = counter.incrementAndGet()
        return person
    }

    fun update(person: Person): Person{
        logger.info("Update a person.")
        //Criar logica para update passando por validacoes
        return person
    }

    fun delete(id: Long){
        logger.info("Delete a person by $id.")
        //Chamar delete por id
    }
    private fun mockPerson(index: Int): Person{
        //mock
        val person = Person()
        person.id = counter.incrementAndGet()
        person.firstName = "Person Name $index"
        person.lastName = "Last Name $index"
        person.address = "Some Address in Brasil"
        person.gender = "Male"
        return person
    }


}