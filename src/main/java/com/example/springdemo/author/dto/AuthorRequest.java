package com.example.springdemo.author.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;

public record AuthorRequest(
		@NotBlank
		@Size(min = 2, max = 50, message = "First name must be between 2 and 50 characters")
		String firstName,
		
		@NotBlank
		@Size(min = 2, max = 50, message = "Last name must be between 2 and 50 characters")
		String lastName,
		
		@PastOrPresent(message = "Birth date must be in the past or present")
		LocalDate birthDate
) {
}

