package com.library;
import com.library.service.BookService;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Application { public static void main(String[] args) { try (var context = new ClassPathXmlApplicationContext("applicationContext.xml")) { System.out.println("IoC container loaded " + context.getBeanDefinitionCount() + " beans"); context.getBean(BookService.class).issueBook("Spring in Action"); } } }
