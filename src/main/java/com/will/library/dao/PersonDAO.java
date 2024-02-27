//package com.will.library.dao;
//
//import com.will.library.models.Book;
//import com.will.library.models.Person;
//import org.hibernate.Hibernate;
//import org.hibernate.Session;
//import org.hibernate.SessionFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.List;
//
//@Component
//@Transactional(readOnly = true)
//public class PersonDAO {
//    private final SessionFactory sessionFactory;
//
//    @Autowired
//    public PersonDAO(SessionFactory sessionFactory) {
//        this.sessionFactory = sessionFactory;
//    }
//
//    public List<Person> findAll() {
//        Session session = sessionFactory.getCurrentSession();
//        return session.createQuery("SELECT p FROM Person p", Person.class).getResultList();
//    }
//
//    public Person findById(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        return session.get(Person.class, id);
//    }
//
//    @Transactional
//    public void update(Person updatedPerson) {
//        Session session = sessionFactory.getCurrentSession();
//        Person person = session.get(Person.class, updatedPerson.getId());
//        person.setFullName(updatedPerson.getFullName());
//        updatedPerson.setYearOfBirth(updatedPerson.getYearOfBirth());
//    }
//
//    @Transactional
//    public void delete(int id) {
//        Session session = sessionFactory.getCurrentSession();
//        Person person = new Person();
//        person.setId(id);
//        session.remove(person);
//    }
//
//    @Transactional
//    public void save(Person person) {
//        Session session = sessionFactory.getCurrentSession();
//        session.persist(person);
//    }
//
//    public List<Book> findBooksOfPerson(int personId) {
//        // TODO: We can return a person with his books associated for some optimization
//        Session session = sessionFactory.getCurrentSession();
//        Person person = session.get(Person.class, personId);
//        List<Book> books = person.getBooks();
//        Hibernate.initialize(books);
//        return books;
//    }
//}
