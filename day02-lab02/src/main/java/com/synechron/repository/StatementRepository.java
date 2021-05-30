package com.synechron.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synechron.domain.Statement;

public interface StatementRepository extends JpaRepository<Statement, Integer> {

}
