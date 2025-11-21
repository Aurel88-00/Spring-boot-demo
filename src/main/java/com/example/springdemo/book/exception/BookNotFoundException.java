package com.example.springdemo.book.exception;
import java.util.UUID;

public class BookNotFoundException extends RuntimeException {
    public BookNotFoundException(UUID id) {
        super("Book %s not found".formatted(id));
    }
}