//$Id$
package com.bank.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bank.models.OtherDepositModel;
import com.bank.models.TransactionModel;
import com.bank.utils.Utils;

public class RecurringDepositDAO implements DatabaseOperations {
	
	public List<OtherDepositModel> getAccounts(String accountNumber) {
		
		List<OtherDepositModel> accounts = new ArrayList<>();
		try {
			Connection con = Utils.getConnection();
			String query="select * from RecurringDeposit where accountNumber = ? and balance > 0";
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,accountNumber);
			ResultSet rs=statement.executeQuery();
			con.close();
			while(rs.next()) {
				OtherDepositModel rdModel = new OtherDepositModel(rs.getString("accountNumber"), 
						rs.getString("rdAccountNumber"), 
						rs.getDouble("balance"), 
						rs.getDate("createdAt"), 
						rs.getDate("updatedAt"), 
						rs.getDouble("interest"), 
						rs.getInt("numberOfMonths"), 
						rs.getDouble("amount"),
						"RD");
				accounts.add(rdModel);
			}
			return accounts;
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return accounts;
	}
	
	public OtherDepositModel getAccountDetails(String accountNumber) {
		try {
			//System.out.println(accountNumber);
			Connection con = Utils.getConnection();
			String query="select * from RecurringDeposit where rdAccountNumber = ?";
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,accountNumber);
			ResultSet rs=statement.executeQuery();
			con.close();
			//System.out.println(rs);
			if(rs.next()) {
				OtherDepositModel fdModel = new OtherDepositModel(rs.getString("accountNumber"),
						rs.getString("rdAccountNumber"),  
						rs.getDouble("balance"), 
						rs.getDate("createdAt"), 
						rs.getDate("updatedAt"), 
						rs.getDouble("interest"), 
						rs.getInt("numberOfMonths"), 
						rs.getDouble("amount"),
						"RD");
				return fdModel;
			}
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	@Override
	public int updateBalance(String accountNumber, double balance) {
		int numOfRowsAffected=0;
		String query="update RecurringDeposit set balance = ?, updatedAt = ? where rdAccountNumber = ?";
		try {
			Connection con=Utils.getConnection();
			PreparedStatement statement=con.prepareStatement(query);
			statement.setDouble(1, balance);
			statement.setDate(2, Utils.curr_date());
			statement.setString(3, accountNumber);
			
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
			String query="select balance from RecurringDeposit where rdAccountNumber = ?";
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

	public int insertRecurringAccountDetails(OtherDepositModel odAccount) {
		int numOfRowsAffected=0;
		String query="insert into RecurringDeposit (accountNumber,rdAccountNumber,createdAt,updatedAt,interest,balance,amount,numberOfMonths) values(?,?,?,?,?,?,?,?)";
		try {
			Connection con=Utils.getConnection();
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,odAccount.getAccountNumber());
			statement.setString(2,odAccount.getDepositAccountNumber());
			statement.setDate(3,odAccount.getDate());
			statement.setDate(4,odAccount.getUpdatedAt());
			statement.setDouble(5,odAccount.getInterest());
			statement.setDouble(6,odAccount.getBalance());
			statement.setDouble(7,odAccount.getDepositAmount());
			statement.setDouble(8,odAccount.getNoOfMonths());
			
			numOfRowsAffected=statement.executeUpdate();
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("insert"+e);
		}
		return numOfRowsAffected;
	}
	
	 public void autoUpdate() {
		 Statement st;
			try {
				Connection con=Utils.getConnection();
				st=con.createStatement();
				ResultSet rs=st.executeQuery("select * from RecurringDeposit");
				TransactionDAO transactiondb = new TransactionDAO();
				while(rs.next()) {
					int monthDifference = Utils.dateDiff(rs.getDate("updatedAt"));
					if(monthDifference>0) {
						double balance=rs.getDouble("balance");
						for(int i = 1;i <= monthDifference;i++) {
							double amount = (balance*(rs.getDouble("interest")/100) )+ rs.getDouble("amount");
							balance+=amount ;
							TransactionModel transaction = new TransactionModel(Utils.curr_date(), rs.getString("rdAccountNumber"), "BANK", "Interest", "RD", amount, 0);
							transactiondb.insertTransaction(transaction);
							
							String query="update RecurringDeposit set balance = ?, updatedAt = ? where rdAccountNumber = ?";
							PreparedStatement statement=con.prepareStatement(query);
							statement.setDouble(1, balance);
							statement.setDate(2, Utils.curr_date());
							statement.setString(3, rs.getString("rdAccountNumber"));
							statement.executeUpdate();
							
							AccountDao accountdb = new AccountDao();
							double bal = accountdb.getBalance(rs.getString("accountNumber")) - rs.getDouble("amount");
							TransactionModel transaction1 = new  TransactionModel(Utils.curr_date(), rs.getString("accountNumber"), rs.getString("rdAccountNumber"), "RD", "RD", 0, rs.getDouble("amount"));
							transactiondb.insertTransaction(transaction1);
							accountdb.updateBalance(rs.getString("accountNumber"), bal);
						}
					}
				}
			} catch (SQLException | ClassNotFoundException e) {
				System.out.println(e.getMessage());
			}
	 }
	 
	@Override
	public int getSize() {
		int size = 0;
		try {
			Connection con=Utils.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) as size from RecurringDeposit");
			rs.next();
			size=rs.getInt("size");
			con.close();
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return size;
	}
}
