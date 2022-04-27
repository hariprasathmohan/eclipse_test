//$Id$
package com.bank.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bank.models.AccountModel;
import com.bank.models.TransactionModel;
import com.bank.utils.Utils;

public class AccountDao implements DatabaseOperations {
	
	
	public int insertAccountDetails(AccountModel account) {
		int numOfRowsAffected=0;
		String query="insert into Account(accountNumber,branchCode,customerId,interest,balance, type) values(?,?,?,?,?,?)";
		try {
			Connection con=Utils.getConnection();
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,account.getAccountNumber());
			statement.setString(2,account.getBranchCode());
			statement.setInt(3,account.getCustomerId());
			statement.setDouble(4, Utils.ACCOUNT_INTEREST);
			statement.setDouble(5, account.getBalance());
			statement.setString(6,account.getAccountType());
			
			numOfRowsAffected=statement.executeUpdate();
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("insert"+e);
		}
		return numOfRowsAffected;
	}

	@Override
	public int updateBalance(String accountNumber, double balance) {
		int numOfRowsAffected=0;
		String query="update Account set balance = ? where accountNumber = ?";
		try {
			Connection con=Utils.getConnection();
			PreparedStatement statement=con.prepareStatement(query);
			statement.setDouble(1, balance);
			statement.setString(2, accountNumber);
			
			numOfRowsAffected=statement.executeUpdate();
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return numOfRowsAffected;
	}
	
	public AccountModel getAccountDetails(String accountNumber) {
		AccountModel account;
		try {
			Connection con = Utils.getConnection();
			String query="select * from Account where accountNumber = ?";
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,accountNumber);
			ResultSet rs=statement.executeQuery();
			con.close();
			if(rs.next()) {
				account  = new AccountModel(rs.getString("accountNumber"),
						rs.getInt("customerId"), 
						rs.getString("type"), 
						rs.getString("branchCode"), 
						rs.getDouble("balance"));
				return account;
			}
			
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public double getBalance(String accountNumber) {
		String query="select balance from Account where accountNumber = ?";
		PreparedStatement statement;
		try {
			Connection con = Utils.getConnection();
			statement = con.prepareStatement(query);
			statement.setString(1,accountNumber);
			ResultSet rs=statement.executeQuery();
			rs.next();
			con.close();
			return rs.getDouble("balance");
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}
	
	public void autoUpdate() {
		try {
			Connection con = Utils.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from Account");
			TransactionDAO transactiondb = new TransactionDAO();
			while(rs.next()) {
				double balance=rs.getDouble("balance");
				double amount ;
				String reason;
				
				if(balance < Utils.MIN_BAL) {
					amount = 14;
					balance -= amount;
					reason = "Low balance";
				}
				else {
					amount = balance*(rs.getDouble("interest")/100);
					balance += amount;
					reason = "Interest";
				}
				TransactionModel transaction = new TransactionModel(Utils.curr_date(), rs.getString("accountNumber"), "BANK", reason, rs.getString("type"), amount, 0);
				transactiondb.insertTransaction(transaction);
				updateBalance(rs.getString("accountNumber"), balance);
			}
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
	}

	@Override
	public int getSize() {
		int size = 0;
		try {
			Connection con=Utils.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) as size from Account");
			rs.next();
			size=rs.getInt("size");
			con.close();
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return size;
	}
}
