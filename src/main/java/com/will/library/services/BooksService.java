package com.will.library.services;

import com.will.library.models.Book;
import com.will.library.models.Person;
import com.will.library.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }

    public Book findById(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void update(Book updatedBook) {
        booksRepository.save(updatedBook);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    public Person findOwnerOfBook(int bookId) {
        return booksRepository.findById(bookId).orElse(null).getOwner();
    }

    @Transactional
    public void returnBook(int bookId) {

    }

    @Transactional
    public void assignBook(int bookId, int personId) {

    }
}
