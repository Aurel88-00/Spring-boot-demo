package com.example.springdemo.author.model;

import java.time.LocalDate;
import java.util.UUID;


public class Author {

	private final UUID id;
	private final String firstName;
	private final String lastName;
	private final LocalDate birthDate;

	private Author(UUID id, String firstName, String lastName, LocalDate birthDate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.birthDate = birthDate;
	}

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

	public UUID getId() {
		return id;
	}

	public String getFirstName() {
		return firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (o == null || getClass() != o.getClass()) {
			return false;
		}
		Author author = (Author) o;
		return id.equals(author.id);
	}

	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public String toString() {
		return "Author{" +
				"id=" + id +
				", firstName='" + firstName + '\'' +
				", lastName='" + lastName + '\'' +
				", birthDate=" + birthDate +
				'}';
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

