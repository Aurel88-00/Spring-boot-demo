package com.example.springdemo.book.repository;
import java.util.UUID;
import java.util.List;
import com.example.springdemo.book.model.Book;

public interface BookRepository  {
    Book save(Book book);
    Book findById(UUID id);
    List<Book> findAll();
    void deleteById(UUID id);
}