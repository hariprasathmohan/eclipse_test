<%@page import="com.bank.database.BankDAO"%>
<%@page import="com.bank.models.BankModel"%>
<%@page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%= "<option value='none'>Select Branch</option>" %>
<%
	String bank = request.getParameter("bank");
	BankDAO bankdb = new BankDAO();
	List<BankModel> banks = bankdb.getBranchs(bank);
	for(BankModel bankModel : banks){%>
		<%= "<option value=" + bankModel.getCode() + ">"+ bankModel.getBranchName() + "</option>" %>
<%}%>