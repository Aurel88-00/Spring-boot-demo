package com.example.springdemo.author.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import com.example.springdemo.author.model.Author;

public interface AuthorRepository {

	List<Author> findAll();

	Optional<Author> findById(UUID id);

	Author save(Author author);

	void deleteById(UUID id);
}

