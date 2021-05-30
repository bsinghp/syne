package com.synechron.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.synechron.domain.Account;
import com.synechron.domain.Statement;
import com.synechron.repository.AccountRepository;
import com.synechron.repository.StatementRepository;

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
		Account account = optAccount.orElseThrow(() -> new RuntimeException("Account with " + accountNumber + " does not exist"));
		return account;
	}

}
