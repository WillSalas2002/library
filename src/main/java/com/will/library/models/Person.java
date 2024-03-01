package com.will.library.models;

import jakarta.validation.constraints.*;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Table(name = "Person")
public class Person {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Name should not be empty")
    @Size(min = 2, message = "The name should be provided and  it should consist of 2 letters at least")
    @Column(name = "full_name")
    private String fullName;
    @Min(value = 1920, message = "Invalid year, the year should be between 1920 and 2020")
    @Max(value = 2020, message = "Invalid year, the year should be between 1920 and 2020")
    @Column(name = "year_of_birth")
    private int yearOfBirth;
    @OneToMany(mappedBy = "owner", fetch = FetchType.EAGER)
    private List<Book> books;

    public Person() {
    }

    public Person(String fullName, int yearOfBirth) {
        this(0, fullName, yearOfBirth);
    }

    public Person(int id, String fullName, int yearOfBirth) {
        this(id, fullName, yearOfBirth, null);
    }

    public Person(int id, String fullName, int yearOfBirth, List<Book> books) {
        this.id = id;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
        this.books = books;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }
}
