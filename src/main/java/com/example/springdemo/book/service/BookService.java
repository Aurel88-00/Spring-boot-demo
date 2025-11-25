package com.example.springdemo.book.service;
import java.util.List;
import java.util.UUID;

import com.example.springdemo.book.dto.BookRequest;
import com.example.springdemo.book.model.Book;

public interface BookService {
	List<Book> getAllBooks();

	Book getBookById(UUID id);

	Book createBook(BookRequest request);

	Book updateBook(UUID id, BookRequest request);

	void deleteBook(UUID id);
}
