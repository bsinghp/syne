package com.ai.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ai.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
