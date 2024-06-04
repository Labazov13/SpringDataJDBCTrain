package com.example.SpringDataJDBCTrain.entities;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

@Data
@Table(name = "books_table")
@NoArgsConstructor
@AllArgsConstructor
public class Book {
    @Id
    private Long id;
    @NotEmpty(message = "Title cannot be empty")
    private String title;
    @NotEmpty(message = "Title cannot be empty")
    private String author;
    @Min(value = 868, message = "The first book was written in 868. You cannot specify a year below this")
    private Integer publicationYear;

    public Book(String title, String author, Integer publicationYear) {
        this.title = title;
        this.author = author;
        this.publicationYear = publicationYear;
    }
}
