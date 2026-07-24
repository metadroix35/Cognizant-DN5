package com.cognizant.hqljpql.repository;

import com.cognizant.hqljpql.entity.AttemptQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttemptQuestionRepository extends JpaRepository<AttemptQuestion, Long> {
}
