package com.library.controller;
import com.library.model.Book; import com.library.repository.BookRepository; import java.util.List; import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/books") public class BookController { private final BookRepository books; public BookController(BookRepository books){this.books=books;} @GetMapping public List<Book> all(){return books.findAll();} @PostMapping public Book create(@RequestBody Book book){return books.save(book);} }
