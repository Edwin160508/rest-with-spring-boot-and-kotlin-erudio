package br.com.erudio.repositories

import br.com.erudio.model.User
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import org.springframework.data.repository.query.Param

@Repository
interface UserRepository : JpaRepository<User?, Long?>{

    @Query("SELECT u FROM User u WHERE u.userName =:userName")
    fun findByUsername(@Param("userName") userName: String?): User?
}