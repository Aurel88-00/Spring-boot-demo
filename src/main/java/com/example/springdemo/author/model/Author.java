package com.example.springdemo.author.model;

import java.time.LocalDate;
import java.util.UUID;
import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
@NoArgsConstructor(force = true)
@Getter
@ToString
@EqualsAndHashCode(of = "id")
public class Author {

	private final UUID id;
	private final String firstName;
	private final String lastName;
	private final LocalDate birthDate;


	public static Author of(UUID id, String firstName, String lastName, LocalDate birthDate) {
		validate(id, firstName, lastName);
		return new Author(id, firstName, lastName, birthDate);
	}

	public Author update(String newFirstName, String newLastName, LocalDate newBirthDate) {
		return Author.of(
				this.id,
				newFirstName != null ? newFirstName : this.firstName,
				newLastName != null ? newLastName : this.lastName,
				newBirthDate != null ? newBirthDate : this.birthDate
		);
	}


	private static void validate(UUID id, String firstName, String lastName) {
		if (id == null) {
			throw new IllegalArgumentException("Author id must not be null");
		}
		if (firstName == null || firstName.isBlank()) {
			throw new IllegalArgumentException("Author first name must not be blank");
		}
		if (lastName == null || lastName.isBlank()) {
			throw new IllegalArgumentException("Author last name must not be blank");
		}
	}
}

