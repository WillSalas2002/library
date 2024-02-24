package com.will.library.dao;

import com.will.library.models.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookDAO {
    private final JdbcTemplate jdbcTemplate;

    @Autowired
    public BookDAO(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Book> findAll() {
        return jdbcTemplate.query("SELECT * FROM Book", new BookMapper());
    }

    public Book findById(int id) {
        return jdbcTemplate.query("SELECT * FROM Book WHERE id = ?",
                new Object[]{id}, new BookMapper()).stream().findAny().orElse(null);
    }

    public void update(Book updatedBook) {
        jdbcTemplate.update("UPDATE Book SET title = ?, author = ?, year = ? WHERE id = ?",
                updatedBook.getTitle(), updatedBook.getAuthor(), updatedBook.getYear(), updatedBook.getId());
    }

    public void delete(int id) {
        jdbcTemplate.update("DELETE FROM Book WHERE id = ?", id);
    }

    public void save(Book book) {
        jdbcTemplate.update("INSERT INTO Book(title, author, year) VALUES (?, ?, ?)",
                book.getTitle(), book.getAuthor(), book.getYear());
    }
}
