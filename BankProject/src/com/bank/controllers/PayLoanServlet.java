//$Id$
package com.bank.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.database.AccountDao;
import com.bank.database.LoanDAO;
import com.bank.database.TransactionDAO;
import com.bank.models.AccountModel;
import com.bank.models.LoanModel;
import com.bank.models.TransactionModel;
import com.bank.utils.Utils;

public class PayLoanServlet extends HttpServlet implements AccountOperations {
	private static final long serialVersionUID = 1L;
	
	AccountDao accountdb = new AccountDao();
	LoanDAO loandb = new LoanDAO();
	TransactionDAO transactiondb = new TransactionDAO();
	
	AccountModel account;
	LoanModel loan;
	
	String loanAccNo;
	double amount;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		loanAccNo = req.getParameter("loanAccNo");
		amount = Double.parseDouble(req.getParameter("amount"));
		
		account = (AccountModel) req.getSession(false).getAttribute("myAccount");
		loan = loandb.getLoanAccountDetails(loanAccNo);

		PrintWriter out= resp.getWriter();
		out.println("<script language='Javascript'>");
		
		String messege = debit(amount);
		if(!messege.equalsIgnoreCase("success")) {
			out.println("alert('"+messege+"'); history.back();");
		} else if(amount <= 0) {
			out.println("alert('Invalid Amount');  history.back();");
		}
		else {
			out.println("alert('Transfered Sucessfully'); window.location.href ='AccountSummary.jsp'");
		}
		out.println("</script>");
	}
	
	@Override
	public void credit(double amount) {
		double balance = loandb.getBalance(loanAccNo) - amount;
		TransactionModel transaction = new TransactionModel(Utils.curr_date(), loanAccNo, account.getAccountNumber(), "repayment to"+loan.getLoanType(), "Loan", amount, 0);
		transactiondb.insertTransaction(transaction);
		loandb.updateBalance(loanAccNo, balance);
	}
	
	@Override
	public String debit(double amount) {
		if(accountdb.getBalance(account.getAccountNumber()) <= 0)
			return "Your account balance is ZERO[0]";
		else if(accountdb.getBalance(account.getAccountNumber()) < amount)
			return "Insuffecient funds your available balance is "+accountdb.getBalance(account.getAccountNumber());
		else if(loan.getBalance()<amount)
			return"Your payable amount is "+ loan.getBalance()+"only!";
		else {
			double balance = accountdb.getBalance(account.getAccountNumber()) - amount;
			account.setBalance(balance);
			accountdb.updateBalance(account.getAccountNumber(), balance);
			TransactionModel transaction = new TransactionModel(Utils.curr_date(), account.getAccountNumber(), loanAccNo, "repayment to"+loan.getLoanType(), "Loan", 0, amount);
			transactiondb.insertTransaction(transaction);
			credit(amount);
			return "success";
		}
	}
}
