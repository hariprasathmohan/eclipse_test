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
import com.bank.database.BeneficieryDAO;
import com.bank.models.AccountModel;
import com.bank.models.BeneficieryModel;

public class AddBeneficieryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String name,accountNumber,ifsc;
	BeneficieryDAO beneficierydb = new BeneficieryDAO();
	AccountDao accountdb = new AccountDao();
	AccountModel account;
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		name = req.getParameter("name");
		accountNumber = req.getParameter("accountNumber");
		ifsc = req.getParameter("ifsc");
		account = (AccountModel) req.getSession(false).getAttribute("myAccount");
		
		PrintWriter out = resp.getWriter();
		out.println("<script language='Javascript'>");
		
		AccountModel account = (AccountModel) req.getSession(false).getAttribute("myAccount");
		AccountModel toAccount = accountdb.getAccountDetails(accountNumber);
		
		if(!account.getAccountNumber().equals(accountNumber)) {
			if(toAccount != null) {
				if(new BankDAO().getBankDetails(toAccount.getBranchCode()).getIfsc().equals(ifsc)) {
					BeneficieryModel beneficiery = new BeneficieryModel(account.getAccountNumber(), name, accountNumber, ifsc);
					beneficierydb.insertBeneficiery(beneficiery);
					out.println("alert('Added Sucessfully'); window.location.href ='AccountSummary.jsp'");
				}else {
					out.println("alert('Invalid Ifsc code'); history.back();");
				}
			}else {
				out.println("alert('Account number does not exists'); history.back();");
			}
		} else {
			out.println("alert('You cannot add your own account as beneficiery'); history.back();");
		}
		out.println("</script>");
	}
}
