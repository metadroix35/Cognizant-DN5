package com.cognizant.country.repository;

import com.cognizant.country.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country, Long> {

    // Search countries containing a given string, sorted ascending
    List<Country> findByNameContainingIgnoreCaseOrderByNameAsc(String keyword);

    // Countries whose names start with a specified alphabet, sorted ascending
    List<Country> findByNameStartingWithOrderByNameAsc(String letter);
}
