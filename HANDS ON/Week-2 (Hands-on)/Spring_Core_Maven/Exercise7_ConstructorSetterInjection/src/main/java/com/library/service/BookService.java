package com.library.service;
import com.library.repository.BookRepository;
public class BookService { private final BookRepository bookRepository; private String branchName; public BookService(BookRepository bookRepository) { this.bookRepository = bookRepository; } public void setBranchName(String branchName) { this.branchName = branchName; } public void showConfiguration() { System.out.println("Constructor injection: " + bookRepository.getName()); System.out.println("Setter injection: branch = " + branchName); } }
