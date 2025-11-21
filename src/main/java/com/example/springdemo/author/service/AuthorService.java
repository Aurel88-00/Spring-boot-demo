package com.example.springdemo.author.service;

import java.util.List;
import java.util.UUID;

import com.example.springdemo.author.model.Author;

public interface AuthorService {

	List<Author> getAuthors();

	Author getAuthor(UUID id);

	Author createAuthor(Author author);

	Author updateAuthor(UUID id, Author author);

	void deleteAuthor(UUID id);
}

