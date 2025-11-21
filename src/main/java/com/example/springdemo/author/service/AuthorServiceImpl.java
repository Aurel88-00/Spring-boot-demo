package com.example.springdemo.author.service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.example.springdemo.author.exception.AuthorNotFoundException;
import com.example.springdemo.author.model.Author;
import com.example.springdemo.author.repository.AuthorRepository;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImpl implements AuthorService {

	private final AuthorRepository authorRepository;

	public AuthorServiceImpl(AuthorRepository authorRepository) {
		this.authorRepository = authorRepository;
	}

	@Override
	public List<Author> getAuthors() {
		return authorRepository.findAll();
	}

	@Override
	public Author getAuthor(UUID id) {
		return authorRepository.findById(id)
				.orElseThrow(() -> new AuthorNotFoundException(id));
	}

	@Override
	public Author createAuthor(Author authorRequest) {
		var author = Author.of(UUID.randomUUID(), authorRequest.getFirstName(), authorRequest.getLastName(), authorRequest.getBirthDate());
		return authorRepository.save(author);
	}

	@Override
	public Author updateAuthor(UUID id, Author authorRequest) {
		var existing = getAuthor(id);
		var updated = existing.update(authorRequest.getFirstName(), authorRequest.getLastName(), authorRequest.getBirthDate());
		return authorRepository.save(updated);
	}

	@Override
	public void deleteAuthor(UUID id) {
		if (authorRepository.findById(id).isEmpty()) {
			throw new AuthorNotFoundException(id);
		}
		authorRepository.deleteById(id);
	}
}

