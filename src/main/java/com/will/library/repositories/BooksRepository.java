package com.will.library.repositories;

import com.will.library.models.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
    List<Book> findBookByTitleStartingWith(String bookName);

    List<Book> findAll(Sort sort);

    Page<Book> findAll(Pageable pageable);
}
