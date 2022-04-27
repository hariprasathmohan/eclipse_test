//$Id$
package com.bank.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.database.LoanDAO;
import com.bank.models.AccountModel;
import com.bank.models.LoanModel;
import com.bank.utils.Utils;

public class LoanApplicationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String type,purpose,loanAccNo;
	double amount;
	LoanDAO loandb = new LoanDAO();
	AccountModel account;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		type = req.getParameter("type");
		purpose = req.getParameter("purpose");
		amount = Double.parseDouble(req.getParameter("amount"));
		account = (AccountModel) req.getSession(false).getAttribute("myAccount");
		loanAccNo = account.getAccountNumber() + loandb.getSize();
		
		PrintWriter out= resp.getWriter();
		out.println("<script language='Javascript'>");
		
		if(!req.getParameter("type").equals("none")) {
			if(!purpose.equals(" ")) {
				if(amount >= 0) {
					LoanModel loan = new LoanModel(account.getAccountNumber(), loanAccNo, "pending", Utils.LOAN_INTEREST, amount, amount, Utils.curr_date(), Utils.curr_date(), type, purpose);
					int count = loandb.insertLoanAccount(loan);
					if(count>0) {
						
						out.println("alert('Form Submitted Sucessfully'); window.location.href ='AccountSummary.jsp'");
					}
					else {
						out.println("alert('Error Occured try again later'); window.location.href ='AccountSummary.jsp'");
					}
				} else {
					out.println("alert('Enter valid Amount');  history.back();");
				}
			}else {
				out.println("alert('Enter purpose');  history.back();");
			}
		} else {
			out.println("alert('please Select the loan type');  history.back();");
		}
		out.println("</script>");
	}
	
}
