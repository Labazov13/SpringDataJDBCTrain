package com.example.SpringDataJDBCTrain.controllers;

import com.example.SpringDataJDBCTrain.entities.Book;
import com.example.SpringDataJDBCTrain.repository.BookDAO;
import com.example.SpringDataJDBCTrain.services.BookService;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService bookService;
    private final BookDAO bookDAO;

    public BookController(BookService bookService, BookDAO bookDAO) {
        this.bookService = bookService;
        this.bookDAO = bookDAO;
    }
    @GetMapping(value = "/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> getBook(@PathVariable(name = "bookId") Long bookId){
        return ResponseEntity.ok(bookDAO.getBook(bookId));
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Iterable<Book>> getLibrary(){
        return ResponseEntity.ok(bookDAO.getLibrary());
    }

    @PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> addBook(@Valid @RequestBody Book book){
        return ResponseEntity.ok(bookDAO.addBook(book));
    }
    @PutMapping(value = "/edit/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Book> editBook(@PathVariable(name = "bookId") Long bookId, @Valid @RequestBody Book book){
        return ResponseEntity.ok(bookDAO.editBook(bookId, book));
    }
    @DeleteMapping(value = "/delete/{bookId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteBook(@PathVariable(name = "bookId") Long bookId){
        return ResponseEntity.ok(bookDAO.deleteBook(bookId));
    }
}
