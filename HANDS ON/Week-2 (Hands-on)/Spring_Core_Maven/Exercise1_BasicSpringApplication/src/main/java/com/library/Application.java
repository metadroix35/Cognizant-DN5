package com.library;
import com.library.repository.BookRepository;
import com.library.service.BookService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Application { public static void main(String[] args) { try (var context = new ClassPathXmlApplicationContext("applicationContext.xml")) { context.getBean(BookRepository.class).displayRepository(); context.getBean(BookService.class).displayService(); } } }
