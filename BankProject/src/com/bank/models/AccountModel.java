//$Id$
package com.bank.models;

public class AccountModel {
	protected String accountNumber;
	private int customerId;
	private String accountType;
	private String branchCode;
	private double balance;
	
	public AccountModel(String accountNumber, int customerId, String accountType, String branchCode, double balance) {
		super();
		this.accountNumber = accountNumber;
		this.customerId = customerId;
		this.accountType = accountType;
		this.branchCode = branchCode;
		this.balance = balance;
	}
	public AccountModel(String accountNumber) {
		this.accountNumber=accountNumber;
	}
	public AccountModel() {
		super();
	}
	
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getAccountType() {
		return accountType;
	}
	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}
	public String getBranchCode() {
		return branchCode;
	}
	public void setBranchCode(String branchCode) {
		this.branchCode = branchCode;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
}
