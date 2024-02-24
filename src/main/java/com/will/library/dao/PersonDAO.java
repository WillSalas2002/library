package com.will.library.dao;

import com.will.library.models.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public PersonDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Person> findAll() {
        return jdbcTemplate.query("SELECT * FROM Person", new PersonMapper());
    }

    public Person findById(int id) {
        return jdbcTemplate.query("SELECT * FROM Person WHERE id = ?",
                new Object[]{id}, new PersonMapper()).stream().findAny().orElse(null);
    }

    public void update(Person updatedPerson) {
        jdbcTemplate.update("UPDATE Person SET fullName = ?, yearOfBirth = ? WHERE id = ?",
                updatedPerson.getFullName(), updatedPerson.getYearOfBirth(), updatedPerson.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Person WHERE id = ?", id);
    }

    public void save(Person person) {
        jdbcTemplate.update("INSERT INTO Person(fullName, yearOfBirth) VALUES (?, ?)",
                person.getFullName(), person.getYearOfBirth());
    }
}
