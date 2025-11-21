package com.example.springdemo.author.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.example.springdemo.author.model.Author;

@Repository
public class InMemoryAuthorRepository implements AuthorRepository {

	private final Map<UUID, Author> storage = new ConcurrentHashMap<>();

	@Override
	public List<Author> findAll() {
		return new ArrayList<>(storage.values());
	}

	@Override
	public Optional<Author> findById(UUID id) {
		return Optional.ofNullable(storage.get(id));
	}

	@Override
	public Author save(Author author) {
		storage.put(author.getId(), author);
		return author;
	}

	@Override
	public void deleteById(UUID id) {
		storage.remove(id);
	}
}

