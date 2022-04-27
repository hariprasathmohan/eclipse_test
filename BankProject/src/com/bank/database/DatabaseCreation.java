//$Id$
package com.bank.database;

import java.sql.Connection;
import java.sql.SQLException;

import com.bank.utils.Utils;

public class DatabaseCreation {
	public static void createTbl() {
		try {
			Connection con = Utils.getConnection();
			
			con.createStatement().executeUpdate("DROP TABLE IF EXISTS Customer");
			con.createStatement().executeUpdate("DROP TABLE IF EXISTS Account");
			con.createStatement().executeUpdate("DROP TABLE IF EXISTS TransactionHistory"); 
			con.createStatement().executeUpdate("DROP TABLE IF EXISTS FixedDeposit");
			con.createStatement().executeUpdate("DROP TABLE IF EXISTS RecurringDeposit");
			con.createStatement().executeUpdate("DROP TABLE IF EXISTS Loan");
			con.createStatement().executeUpdate("DROP TABLE IF EXISTS Users");
			con.createStatement().executeUpdate("DROP TABLE IF EXISTS Bank");
			con.createStatement().executeUpdate("DROP TABLE IF EXISTS Beneficiery");
			
			con.createStatement().executeUpdate("create table Customer("
					+ "id SERIAL PRIMARY KEY," 
					+ "name VARCHAR(50) NOT NULL," 
					+ "address VARCHAR(250) NOT NULL,"
					+ "email VARCHAR(50) NOT NULL,"
					+ "mobile VARCHAR(50) NOT NULL)");
			con.createStatement().executeUpdate("create table Account("
					+ "accountNumber VARCHAR(50) NOT NULL PRIMARY KEY,"
					+ "branchCode VARCHAR(5) NOT NULL,"
					+ "customerId INT,"
					+ "interest FLOAT(50),"
					+ "type  VARCHAR(50) NOT NULL,"
					+ "balance FLOAT(50))");
			con.createStatement().executeUpdate("create table TransactionHistory("
					+ "id SERIAL PRIMARY KEY,"
					+ "date DATE NOT NULL,"
					+ "fromAccount VARCHAR(50) NOT NULL,"
					+ "toAccount VARCHAR(50),"
					+ "purpose VARCHAR(50) NOT NULL,"
					+ "type VARCHAR(50),"
					+ "debit FLOAT(50),"
					+ "credit FLOAT(50))");
			con.createStatement().executeUpdate("create table FixedDeposit("
					+ "fdAccountNumber VARCHAR(50) NOT NULL PRIMARY KEY,"
					+ "accountNumber VARCHAR(50) NOT NULL,"
					+ "interest FLOAT(50),"
					+ "numberOfMonths INT,"
					+ "amount FLOAT(50),"
					+ "balance FLOAT(50),"
					+ "createdAt DATE NOT NULL,"
					+ "updatedAt DATE NOT NULL,"
					+ "remainingMonth INT)");
			con.createStatement().executeUpdate("create table RecurringDeposit("
					+ "rdAccountNumber VARCHAR(50) NOT NULL PRIMARY KEY,"
					+ "accountNumber VARCHAR(50) NOT NULL,"
					+ "interest FLOAT(50),"
					+ "numberOfMonths INT,"
					+ "amount FLOAT(50),"
					+ "balance FLOAT(50),"
					+ "createdAt DATE NOT NULL,"
					+ "updatedAt DATE NOT NULL,"
					+ "remainingMonth INT)");
			con.createStatement().executeUpdate("create table Loan("
					+ "loanNumber VARCHAR(50) NOT NULL PRIMARY KEY,"
					+ "type VARCHAR(50) NOT NULL,"
					+ "purpose VARCHAR(50) NOT NULL,"
					+ "accountNumber VARCHAR(50) NOT NULL,"
					+ "interest FLOAT(50),"
					+ "amount FLOAT(50),"
					+ "balance FLOAT(50),"
					+ "status VARCHAR(50),"
					+ "createdAt DATE NOT NULL,"
					+ "updatedAt DATE NOT NULL)");
			con.createStatement().executeUpdate("create table Users("
					+ "userId  VARCHAR(50) NOT NULL PRIMARY KEY,"
					+ "password  VARCHAR(50) NOT NULL,"
					+ "isFirst Boolean,"
					+ "accountNumber VARCHAR(50) NOT NULL)");
			con.createStatement().executeUpdate("create table Bank("
					+ "code VARCHAR(5) NOT NULL PRIMARY KEY,"
					+ "bankName VARCHAR(50),"
					+ "branchName VARCHAR(50) NOT NULL,"
					+ "ifsc VARCHAR(50) NOT NULL)");
			con.createStatement().executeUpdate("create table Beneficiery("
					+ "id SERIAL PRIMARY KEY,"
					+ "accountNumber VARCHAR(50),"
					+ "nickName VARCHAR(50) NOT NULL,"
					+ "beneAccNo VARCHAR(50) NOT NULL,"
					+ "ifsc VARCHAR(50) NOT NULL)");
			
			System.out.println("created");
			con.close();
		}catch (SQLException | ClassNotFoundException e) {
			System.out.println("create"+e);
		}
	}
}