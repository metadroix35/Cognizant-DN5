package com.cognizant.hqljpql.repository;

import com.cognizant.hqljpql.entity.Attempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttemptRepository extends JpaRepository<Attempt, Long> {

    // Exercise 3: HQL joining User, Attempt, AttemptQuestion, Question, AttemptOption, Option
    @Query("SELECT a FROM Attempt a " +
            "JOIN FETCH a.user u " +
            "JOIN FETCH a.attemptQuestions aq " +
            "JOIN FETCH aq.question q " +
            "JOIN FETCH aq.attemptOptions ao " +
            "JOIN FETCH ao.option o")
    List<Attempt> findAllAttemptsWithDetails();
}
