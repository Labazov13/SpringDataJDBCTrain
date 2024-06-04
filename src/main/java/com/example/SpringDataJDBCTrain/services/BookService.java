package com.example.SpringDataJDBCTrain.services;

import com.example.SpringDataJDBCTrain.entities.Book;
import com.example.SpringDataJDBCTrain.exceptions.BookNotFoundException;
import com.example.SpringDataJDBCTrain.repository.BookRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }


    public Book getBook(Long bookId){
        return bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found"));
    }

    public Iterable<Book> getLibrary(){
        return bookRepository.findAll();
    }

    public Book addBook(Book book){
        return bookRepository.save(book);
    }

    public Book editBook(Long bookId, Book requestBook){
        Book book = bookRepository.findById(bookId).orElseThrow(() -> new BookNotFoundException("Book not found"));
        book.setTitle(requestBook.getTitle());
        book.setAuthor(requestBook.getAuthor());
        book.setPublicationYear(book.getPublicationYear());
        return bookRepository.save(book);
    }
}
