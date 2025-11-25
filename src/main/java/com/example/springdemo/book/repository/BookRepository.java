package com.example.springdemo.book.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springdemo.book.model.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, UUID> {
}