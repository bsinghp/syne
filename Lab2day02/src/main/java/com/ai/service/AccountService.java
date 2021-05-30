package com.ai.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ai.domain.Account;
import com.ai.domain.Statement;
import com.ai.exception.AccountNotFoundException;
import com.ai.repository.AccountRepository;
import com.ai.repository.StatementRepository;

@Service
public class AccountService {
	@Autowired
	private AccountRepository accountRepository;

	@Autowired
	private StatementRepository statementRepository;

	public void deposit(int accountNumber, int amount, String type) {
		Account account = getAccount(accountNumber);
		account.setBalance(account.getBalance() + amount);
		accountRepository.save(account);
		createStatement(accountNumber, amount, type);
	}

	@Transactional
	public void withdraw(int accountNumber, int amount, String type) {
		Account account = getAccount(accountNumber);
		account.setBalance(account.getBalance() - amount);
		accountRepository.save(account);
		createStatement(accountNumber, amount, type);
	}
	
	private void createStatement(int accountNumber, int amount, String type) {
		Statement stmt = new Statement();
		stmt.setAccountNumber(accountNumber);
		stmt.setType(type);
		stmt.setAmount(amount);
		statementRepository.save(stmt);
	}
	
	private Account getAccount(int accountNumber) {
		Optional<Account> optAccount = accountRepository.findById(accountNumber);
		Account account = optAccount.orElseThrow(() -> new AccountNotFoundException(accountNumber));
		return account;
	}

}
