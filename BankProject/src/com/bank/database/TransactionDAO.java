//$Id$
package com.bank.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.models.TransactionModel;
import com.bank.utils.Utils;

public class TransactionDAO {
	
	public List<TransactionModel> getTransactions(String accountNumber) {
		List<TransactionModel> transactions =new ArrayList<>();
		try {
			Connection con=Utils.getConnection();
			String query="select * from TransactionHistory where fromAccount = ?";
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,accountNumber);
			ResultSet rs=statement.executeQuery();
			con.close();
			while(rs.next()) {
				TransactionModel transaction = new TransactionModel(rs.getDate("date"), 
						rs.getString("fromAccount"), 
						rs.getString("toAccount"), 
						rs.getString("purpose"), 
						rs.getString("type"), 
						rs.getDouble("credit"), 
						rs.getDouble("debit"));
				transactions.add(transaction);					
			}
			return transactions;
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	public int insertTransaction(TransactionModel transaction) {
		int numOfRowsAffected = 0;
		try {
			Connection con = Utils.getConnection();
			String query = "insert into TransactionHistory (date,fromAccount,purpose,toAccount,type,debit,credit) values (?,?,?,?,?,?,?);";
			PreparedStatement statement=con.prepareStatement(query);
			statement.setDate(1, transaction.getDate());
			statement.setString(2, transaction.getFromAccount());
			statement.setString(3, transaction.getPurpose());
			statement.setString(4, transaction.getToAccount());
			statement.setString(5, transaction.getType());
			statement.setDouble(6, transaction.getDebitAmount());
			statement.setDouble(7, transaction.getCreditAmount());
			
			numOfRowsAffected=statement.executeUpdate();
			con.close();
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return numOfRowsAffected;
	}
}
