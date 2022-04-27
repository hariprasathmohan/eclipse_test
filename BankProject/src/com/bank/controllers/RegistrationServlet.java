//$Id$
package com.bank.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bank.database.AccountDao;
import com.bank.database.CustomerDAO;
import com.bank.database.UserDAO;
import com.bank.models.AccountModel;
import com.bank.models.Address;
import com.bank.models.CustomerModel;
import com.bank.models.UserModel;
import com.bank.utils.EncryptDecrypt;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	AccountDao accountdb = new AccountDao();
	CustomerDAO customerdb = new CustomerDAO();
	UserDAO userdb = new UserDAO();
	EncryptDecrypt encDec = new EncryptDecrypt();
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		String name=req.getParameter("name");
		String addressLine1=req.getParameter("address1");
		String addressLine2=req.getParameter("address2");
		String city=req.getParameter("city");
		String state=req.getParameter("state");
		String mobile=req.getParameter("mobile");
		String email=req.getParameter("email");
		Address address=new Address(addressLine1, addressLine2, city, state);
		String branchCode=req.getParameter("branch");
		double balance=10000;
		PrintWriter out= resp.getWriter();
		out.println("<script language='Javascript'>");
		
		if(validateName(name)) {
			if(validateEmail(email)) {
				if(validateMobile(mobile)) {
					if(!req.getParameter("branch").equals("none")) {
						if(!customerdb.isEmailAvailable(email)) {
							if(req.getParameter("accountType") != null) {
								String type = req.getParameter("accountType");
								CustomerModel customer = new CustomerModel(name, address, email, mobile);
								int cusid = customerdb.insertCustomerDetails(customer);
								String accountNumber=branchCode+cusid;
								String userId=name+accountNumber;
								String password = encDec.encrypt(getPasssword());
								AccountModel account = new AccountModel(accountNumber, cusid, type, branchCode, balance);
								accountdb.insertAccountDetails(account);
								UserModel user = new UserModel(accountNumber, userId, password, true);
								userdb.insertUser(user, password);
								RequestDispatcher rd=req.getRequestDispatcher("AccountOpen.jsp");
								req.setAttribute("action", "account");
								req.setAttribute("accountNumber", accountNumber);
								req.setAttribute("userId", userId);
								req.setAttribute("password", encDec.decrypt(password));
								rd.forward(req, resp);
							} else {//account type
								out.println("alert('please select Account type'); history.back();");								
							}									
						} else {// email check
							out.println("alert('Email Already Exists'); history.back();");
						}
					}else {// branch select
						out.println("alert('please select bank and branch'); history.back();");
					}
				}else {// mobile number
					out.println("alert('Invalid Mobile Number'); history.back();");
				}
			}else {// Email
				out.println("alert('Invalid Email'); history.back();");
			}
		}else {// name
			out.println("alert('Invalid name'); history.back();");
		}
		out.println("</script>");		
	}
	//Validations
	boolean validateName(String name) {
		Pattern namePattern = Pattern.compile("^([a-zA-Z\\s.]{3,})$"); 
		Matcher match=namePattern.matcher(name);
		return match.find();
	}
	boolean validateEmail(String email) {
		Pattern emailPattern = Pattern.compile("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[a-zA-Z]{2,6}$"); 
		Matcher match=emailPattern.matcher(email);
		return match.find();
	}
	boolean validateMobile(String mobile) {
		Pattern mobilePattern = Pattern.compile("^[6-9][0-9]{9}$"); 
		Matcher match=mobilePattern.matcher(mobile);
		return match.find();
	}
	//Auto generated password
	public String getPasssword() {
		String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789!@#$%^&*";
		String pwd = "";
		for(int i=1;i<=5;i++) {
			pwd+=characters.charAt(new Random().nextInt(characters.length()));
		}
		return pwd;
	}
}
