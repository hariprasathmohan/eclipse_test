//$Id$
package com.bank.controllers;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.bank.database.UserDAO;
import com.bank.models.AccountModel;
import com.bank.utils.EncryptDecrypt;

public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	String oldPassword,newPassword,confirmPassword;
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		oldPassword = req.getParameter("oldPassword");
		newPassword = req.getParameter("newPassword");
		confirmPassword = req.getParameter("password");
		UserDAO userdb = new UserDAO();
		
		PrintWriter out= resp.getWriter();
		out.println("<script language='Javascript'>");
		
		HttpSession session = req.getSession(false);
		AccountModel myAccount = (AccountModel) session.getAttribute("myAccount");
		
		if(!oldPassword.equals(newPassword)) {
			if(userdb.validatePassword(myAccount.getAccountNumber(), oldPassword)) {
				if(newPassword.equals(confirmPassword)) {
					EncryptDecrypt enc = new EncryptDecrypt();
					userdb.updatePassword(myAccount.getAccountNumber(), enc.encrypt(newPassword), false);
					out.println("alert('Password Changed Sucessfully'); window.location.href ='Login.jsp'");
				} else {
					out.println("alert('Password and confirm password are not same'); history.back();");
				}
			} else {
				out.println("alert('Invalid password'); history.back();");
			}
		} else {
			out.println("alert('Existing password and New password must not be same'); history.back();");
		}
		out.println("</script>");
	}
	
}
