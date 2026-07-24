package com.library;
import com.library.model.Book;
import com.library.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
@SpringBootApplication public class LibraryManagementApplication { public static void main(String[] args) { SpringApplication.run(LibraryManagementApplication.class, args); } @Bean CommandLineRunner loadData(BookRepository books) { return args -> { books.save(new Book("Spring in Action", "Craig Walls")); System.out.println("Library API started; sample books: " + books.count()); }; } }
