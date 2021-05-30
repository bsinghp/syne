package com.synechron.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.synechron.domain.Account;

public interface AccountRepository extends JpaRepository<Account, Integer> {

}
