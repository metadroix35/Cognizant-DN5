package com.library.service;
import com.library.repository.BookRepository;
public class BookService { private BookRepository bookRepository; public void setBookRepository(BookRepository bookRepository) { this.bookRepository = bookRepository; } public void listBooks() { System.out.println("Dependency injected: " + (bookRepository != null)); System.out.println("Available books: " + bookRepository.findAll()); } }
