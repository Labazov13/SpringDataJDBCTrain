package com.example.SpringDataJDBCTrain.repository;

import com.example.SpringDataJDBCTrain.entities.Book;
import com.example.SpringDataJDBCTrain.exceptions.BookNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class BookDAO {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Book getBook(Long bookId) {
        return jdbcTemplate.query("SELECT * FROM books_table WHERE id=?",
                        new Object[]{bookId}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny()
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
    }

    public Book getBook(String title) {
        return jdbcTemplate.query("SELECT * FROM books_table WHERE title=?",
                        new Object[]{title}, new BeanPropertyRowMapper<>(Book.class)).stream().findAny()
                .orElseThrow(() -> new BookNotFoundException("Book not found"));
    }

    public List<Book> getLibrary() {
        return jdbcTemplate.query("SELECT * FROM books_table", new BeanPropertyRowMapper<>(Book.class));
    }

    public Book addBook(Book book) {
        jdbcTemplate.update("INSERT INTO books_table(title, author, publication_year) VALUES(?, ?, ?)",
                book.getTitle(), book.getAuthor(),
                book.getPublicationYear());
        return getBook(book.getTitle());
    }

    public Book editBook(Long bookId, Book book) {
        jdbcTemplate.update("UPDATE books_table SET title=?, author=?, publication_year=? WHERE id=?", book.getTitle(),
                book.getAuthor(), book.getPublicationYear(), bookId);
        return getBook(bookId);
    }

    public String deleteBook(Long bookId) {
        jdbcTemplate.update("DELETE FROM books_table WHERE id=?", bookId);
        return "Success!";
    }
}
