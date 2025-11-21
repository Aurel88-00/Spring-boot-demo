package com.example.springdemo.book.repository;
import org.springframework.stereotype.Repository;
import java.util.UUID;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import com.example.springdemo.book.model.Book;
import java.util.List;
import java.util.ArrayList;

@Repository
public class InMemoryBookRepository implements BookRepository {
    private final Map<UUID, Book> storage = new ConcurrentHashMap<>();

    @Override
    public Book save(Book book) {
        storage.put(book.getId(), book);
        return book;
    }
    @Override
    public Book findById(UUID id) {
        return storage.get(id);
    }
    @Override
    public List<Book> findAll() {
        return new ArrayList<>(storage.values());
    }
    @Override
    public void deleteById(UUID id) {
        storage.remove(id);
    }
}
