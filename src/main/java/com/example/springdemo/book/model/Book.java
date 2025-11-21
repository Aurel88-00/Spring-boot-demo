package com.example.springdemo.book.model;
import java.time.LocalDate;
import java.util.UUID;
import java.util.Objects;

import com.example.springdemo.book.model.enums.Genres;

public class Book {
    private final UUID id;
    private final String title;
    private final String author;
    private final Genres genre;
    private final LocalDate publishedDate;
    private final String language;
    private final String price;

    public Book(UUID id, String title, String author, Genres genre, LocalDate publishedDate, String language, String price) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.publishedDate = publishedDate;
        this.language = language;
        this.price = price;
    }

    public UUID getId() {
        return id;
    }
    public String getTitle() {
        return title;
    }
    public String getAuthor() {
        return author;
    }
    public Genres getGenre() {
        return genre;
    }
    public LocalDate getPublishedDate() {
        return publishedDate;
    }
    public String getLanguage() {
        return language;
    }
    public String getPrice() {
        return price;
    }

    // Check if the book is equal to another book by comparing the id across all instances
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return Objects.equals(id, book.id);
    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
    // Return a string representation of the book
    @Override
    public String toString() {
        return "Book{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                ", genre=" + genre +
                ", publishedDate=" + publishedDate +
                ", language='" + language + '\'' +
                ", price='" + price + '\'' +
                '}';
    }
}
