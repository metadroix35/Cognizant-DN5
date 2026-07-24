package com.cognizant.stock;

import com.cognizant.stock.entity.Stock;
import com.cognizant.stock.repository.StockRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class StockQueryApplication {

    public static void main(String[] args) {
        SpringApplication.run(StockQueryApplication.class, args);
    }

    @Bean
    CommandLineRunner run(StockRepository stockRepository) {
        return args -> {

            System.out.println("=== Part 1 Exercise 2: Stock Query Methods ===");
            System.out.println();

            // 1. Facebook stock during September 2019
            System.out.println("--- 1. Facebook (FB) stock during September 2019 ---");
            LocalDate sep1 = LocalDate.of(2019, 9, 1);
            LocalDate sep30 = LocalDate.of(2019, 9, 30);
            List<Stock> fbSep = stockRepository.findByCodeAndDateBetween("FB", sep1, sep30);
            fbSep.forEach(s -> System.out.println("  " + s));
            System.out.println();

            // 2. Google stock where closing price > 1250
            System.out.println("--- 2. Google (GOOGL) stock where close > 1250 ---");
            List<Stock> googleHigh = stockRepository.findByCodeAndCloseGreaterThan(
                    "GOOGL", new BigDecimal("1250.00"));
            googleHigh.forEach(s -> System.out.println("  " + s));
            System.out.println();

            // 3. Top 3 highest transaction volumes
            System.out.println("--- 3. Top 3 highest transaction volumes ---");
            List<Stock> top3Volume = stockRepository.findTop3ByOrderByVolumeDesc();
            top3Volume.forEach(s -> System.out.println("  " + s));
            System.out.println();

            // 4. Lowest three Netflix stock values
            System.out.println("--- 4. Lowest three Netflix (NFLX) stock values ---");
            List<Stock> nflxLow = stockRepository.findTop3ByCodeOrderByCloseAsc("NFLX");
            nflxLow.forEach(s -> System.out.println("  " + s));
            System.out.println();

            System.out.println("=== Stock Query Methods Demo Complete ===");
        };
    }
}
