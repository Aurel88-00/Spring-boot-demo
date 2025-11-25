package com.example.springdemo.book.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springdemo.book.dto.BookRequest;
import com.example.springdemo.book.dto.BookResponse;
import com.example.springdemo.book.model.Book;
import com.example.springdemo.book.service.BookService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/books")
@Validated
public class BookController {

	private final BookService bookService;

	public BookController(BookService bookService) {
		this.bookService = bookService;
	}

	@GetMapping
	public List<BookResponse> getAllBooks() {
		return bookService.getAllBooks()
				.stream()
				.map(this::mapToResponse)
				.toList();
	}

	@GetMapping("/{id}")
	public BookResponse getBookById(@PathVariable UUID id) {
		return mapToResponse(bookService.getBookById(id));
	}

	@PostMapping
	public ResponseEntity<BookResponse> createBook(@RequestBody @Valid BookRequest request) {
		var created = bookService.createBook(request);
		return ResponseEntity.ok(mapToResponse(created));
	}

	@PutMapping("/{id}")
	public BookResponse updateBook(@PathVariable UUID id, @RequestBody @Valid BookRequest request) {
		return mapToResponse(bookService.updateBook(id, request));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBook(@PathVariable UUID id) {
		bookService.deleteBook(id);
		return ResponseEntity.noContent().build();
	}

	private BookResponse mapToResponse(Book book) {
		var author = book.getAuthor();
		var authorName = author != null ? author.getFirstName() + " " + author.getLastName() : null;
		var authorId = author != null ? author.getId() : null;
		return new BookResponse(
				book.getId(),
				book.getTitle(),
				authorId,
				authorName,
				book.getGenre(),
				book.getPublishedDate(),
				book.getLanguage(),
				book.getPrice()
		);
	}
}
