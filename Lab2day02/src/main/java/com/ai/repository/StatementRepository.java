package com.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.domain.Statement;

public interface StatementRepository extends JpaRepository<Statement, Integer> {

}
