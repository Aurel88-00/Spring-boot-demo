package com.example.springdemo.domains.author.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/v1/authors")
public class AuthorController {

    public AuthorController() {
        System.out.println("AuthorController constructor");
    }
    
    @GetMapping()
    public String getAllAuthors() {
        return "Hello World";
    }

    @GetMapping("/{id}")
    public String getAuthor(@PathVariable String id) {
        return "Hello World";
    }

    @PostMapping()
    public String createAuthor(@RequestBody Object author) {
        return "Hello World";
    }
    @PutMapping("/{id}")
    public String updateAuthor(@PathVariable String id, @RequestBody Object author) {
        return "Hello World";
    }
    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable String id) {
        return "Hello World";
    }
    
}
