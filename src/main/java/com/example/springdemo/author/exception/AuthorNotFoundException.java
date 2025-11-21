package com.example.springdemo.author.exception;

import java.util.UUID;

public class AuthorNotFoundException extends RuntimeException {

	public AuthorNotFoundException(UUID id) {
		super("Author %s not found".formatted(id));
	}
}

