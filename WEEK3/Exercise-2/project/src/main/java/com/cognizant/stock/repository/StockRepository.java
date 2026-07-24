package com.cognizant.stock.repository;

import com.cognizant.stock.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface StockRepository extends JpaRepository<Stock, Long> {

    // 1. Facebook stock during September 2019
    List<Stock> findByCodeAndDateBetween(String code, LocalDate startDate, LocalDate endDate);

    // 2. Google stock where closing price is greater than 1250
    List<Stock> findByCodeAndCloseGreaterThan(String code, BigDecimal closePrice);

    // 3. Top 3 highest transaction volumes
    List<Stock> findTop3ByOrderByVolumeDesc();

    // 4. Lowest three Netflix stock values (by closing price)
    List<Stock> findTop3ByCodeOrderByCloseAsc(String code);
}
