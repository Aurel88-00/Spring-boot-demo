package com.example.springdemo.book.dto;
import java.time.LocalDate;
import java.util.UUID;

import com.example.springdemo.book.model.enums.Genres;

public record BookResponse(
		UUID id,
		String title,
		UUID authorId,
		String authorName,
		Genres genre,
		LocalDate publishedDate,
		String language,
		String price
) {
}
