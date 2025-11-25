package com.example.springdemo.book.model;
import java.time.LocalDate;
import java.util.UUID;

import com.example.springdemo.book.model.enums.Genres;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class Book {
    private final UUID id;
    private final String title;
    private final String author;
    private final Genres genre;
    private final LocalDate publishedDate;
    private final String language;
    private final String price;
}
