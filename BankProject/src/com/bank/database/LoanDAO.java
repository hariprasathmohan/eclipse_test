//$Id$
package com.bank.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bank.models.LoanModel;
import com.bank.models.TransactionModel;
import com.bank.utils.Utils;

public class LoanDAO implements DatabaseOperations {
	
	
	public List<LoanModel> getAccounts(String accountNumber) {
		List<LoanModel> accounts = new ArrayList<>();
		try {
			Connection con = Utils.getConnection();
			String query="select * from Loan where accountNumber = ? and balance > 0 and status <> 'pending'";
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,accountNumber);
			ResultSet rs=statement.executeQuery();
			con.close();
			while(rs.next()) {
				LoanModel loanModel = new LoanModel(rs.getString("accountNumber"),
						rs.getString("loanNumber"),
						rs.getString("status"),
						rs.getDouble("interest"),
						rs.getDouble("amount"),
						rs.getDouble("balance"),
						rs.getDate("createdAt"), 
						rs.getDate("updatedAt"),
						rs.getString("type"),
						rs.getString("purpose"));					
				accounts.add(loanModel);
			}
			return accounts;
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	public LoanModel getLoanAccountDetails(String accountNumber) {
		try {
			Connection con = Utils.getConnection();
			String query="select * from Loan where loanNumber = ? and status <> 'pending'";
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,accountNumber);
			ResultSet rs=statement.executeQuery();
			con.close();
			if(rs.next()) {
				LoanModel loanModel = new LoanModel(rs.getString("accountNumber"),
						rs.getString("loanNumber"),
						rs.getString("status"),
						rs.getDouble("interest"),
						rs.getDouble("amount"),
						rs.getDouble("balance"),
						rs.getDate("createdAt"),
						rs.getDate("updatedAt"),
						rs.getString("type"),
						rs.getString("purpose"));	
				return loanModel;
			}
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public int updateBalance(String accountNumber, double balance) {
		int numOfRowsAffected=0;
		String query="update Loan set balance = ? where loanNumber = ?";
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

	@Override
	public double getBalance(String accountNumber) {
		try {
			Connection con=Utils.getConnection();
			String query="select balance from Loan where loanNumber = ? and status <> 'pending'";
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,accountNumber);
			ResultSet rs=statement.executeQuery();
			rs.next();
			con.close();
			return rs.getDouble("balance");
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return 0;
	}
	public int insertLoanAccount(LoanModel loan) {
		int numOfRowsAffected = 0;
		try {
			Connection con = Utils.getConnection();
			String query = "insert into Loan (accountNumber,loanNumber,createdAt,updatedAt,interest,balance,amount,status,type,purpose) values(?,?,?,?,?,?,?,?,?,?);";
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1, loan.getAccountNumber());
			statement.setString(2,loan.getLoanAccountNumber());
			statement.setDate(3,loan.getCreatedAt());
			statement.setDate(4,loan.getUpdatedAt());
			statement.setDouble(5,loan.getInterest());
			statement.setDouble(6,loan.getBalance());
			statement.setDouble(7,loan.getAmount());
			statement.setString(8,loan.getStatus());
			statement.setString(9,loan.getLoanType());
			statement.setString(10,loan.getLoanPurpose());
			
			numOfRowsAffected = statement.executeUpdate();
			con.close();
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return numOfRowsAffected;
	}
	
	public void autoUpdate() {
		Statement st;
		try {
			Connection con=Utils.getConnection();
			st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from Loan");
			TransactionDAO transactiondb = new TransactionDAO();
			while(rs.next()) {
				int monthDifference = Utils.dateDiff(rs.getDate("updatedAt"));
				if(monthDifference>0) {
					double balance=rs.getDouble("balance");					
					for(int i = 1;i <= monthDifference;i++) {
						double amount = balance*(rs.getDouble("interest")/100);
						balance+=amount;
						TransactionModel transaction = new TransactionModel(Utils.curr_date(), rs.getString("loanNumber"), "Loan", "Interest", "FD", amount, 0);
						transactiondb.insertTransaction(transaction);
						
						String query="update Loan set balance = ?, updatedAt = ? where loanNumber = ?";
						PreparedStatement statement=con.prepareStatement(query);
						statement.setDouble(1, balance);
						statement.setDate(2, Utils.curr_date());
						statement.setString(3, rs.getString("loanNumber"));
						statement.executeUpdate();
					}
				}
			}
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public int getSize() {
		int size = 0;
		try {
			Connection con=Utils.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) as size from Loan");
			rs.next();
			size=rs.getInt("size");
			con.close();
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return size;
	}
}
