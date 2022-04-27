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

public class OtherDepositApplicationServlet extends HttpServlet implements AccountOperations {
	private static final long serialVersionUID = 1L;
	String accountNumber,depositAccountNumber , type,accType = null;
	int numOfMonths;
	double amount;
	
	AccountModel account;
	AccountDao accountdb = new AccountDao();
	TransactionDAO transactiondb = new TransactionDAO();
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		account = (AccountModel) req.getSession(false).getAttribute("myAccount");
		accountNumber = req.getParameter("accountNumber");
		numOfMonths = Integer.parseInt(req.getParameter("numOfMonth"));
		amount = Double.parseDouble(req.getParameter("amount"));
		
		PrintWriter out = resp.getWriter();
		out.println("<script language='Javascript'>");
		accType = req.getParameter("accType");
		if(accType != null) {
			
			if(!account.getAccountNumber().equals(accountNumber)) {
				out.println("alert('Your Account Number is Incorrect');  history.back();");
			} else if(numOfMonths < 10) {
				out.println("alert('Open account for atleast 10 months');  history.back();");
			} else if(amount <= 0) {
				out.println("alert('Enter valid amount');  history.back();");
			}		
			else {
				if(accType.equalsIgnoreCase("fd")) {
					FixedDepositDAO fixeddb = new FixedDepositDAO();
					type = "FD";
					depositAccountNumber = "FD"+accountNumber+fixeddb.getSize();
					OtherDepositModel odModel = new OtherDepositModel(accountNumber, depositAccountNumber, amount, Utils.curr_date(), Utils.curr_date(), Utils.FD_INTEREST, numOfMonths, amount , type);
					fixeddb.insertFixedAccountDetails(odModel);
					String messege = debit(amount);
					if(!messege.equalsIgnoreCase("success")) {
						out.println("alert('"+messege+"'); history.back();");
					} else {
						out.println("alert('Fixed Deposit Account Created Sucessfully'); window.location.href ='AccountSummary.jsp'");					
					}
				} else if(accType.equalsIgnoreCase("rd")) {
					RecurringDepositDAO recurringdb = new RecurringDepositDAO();
					type = "RD";
					depositAccountNumber = "RD"+accountNumber+recurringdb.getSize();
					OtherDepositModel odModel = new OtherDepositModel(accountNumber, depositAccountNumber, amount, Utils.curr_date(), Utils.curr_date(), Utils.RD_INTEREST, numOfMonths, amount, type);
					recurringdb.insertRecurringAccountDetails(odModel);
					String messege = debit(amount);
					if(!messege.equalsIgnoreCase("success")) {
						out.println("alert('"+messege+"'); history.back();");
					} else {
						out.println("alert('Recurring Deposit Account Created Sucessfully'); window.location.href ='AccountSummary.jsp'");					
					}
				}
			}
		} else {
			out.println("alert('Please Select the deposit account');  history.back();");
		}
			
		out.println("</script>");
	}
	
	@Override
	public void credit(double amount) {
		TransactionModel transaction = new TransactionModel(Utils.curr_date(), depositAccountNumber, accountNumber, type+" account", type, amount, 0);
		transactiondb.insertTransaction(transaction);
	}
	@Override
	public String debit(double amount) {
		if(accountdb.getBalance(account.getAccountNumber()) <= 0) {
			return "Your account balance is ZERO[0]";
		} else if(accountdb.getBalance(account.getAccountNumber()) < amount) {
			return "Insuffecient funds your available balance is "+accountdb.getBalance(account.getAccountNumber());
		} else {
			double balance = accountdb.getBalance(accountNumber) - amount;
			account.setBalance(balance);
			TransactionModel transaction = new TransactionModel(Utils.curr_date(), accountNumber, depositAccountNumber, type+" account", type, 0, amount);
			transactiondb.insertTransaction(transaction);
			accountdb.updateBalance(accountNumber, balance);
			credit(amount);
			return "success";
		}
	}
}
