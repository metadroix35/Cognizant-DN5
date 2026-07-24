package com.cognizant.hqljpql.repository;

import com.cognizant.hqljpql.entity.AttemptOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptOptionRepository extends JpaRepository<AttemptOption, Long> {
}
