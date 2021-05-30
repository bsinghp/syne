package com.synechron.exception;

public class AccountNotFoundException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AccountNotFoundException(int accountNumber) {
		super(accountNumber + "");
	}

	
}
