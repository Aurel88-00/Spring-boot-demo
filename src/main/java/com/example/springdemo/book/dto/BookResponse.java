package com.example.springdemo.book.dto;
import java.util.UUID;
import java.time.LocalDate;
import com.example.springdemo.book.model.enums.Genres;

public record  BookResponse (
    UUID id,
    String title,
    String author,
    Genres genre,
    LocalDate publishedDate,
    String language,
    String price
) {
    
}
