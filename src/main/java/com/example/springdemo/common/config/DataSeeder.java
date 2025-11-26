package com.example.springdemo.common.config;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.springdemo.author.model.Author;
import com.example.springdemo.author.repository.AuthorRepository;
import com.example.springdemo.book.model.Book;
import com.example.springdemo.book.model.enums.Genres;
import com.example.springdemo.book.repository.BookRepository;

@Component
public class DataSeeder implements CommandLineRunner {

	private static final Logger log = LoggerFactory.getLogger(DataSeeder.class);

	private final AuthorRepository authorRepository;
	private final BookRepository bookRepository;

	public DataSeeder(AuthorRepository authorRepository, BookRepository bookRepository) {
		this.authorRepository = authorRepository;
		this.bookRepository = bookRepository;
	}

	@Override
	@Transactional
	public void run(String... args) {
		// Avoid reseeding if data already exists
		if (authorRepository.count() > 0 || bookRepository.count() > 0) {
			log.info("Skipping data seeding; authors or books already present.");
			return;
		}

		log.info("Seeding initial authors and books...");

		var rowling = createAuthor("J.K.", "Rowling", LocalDate.of(1965, 7, 31));
		var orwell = createAuthor("George", "Orwell", LocalDate.of(1903, 6, 25));

		authorRepository.saveAll(List.of(rowling, orwell));

		var hp1 = createBook("Harry Potter and the Philosopher's Stone", Genres.FANTASY,
				LocalDate.of(1997, 6, 26), "English", "19.99", rowling);
		var hp2 = createBook("Harry Potter and the Chamber of Secrets", Genres.FANTASY,
				LocalDate.of(1998, 7, 2), "English", "19.99", rowling);
		var b1984 = createBook("1984", Genres.SCIENCE_FICTION,
				LocalDate.of(1949, 6, 8), "English", "14.99", orwell);

		bookRepository.saveAll(List.of(hp1, hp2, b1984));

		// Simple relationship checks
		var loadedRowling = authorRepository.findById(rowling.getId()).orElseThrow();
		log.info("Author '{}' {} has {} books",
				loadedRowling.getFirstName(),
				loadedRowling.getLastName(),
				loadedRowling.getBooks().size());

		var anyBook = bookRepository.findAll().stream().findFirst().orElseThrow();
		log.info("Book '{}' is written by '{} {}'",
				anyBook.getTitle(),
				anyBook.getAuthor().getFirstName(),
				anyBook.getAuthor().getLastName());

		log.info("Data seeding completed: {} authors, {} books",
				authorRepository.count(), bookRepository.count());
	}

	private Author createAuthor(String firstName, String lastName, LocalDate birthDate) {
		var author = new Author();
		author.setFirstName(firstName);
		author.setLastName(lastName);
		author.setBirthDate(birthDate);
		return author;
	}

	private Book createBook(String title, Genres genre, LocalDate publishedDate,
			String language, String price, Author author) {
		var book = new Book();
		book.setTitle(title);
		book.setGenre(genre);
		book.setPublishedDate(publishedDate);
		book.setLanguage(language);
		book.setPrice(price);
		book.setAuthor(author);
		return book;
	}
}


