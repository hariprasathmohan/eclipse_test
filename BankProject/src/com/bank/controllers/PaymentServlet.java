//$Id$
package com.bank.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.database.AccountDao;
import com.bank.database.BankDAO;
import com.bank.database.TransactionDAO;
import com.bank.models.AccountModel;
import com.bank.models.TransactionModel;
import com.bank.utils.Utils;


public class PaymentServlet extends HttpServlet implements AccountOperations {
	private static final long serialVersionUID = 1L;
	String name,toAccountNumber,ifsc,purpose;
	double amount;
	
	AccountDao accountdb = new AccountDao();
	TransactionDAO transactiondb = new TransactionDAO();
	AccountModel account ;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		account = (AccountModel) req.getSession(false).getAttribute("myAccount");
		name = req.getParameter("name");
		toAccountNumber = req.getParameter("accountNumber");
		ifsc = req.getParameter("ifsc");
		purpose = req.getParameter("purpose");
		amount = Double.parseDouble(req.getParameter("amount"));

		PrintWriter out= resp.getWriter();
		out.println("<script language='Javascript'>");
		
		if(!account.getAccountNumber().equals(toAccountNumber)) {
			if(toAccountNumber != null) {
				if(accountdb.getAccountDetails(toAccountNumber) != null) {
					String IFSC = new BankDAO().getBankDetails(accountdb.getAccountDetails(toAccountNumber).getBranchCode()).getIfsc();
					if(ifsc.equals(IFSC)) {
						if(amount > 0) {
							String messege=debit(amount);
							if(!messege.equalsIgnoreCase("success")) {
								out.println("alert('"+messege+"'); history.back();");
							} else {
								out.println("alert('Transfered Sucessfully'); window.location.href ='AccountSummary.jsp'");
							}
						} else {
							out.println("alert('Enter valid amount'); history.back();");
						}
					} else {
						out.println("alert('Invalid IFSC code'); history.back();");
					}
				} else {
					out.println("alert('Invalid Account Number'); history.back();");
				}
			}
		} else {
			out.println("alert('You cannot pay your own account'); history.back();");
		}	
		out.println("</script>");
	}
	
	//deposit to other account
	@Override
	public void credit(double amount) {
		String myAccount = account.getAccountNumber();
		double balance = accountdb.getBalance(toAccountNumber) + amount;
		TransactionModel transaction = new TransactionModel(Utils.curr_date(), toAccountNumber, myAccount, purpose, "", amount, 0);
		transactiondb.insertTransaction(transaction);
		accountdb.updateBalance(toAccountNumber, balance);
	}
	@Override
	public String debit(double amount) {
		if(accountdb.getBalance(account.getAccountNumber()) <= 0) {
			return "Your account balance is ZERO[0]";
		} else if(accountdb.getBalance(account.getAccountNumber()) < amount) {
			return "Insuffecient funds your available balance is "+accountdb.getBalance(account.getAccountNumber());
		} else {
			String myAccount = account.getAccountNumber();
			double balance = accountdb.getBalance(myAccount) - amount;
			account.setBalance(balance);
			TransactionModel transaction = new TransactionModel(Utils.curr_date(), myAccount, toAccountNumber, purpose, "", 0, amount);;
			transactiondb.insertTransaction(transaction);
			accountdb.updateBalance(myAccount, balance);
			credit(amount);
			return "success";
		}
	}
}
