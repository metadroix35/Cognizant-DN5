package com.library;
import com.library.service.BookService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Application { public static void main(String[] args) { try (var context = new ClassPathXmlApplicationContext("applicationContext.xml")) { context.getBean(BookService.class).listBooks(); } } }
