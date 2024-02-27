package com.will.library.repositories;

import com.will.library.models.Book;
import com.will.library.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    @Modifying
    @Query("UPDATE Book b SET b.owner = null WHERE b.id = :bookId")
    @Transactional
    void returnBook(@Param("bookId") int bookId);

    @Modifying
    @Query("UPDATE Book b SET b.owner = :person WHERE b.id = :bookId")
    @Transactional
    void assignBook(@Param("bookId") int bookId, @Param("person") Person person);
}
