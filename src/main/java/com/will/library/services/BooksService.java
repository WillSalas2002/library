package com.will.library.services;

import com.will.library.models.Book;
import com.will.library.models.Person;
import com.will.library.repositories.BooksRepository;
import com.will.library.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;
    private final PeopleRepository peopleRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository, PeopleRepository peopleRepository) {
        this.booksRepository = booksRepository;
        this.peopleRepository = peopleRepository;
    }

    public List<Book> findAll() {
        return booksRepository.findAll();
    }
    public List<Book> findAll(String sort) {
        return booksRepository.findAll(Sort.by(sort));
    }
    public List<Book> findAll(int page, int itemsPerPage) {
        return booksRepository.findAll(PageRequest.of(page, itemsPerPage)).getContent();
    }
    public List<Book> findAll(int page, int itemsPerPage, String sort) {
        return booksRepository.findAll(PageRequest.of(page, itemsPerPage, Sort.by(sort))).getContent();
    }

    public Book findById(int id) {
        return booksRepository.findById(id).orElse(null);
    }

    @Transactional
    public void update(Book updatedBook) {
        updatedBook.setOwner(findOwnerOfBook(updatedBook.getId()));
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
        Book book = findById(bookId);
        book.setOwner(null);
    }

    @Transactional
    public void assignBook(int bookId, int personId) {
        Person person = new Person();
        person.setId(personId);
        Book book = findById(bookId);
        book.setOwner(person);
        booksRepository.save(book);
    }

    public List<Book> search(String bookName) {
        return booksRepository.findBookByTitleStartingWith(bookName);
    }

    public List<Person> findAllPeople() {
        return peopleRepository.findAll();
    }
}
