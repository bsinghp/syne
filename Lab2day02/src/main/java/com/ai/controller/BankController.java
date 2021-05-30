package com.ai.controller;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ai.service.AccountService;

@RestController
@RequestMapping("/bank")
public class BankController {
	@Autowired
	private AccountService accountService;

	@PostConstruct
	public void printDetails() {
		System.out.println("*********************************");
		System.out.println(accountService.getClass().getName());
		System.out.println(accountService.getClass().getSuperclass().getName());
		System.out.println("*********************************");
	}

	@PostMapping("/deposit")
	public String deposit(@RequestParam("accountnumber") int accountNumber, @RequestParam int amount,
			@RequestParam String type) {
		accountService.deposit(accountNumber, amount, type);
		return "Deposit successful";
	}

	@PostMapping("/withdraw")
	public String withdraw(@RequestParam("accountnumber") int accountNumber, @RequestParam int amount,
			@RequestParam String type) {
		accountService.withdraw(accountNumber, amount, type);
		return "Withdraw successful";
	}

}
