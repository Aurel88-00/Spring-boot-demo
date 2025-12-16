package com.example.springdemo.book.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springdemo.author.exception.AuthorNotFoundException;
import com.example.springdemo.author.model.Author;
import com.example.springdemo.author.repository.AuthorRepository;
import com.example.springdemo.book.dto.BookRequest;
import com.example.springdemo.book.exception.BookNotFoundException;
import com.example.springdemo.book.model.Book;
import com.example.springdemo.book.repository.BookRepository;

@Service
public class BookServiceImpl implements BookService {

	private final BookRepository bookRepository;
	private final AuthorRepository authorRepository;

	public BookServiceImpl(BookRepository bookRepository, AuthorRepository authorRepository) {
		this.bookRepository = bookRepository;
		this.authorRepository = authorRepository;
	}

	@Override
	@Transactional(readOnly = true)
	public List<Book> getAllBooks() {
		return bookRepository.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Book getBookById(UUID id) {
		return bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException(id));
	}

	@Override
	@Transactional
	public Book createBook(BookRequest request) {
		Author author = findAuthor(request.authorId());
		Book book = new Book();
		applyRequestToEntity(request, book, author);
		return bookRepository.save(book);
	}

	@Override
	@Transactional
	public Book updateBook(UUID id, BookRequest request) {
		Book book = bookRepository.findById(id)
				.orElseThrow(() -> new BookNotFoundException(id));
		Author author = findAuthor(request.authorId());
		applyRequestToEntity(request, book, author);
		return bookRepository.save(book);
	}

	@Override
	@Transactional
	public void deleteBook(UUID id) {
		if (!bookRepository.existsById(id)) {
			throw new BookNotFoundException(id);
		}
		bookRepository.deleteById(id);
	}

	private Author findAuthor(UUID authorId) {
		return authorRepository.findById(authorId)
				.orElseThrow(() -> new AuthorNotFoundException(authorId));
	}

	private void applyRequestToEntity(BookRequest request, Book book, Author author) {
		book.setTitle(request.title());
		book.setGenre(request.genre());
		book.setPublishedDate(request.publishedDate());
		book.setLanguage(request.language());
		book.setPrice(request.price());
		book.setAuthor(author);
	}
}
