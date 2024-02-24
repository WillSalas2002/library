package com.will.library.dao;

import com.will.library.models.Person;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PersonMapper implements RowMapper<Person> {
    @Override
    public Person mapRow(ResultSet rs, int rowNum) throws SQLException {
        Person person = new Person();
        person.setId(rs.getInt("id"));
        person.setFullName(rs.getString("fullName"));
        person.setYearOfBirth(rs.getInt("yearOfBirth"));
        return person;
    }
}
