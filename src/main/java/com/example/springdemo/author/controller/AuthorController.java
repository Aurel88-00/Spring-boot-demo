package com.example.springdemo.author.controller;

import java.net.URI;
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

import com.example.springdemo.author.dto.AuthorRequest;
import com.example.springdemo.author.dto.AuthorResponse;
import com.example.springdemo.author.model.Author;
import com.example.springdemo.author.service.AuthorService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/v1/authors")
@Validated
public class AuthorController {

	private final AuthorService authorService;

	public AuthorController(AuthorService authorService) {
		this.authorService = authorService;
	}

	@GetMapping
	public List<AuthorResponse> getAuthors() {
		return authorService.getAuthors()
				.stream()
				.map(author -> new AuthorResponse(author.getId(), author.getFirstName(), author.getLastName(), author.getBirthDate()))
				.toList();
	}

	@GetMapping("/{id}")
	public AuthorResponse getAuthor(@PathVariable UUID id) {
		var author = authorService.getAuthor(id);
		return new AuthorResponse(author.getId(), author.getFirstName(), author.getLastName(), author.getBirthDate());
	}

	@PostMapping
	public ResponseEntity<AuthorResponse> createAuthor(@RequestBody @Valid AuthorRequest request) {
		var author = authorService.createAuthor(mapToEntity(request));
		var response = new AuthorResponse(author.getId(), author.getFirstName(), author.getLastName(), author.getBirthDate());
		return ResponseEntity
				.created(URI.create("/api/v1/authors/" + response.id()))
				.body(response);
	}

	@PutMapping("/{id}")
	public AuthorResponse updateAuthor(@PathVariable UUID id, @RequestBody @Valid AuthorRequest request) {
		var author = authorService.updateAuthor(id, mapToEntity(request));
		return new AuthorResponse(author.getId(), author.getFirstName(), author.getLastName(), author.getBirthDate());
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteAuthor(@PathVariable UUID id) {
		authorService.deleteAuthor(id);
		return ResponseEntity.noContent().build();
	}

	private Author mapToEntity(AuthorRequest request) {
		var author = new Author();
		author.setFirstName(request.firstName());
		author.setLastName(request.lastName());
		author.setBirthDate(request.birthDate());
		return author;
	}
}

