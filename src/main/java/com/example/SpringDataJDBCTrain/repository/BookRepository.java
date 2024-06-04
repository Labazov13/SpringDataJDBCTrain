package com.example.SpringDataJDBCTrain.repository;

import com.example.SpringDataJDBCTrain.entities.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> {

}
