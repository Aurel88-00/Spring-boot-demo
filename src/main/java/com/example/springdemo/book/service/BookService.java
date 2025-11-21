package com.example.springdemo.book.service;
import java.util.UUID;
import java.util.List;
import com.example.springdemo.book.model.Book;


public interface BookService {
   List<Book> getAllBooks();
   Book getBookById(UUID id);
   Book createBook(Book book);
   Book updateBook(UUID id, Book book);
   void deleteBook(UUID id);
}
