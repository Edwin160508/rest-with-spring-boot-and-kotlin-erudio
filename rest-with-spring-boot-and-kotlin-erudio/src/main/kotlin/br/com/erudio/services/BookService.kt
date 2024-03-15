package br.com.erudio.services

import br.com.erudio.controllers.BookController
import br.com.erudio.data.vo.v1.BookVO
import br.com.erudio.exceptions.RequiredObjectIsNullException
import br.com.erudio.exceptions.ResourceNotFoundException
import br.com.erudio.mapper.DozerMapper
import br.com.erudio.model.Book
import br.com.erudio.repositories.BookRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class BookService {

    @Autowired
    private lateinit var repository: BookRepository

    private val logger = Logger.getLogger(BookService::class.java.name)

    fun findAll():List<BookVO>{
        logger.info("Finding all books.")
        val books: MutableList<Book> = repository.findAll();
        val vos = DozerMapper.parseListObjects(books, BookVO::class.java)
        for(book in vos){
            addSelfRefHateoas(book)
        }
        return vos
    }
    fun findById(id:Long): BookVO {
        logger.info("Finding a book.by id ${id}.")
        val book = repository.findById(id).orElseThrow{ ResourceNotFoundException("No records found for this ID!") }

        val bookVO: BookVO = DozerMapper.parseObject(book, BookVO::class.java)

        addSelfRefHateoas(bookVO)
        return bookVO
    }

    fun create(book: BookVO?): BookVO {
        if (book == null) throw RequiredObjectIsNullException()
        logger.info("Creating one person with name ${book.title}!")
        var entity: Book = DozerMapper.parseObject(book, Book::class.java)
        val bookVO: BookVO = DozerMapper.parseObject(repository.save(entity), BookVO::class.java)
        addSelfRefHateoas(bookVO)
        return bookVO

    }

    fun update(book: BookVO?): BookVO {

        if (book == null) throw RequiredObjectIsNullException()
        logger.info("Update a person, by ID ${book.key}.")
        val entity = repository.findById(book.key).orElseThrow{ ResourceNotFoundException("No records found for this ID!") }
        //Criar logica para update passando por validacoes
        entity.title = book.title
        entity.author = book.author
        entity.price = book.price
        entity.launchDate = book.launchDate

        val bookVO: BookVO = DozerMapper.parseObject(repository.save(entity), BookVO::class.java)
        addSelfRefHateoas(bookVO)
        return bookVO
    }

    fun delete(id: Long){
        logger.info("Delete a person by $id.")
        val entity = repository.findById(id).orElseThrow{ ResourceNotFoundException("No records found for this ID!") }
        repository.delete(entity)
    }

    private fun addSelfRefHateoas(bookVO: BookVO){
        val withSelfRef = WebMvcLinkBuilder.linkTo(BookController::class.java).slash(bookVO.key)
            .withSelfRel()
        bookVO.add(withSelfRef)
    }
}