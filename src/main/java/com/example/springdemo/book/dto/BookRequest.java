package com.example.springdemo.book.dto;

import java.time.LocalDate;
import java.util.UUID;

import com.example.springdemo.book.model.enums.Genres;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public record BookRequest(
		@NotBlank
		@Size(min = 2, max = 255, message = "Title must be between 2 and 255 characters")
		String title,

		@NotNull
		Genres genre,

		@PastOrPresent(message = "Published date must be in the past or present")
		LocalDate publishedDate,

		@NotBlank
		@Size(min = 2, max = 100, message = "Language must be between 2 and 100 characters")
		String language,

		@NotBlank
		@Size(min = 1, max = 100, message = "Price must be between 1 and 100 characters")
		String price,

		@NotNull
		UUID authorId
) {
}
