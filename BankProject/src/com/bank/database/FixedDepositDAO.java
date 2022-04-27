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

public class FixedDepositDAO implements DatabaseOperations {
	
	public List<OtherDepositModel> getAccounts(String accountNumber) {
		List<OtherDepositModel> accounts = new ArrayList<>();
		try {
			Connection con = Utils.getConnection();
			String query="select * from FixedDeposit where accountNumber = ? and balance > 0";
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,accountNumber);
			ResultSet rs=statement.executeQuery();
			con.close();
			while(rs.next()) {
				OtherDepositModel fdModel = new OtherDepositModel(rs.getString("accountNumber"), 
						rs.getString("fdAccountNumber"), 
						rs.getDouble("balance"), 
						rs.getDate("createdAt"), 
						rs.getDate("updatedAt"), 
						rs.getDouble("interest"), 
						rs.getInt("numberOfMonths"), 
						rs.getDouble("amount"),
						"FD");
				accounts.add(fdModel);
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
			String query="select * from FixedDeposit where fdAccountNumber = ?";
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,accountNumber);
			ResultSet rs=statement.executeQuery();
			con.close();
			//System.out.println(rs);
			if(rs.next()) {
				OtherDepositModel fdModel = new OtherDepositModel(rs.getString("accountNumber"),
						rs.getString("fdAccountNumber"),  
						rs.getDouble("balance"), 
						rs.getDate("createdAt"), 
						rs.getDate("updatedAt"), 
						rs.getDouble("interest"), 
						rs.getInt("numberOfMonths"), 
						rs.getDouble("amount"),
						"FD");
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
		String query="update FixedDeposit set balance = ? where fdAccountNumber = ?";
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
			Connection con = Utils.getConnection();
			String query="select balance from FixedDeposit where fdAccountNumber = ?";
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
	public int insertFixedAccountDetails(OtherDepositModel fdAccount) {
		int numOfRowsAffected=0;
		String query="insert into FixedDeposit (accountNumber,fdAccountNumber,createdAt,updatedAt,interest,balance,amount,numberOfMonths) values(?,?,?,?,?,?,?,?)";
		try {
			Connection con=Utils.getConnection();
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,fdAccount.getAccountNumber());
			statement.setString(2,fdAccount.getDepositAccountNumber());
			statement.setDate(3,Utils.curr_date());
			statement.setDate(4,Utils.curr_date());
			statement.setDouble(5,fdAccount.getInterest());
			statement.setDouble(6,fdAccount.getBalance());
			statement.setDouble(7,fdAccount.getDepositAmount());
			statement.setDouble(8,fdAccount.getNoOfMonths());
			
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
			ResultSet rs=st.executeQuery("select * from FixedDeposit");
			TransactionDAO transactiondb = new TransactionDAO();
			while(rs.next()) {
				int monthDifference = Utils.dateDiff(rs.getDate("updatedAt"));
				if(monthDifference>0) {
					double balance=rs.getDouble("balance");					
					for(int i = 1;i <= monthDifference;i++) {
						double amount = balance*(rs.getDouble("interest")/100);
						balance+=amount;
						TransactionModel transaction = new TransactionModel(Utils.curr_date(), rs.getString("fdAccountNumber"), "BANK", "Interest", "FD", amount, 0);
						transactiondb.insertTransaction(transaction);
						
						String query="update FixedDeposit set balance = ?, updatedAt = ? where fdAccountNumber = ?";
						PreparedStatement statement=con.prepareStatement(query);
						statement.setDouble(1, balance);
						statement.setDate(2, Utils.curr_date());
						statement.setString(3, rs.getString("fdAccountNumber"));
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
			ResultSet rs=st.executeQuery("select count(*) as size from FixedDeposit");
			rs.next();
			size=rs.getInt("size");
			con.close();
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return size;
	}
}
