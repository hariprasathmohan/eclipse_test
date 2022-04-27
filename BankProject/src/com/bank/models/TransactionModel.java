//$Id$
package com.bank.models;

import java.sql.Date;

public class TransactionModel {
	private Date date;
	private String fromAccount;
	private String toAccount;
	private String purpose;
	private String type;
	private double creditAmount;
	private double debitAmount;
	
	public TransactionModel() {
		super();
	}
	public TransactionModel(Date date, String fromAccount, String toAccount, String purpose, String type, double creditAmount, double debitAmount) {
		super();
		this.date = date;
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
		this.purpose = purpose;
		this.type = type;
		this.creditAmount = creditAmount;
		this.debitAmount = debitAmount;
	}
	
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public String getFromAccount() {
		return fromAccount;
	}
	public void setFromAccount(String fromAccount) {
		this.fromAccount = fromAccount;
	}
	public String getToAccount() {
		return toAccount;
	}
	public void setToAccount(String toAccount) {
		this.toAccount = toAccount;
	}
	public String getPurpose() {
		return purpose;
	}
	public void setPurpose(String purpose) {
		this.purpose = purpose;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public double getCreditAmount() {
		return creditAmount;
	}
	public void setCreditAmount(double creditAmount) {
		this.creditAmount = creditAmount;
	}
	public double getDebitAmount() {
		return debitAmount;
	}
	public void setDebitAmount(double debitAmount) {
		this.debitAmount = debitAmount;
	}
}
