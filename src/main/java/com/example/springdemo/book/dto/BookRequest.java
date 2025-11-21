package com.example.springdemo.book.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.PastOrPresent;
import java.time.LocalDate;
import java.util.UUID;
import com.example.springdemo.book.model.enums.Genres;

public record BookRequest (
  @NotNull
  UUID id,
  @NotBlank
  @Size(min = 2, max = 100, message = "Title must be between 2 and 100 characters")
  String title,
  @NotBlank
  @Size(min = 2, max = 100, message = "Author must be between 2 and 100 characters")
  String author,
  @NotBlank
  @Size(min = 2, max = 100, message = "Genre must be between 2 and 100 characters")
  Genres genre,
  @PastOrPresent(message = "Published date must be in the past or present")
  LocalDate publishedDate,
  @NotBlank
  @Size(min = 2, max = 100, message = "Language must be between 2 and 100 characters")
  String language,
  @NotBlank
  @Size(min = 2, max = 100, message = "Price must be between 2 and 100 characters")
  String price
) {
}
