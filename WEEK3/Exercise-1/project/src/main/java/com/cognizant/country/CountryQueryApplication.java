package com.cognizant.country;

import com.cognizant.country.entity.Country;
import com.cognizant.country.repository.CountryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CountryQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(CountryQueryApplication.class, args);
    }

    @Bean
    CommandLineRunner run(CountryRepository countryRepository) {
        return args -> {

            System.out.println("=== Part 1 Exercise 1: Country Query Methods ===");
            System.out.println();

            // Test 1: Search countries containing "ou" (sorted ascending)
            System.out.println("--- Countries containing 'ou' (sorted ascending) ---");
            List<Country> countriesWithOu = countryRepository.findByNameContainingIgnoreCaseOrderByNameAsc("ou");
            countriesWithOu.forEach(c -> System.out.println("  " + c.getName()));
            System.out.println();

            // Test 2: Countries starting with 'Z' (sorted ascending)
            System.out.println("--- Countries starting with 'Z' ---");
            List<Country> zCountries = countryRepository.findByNameStartingWithOrderByNameAsc("Z");
            zCountries.forEach(c -> System.out.println("  " + c.getName()));
            System.out.println();

            // Test 3: Countries starting with 'A'
            System.out.println("--- Countries starting with 'A' ---");
            List<Country> aCountries = countryRepository.findByNameStartingWithOrderByNameAsc("A");
            aCountries.forEach(c -> System.out.println("  " + c.getName()));
            System.out.println();

            // Test 4: Countries containing "ia"
            System.out.println("--- Countries containing 'ia' (sorted ascending) ---");
            List<Country> iaCountries = countryRepository.findByNameContainingIgnoreCaseOrderByNameAsc("ia");
            iaCountries.forEach(c -> System.out.println("  " + c.getName()));
            System.out.println();

            System.out.println("=== Query Methods Demo Complete ===");
        };
    }
}
