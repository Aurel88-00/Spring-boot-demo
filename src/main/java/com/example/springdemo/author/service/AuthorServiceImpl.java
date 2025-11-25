package com.example.springdemo.author.service;

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
		return authorRepository.save(authorRequest);
	}

	@Override
	public Author updateAuthor(UUID id, Author authorRequest) {
		Author existing = getAuthor(id);
		existing.setFirstName(authorRequest.getFirstName());
		existing.setLastName(authorRequest.getLastName());
		existing.setBirthDate(authorRequest.getBirthDate());
		return authorRepository.save(existing);
	}

	@Override
	public void deleteAuthor(UUID id) {
		if (authorRepository.findById(id).isEmpty()) {
			throw new AuthorNotFoundException(id);
		}
		authorRepository.deleteById(id);
	}
}

