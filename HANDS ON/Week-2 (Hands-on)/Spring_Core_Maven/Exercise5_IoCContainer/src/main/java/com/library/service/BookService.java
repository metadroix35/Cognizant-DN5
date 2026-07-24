package com.library.service;
import com.library.repository.BookRepository;
public class BookService { private BookRepository bookRepository; public void setBookRepository(BookRepository bookRepository) { this.bookRepository = bookRepository; } public void issueBook(String title) { System.out.println("Service issuing: " + title); bookRepository.save(title); } }
