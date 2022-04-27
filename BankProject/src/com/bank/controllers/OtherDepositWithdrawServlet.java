//$Id$
package com.bank.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.database.AccountDao;
import com.bank.database.FixedDepositDAO;
import com.bank.database.RecurringDepositDAO;
import com.bank.database.TransactionDAO;
import com.bank.models.AccountModel;
import com.bank.models.OtherDepositModel;
import com.bank.models.TransactionModel;
import com.bank.utils.Utils;

public class OtherDepositWithdrawServlet extends HttpServlet implements AccountOperations {
	private static final long serialVersionUID = 1L;
	
	AccountDao accountdb = new AccountDao();
	FixedDepositDAO fixeddb = new FixedDepositDAO();
	RecurringDepositDAO recurringdb = new RecurringDepositDAO();
	TransactionDAO transactiondb = new TransactionDAO();
	
	AccountModel account;
	OtherDepositModel otherModel;
	
	String depositAccountNumber,type;
	double amount;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		depositAccountNumber = req.getParameter("fdAccNo");
		amount = Double.parseDouble(req.getParameter("amount"));
		
		type = depositAccountNumber.substring(0, 2);
		
		if(type.equalsIgnoreCase("fd")) {
			otherModel = fixeddb.getAccountDetails(depositAccountNumber);
		}
		if(type.equalsIgnoreCase("rd")) {
			otherModel = recurringdb.getAccountDetails(depositAccountNumber);
		}
		
		account = (AccountModel) req.getSession(false).getAttribute("myAccount");
		
		PrintWriter out= resp.getWriter();
		out.println("<script language='Javascript'>");
		
		 if(amount <= 0) {
				out.println("alert('Enter valid amount');  history.back();");
			}else{
				String messege = debit(amount);
				if(!messege.equalsIgnoreCase("success")) {
					out.println("alert('"+messege+"'); history.back();");
				}
				else {
					out.println("alert('Transfered Sucessfully'); window.location.href ='AccountSummary.jsp'");
					out.println("</script>");
				}
			}
		out.println("</script>");
	}
	
	@Override
	public void credit(double amount) {
		double balance = accountdb.getBalance(account.getAccountNumber()) + amount;
		TransactionModel transaction = new TransactionModel(Utils.curr_date(), account.getAccountNumber(), depositAccountNumber, "from "+type, type, amount, 0);
		transactiondb.insertTransaction(transaction);
		accountdb.updateBalance(account.getAccountNumber(), balance);		
	}
	@Override
	public String debit(double amount) {
		if(type.equalsIgnoreCase("fd")) {
			if(fixeddb.getBalance(depositAccountNumber) <= 0)
				return "Your account balance is ZERO[0]";
			else if(fixeddb.getBalance(depositAccountNumber) < amount)
				return "Insuffecient funds your available balance is "+fixeddb.getBalance(depositAccountNumber);
			else {
				double balance = fixeddb.getBalance(depositAccountNumber) - amount;
				otherModel.setBalance(balance);
				fixeddb.updateBalance(depositAccountNumber, balance);
				TransactionModel transaction = new TransactionModel(Utils.curr_date(), depositAccountNumber, account.getAccountNumber(), "from "+type, type, 0, amount);
				transactiondb.insertTransaction(transaction);
				credit(amount);
				return "success";
			}
		}
		if(type.equalsIgnoreCase("rd")) {
			if(recurringdb.getBalance(depositAccountNumber) <= 0)
				return "Your account balance is ZERO[0]";
			else if(recurringdb.getBalance(depositAccountNumber) < amount)
				return "Insuffecient funds your available balance is "+recurringdb.getBalance(depositAccountNumber);
			else {
				double balance = recurringdb.getBalance(depositAccountNumber) - amount;
				otherModel.setBalance(balance);
				recurringdb.updateBalance(depositAccountNumber, balance);
				TransactionModel transaction = new TransactionModel(Utils.curr_date(), depositAccountNumber, account.getAccountNumber(), "from Rd", "Rd", 0, amount);
				transactiondb.insertTransaction(transaction);
				credit(amount);
				return "success";
			}
		}
		return "Technical Error";
	}
}
