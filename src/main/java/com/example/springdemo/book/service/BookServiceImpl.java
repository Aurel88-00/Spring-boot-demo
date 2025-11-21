package com.example.springdemo.book.service;
import org.springframework.stereotype.Service;
import com.example.springdemo.book.repository.BookRepository;
import com.example.springdemo.book.model.Book;
import java.util.UUID;
import java.util.List;
import com.example.springdemo.book.exception.BookNotFoundException;

@Service
public class BookServiceImpl implements BookService {
    private final BookRepository bookRepository;

    public BookServiceImpl(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override	
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }
    @Override
    public Book getBookById(UUID id) {
        Book book = bookRepository.findById(id);
        if (book == null) {
            throw new BookNotFoundException(id);
        }
        return book;
    }
    @Override
    public Book createBook(Book book) {
        return bookRepository.save(book);
    }
    @Override
    public Book updateBook(UUID id, Book book) {
        return bookRepository.save(book);
    }
    @Override
    public void deleteBook(UUID id) {
        bookRepository.deleteById(id);
    }
}
