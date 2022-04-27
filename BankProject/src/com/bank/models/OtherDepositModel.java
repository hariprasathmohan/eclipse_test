//$Id$
package com.bank.models;

import java.sql.Date;

public class OtherDepositModel extends AccountModel {
	private String depositAccountNumber;
	private double balance;
	private Date date;
	private Date updatedAt;
	private double interest;
	private int noOfMonths;
	private String type;
	private double depositAmount;
	
	public OtherDepositModel(String accountNumber, String depositAccountNumber, double balance, Date date, 
			Date updatedAt, double interest, int noOfMonths, double depositAmount, String type) {
		super(accountNumber);
		this.depositAccountNumber = depositAccountNumber;
		this.balance = balance;
		this.date = date;
		this.updatedAt = updatedAt;
		this.interest = interest;
		this.noOfMonths = noOfMonths;
		this.depositAmount = depositAmount;
		this.type = type;
	}
	public OtherDepositModel() {
		super();
	}
	
	public String getDepositAccountNumber() {
		return depositAccountNumber;
	}
	public void setDepositAccountNumber(String depositAccountNumber) {
		this.depositAccountNumber = depositAccountNumber;
	}
	public String getType() {
		return this.type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}
	public double getInterest() {
		return interest;
	}
	public void setInterest(double interest) {
		this.interest = interest;
	}
	public int getNoOfMonths() {
		return noOfMonths;
	}
	public void setNoOfMonths(int noOfMonths) {
		this.noOfMonths = noOfMonths;
	}
	public double getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(double depositAmount) {
		this.depositAmount = depositAmount;
	}
}
