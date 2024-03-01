package com.will.library.services;

import com.will.library.models.Book;
import com.will.library.models.Person;
import com.will.library.repositories.PeopleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class PeopleService {
    private final PeopleRepository peopleRepository;

    @Autowired
    public PeopleService(PeopleRepository peopleRepository) {
        this.peopleRepository = peopleRepository;
    }

    public List<Person> findAll() {
        return peopleRepository.findAll();
    }

    public Person findById(int id) {
        return peopleRepository.findById(id).orElse(null);
    }

    @Transactional
    public void update(Person updatedPerson) {
        peopleRepository.save(updatedPerson);
    }

    @Transactional
    public void save(Person person) {
        peopleRepository.save(person);
    }

    @Transactional
    public void delete(int id) {
        List<Book> booksOfPerson = findBooksOfPerson(id);
        if (booksOfPerson != null) {
            for (Book book : booksOfPerson) {
                book.setTakenAt(null);
            }
        }
        peopleRepository.deleteById(id);
    }

    public List<Book> findBooksOfPerson(int personId) {
        List<Book> books = findById(personId).getBooks();
        for (Book book : books) {
            LocalDateTime takenAt = book.getTakenAt();
            LocalDateTime now = LocalDateTime.now();
            long diff = ChronoUnit.DAYS.between(takenAt, now);
            if (diff > 2) {
                book.setExpired(true);
            }
        }
        return books;
    }
}
