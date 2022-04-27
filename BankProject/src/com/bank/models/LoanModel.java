//$Id$
package com.bank.models;

import java.sql.Date;

public class LoanModel extends AccountModel {
	private int customerId;
	private String loanAccountNumber;
	private String status;
	private double interest;
	private double amount;
	private double balance;
	private Date createdAt;
	private Date updatedAt;
	private String loanType;
	private String loanPurpose;
	
	public LoanModel() {
		super();
	}
	public LoanModel( String accountNumber, String loanAccountNumber, String status, double interest, double amount, double balance, Date createdAt, Date updatedAt, String loanType, String loanPurpose) {
		super(accountNumber);
		this.loanAccountNumber = loanAccountNumber;
		this.status = status;
		this.interest = interest;
		this.amount = amount;
		this.balance = balance;
		this.createdAt = createdAt;
		this.updatedAt = updatedAt;
		this.loanType = loanType;
		this.loanPurpose = loanPurpose;
	}
	
	public String getLoanType() {
		return loanType;
	}
	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}
	public String getLoanPurpose() {
		return loanPurpose;
	}
	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getLoanAccountNumber() {
		return loanAccountNumber;
	}
	public void setLoanAccountNumber(String loanAccountNumber) {
		this.loanAccountNumber = loanAccountNumber;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	
}
