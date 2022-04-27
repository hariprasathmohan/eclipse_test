//$Id$
package com.bank.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.database.AccountDao;
import com.bank.database.UserDAO;
import com.bank.models.AccountModel;
import com.bank.models.UserModel;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	String userId,password;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		//com.bank.database.DatabaseCreation.createTbl();
		//new com.bank.bank.Bank();
		UserDAO userdb = new UserDAO();
		AccountDao accountdb = new AccountDao();
		userId = req.getParameter("userId");
		password = req.getParameter("password");

		PrintWriter out= resp.getWriter();
		out.println("<script language='Javascript'>");
		
		if(userId != null && password != null) {
			UserModel user = userdb.getUser(userId);
			if(user != null) {
				if(userdb.validatePassword(user.getAccountNumber(), password)) {
					AccountModel myAccount = accountdb.getAccountDetails(user.getAccountNumber());
					HttpSession session=req.getSession(true);
				    session.setAttribute("myAccount",myAccount);
				    if(user.isFirst()) {
				    	resp.sendRedirect("ChangePassword.jsp");
				    } else {
				    	resp.sendRedirect("AccountSummary.jsp");
				    }
				}else {
					out.println("alert('Your password is invalid'); history.back();");
				}
			}else {
				out.println("alert('Your UserId is invalid'); history.back();");
			}
		}
		out.println("</script>");
	}
	
}
