//$Id$
package com.bank.controllers;

public interface AccountOperations {
	public abstract void credit(double amount);
	public abstract String debit(double amount);
}