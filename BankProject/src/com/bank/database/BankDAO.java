//$Id$
package com.bank.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.bank.models.BankModel;
import com.bank.utils.Utils;

public class BankDAO {
	public List<String> getBankList(){
		List<String> bank = new ArrayList<>();
		try {
			Connection con = Utils.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select distinct bankName from Bank");
			con.close();
			while(rs.next()) {
				bank.add(rs.getString("bankName"));
			}
			return bank;
			
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	public List<BankModel> getBranchs(String Bank){
		List<BankModel> branches = new ArrayList<>();
		try {
			Connection con = Utils.getConnection();
			String query="select * from Bank where bankName = ?";
			PreparedStatement st=con.prepareStatement(query);
			st.setString(1, Bank);
			ResultSet rs=st.executeQuery();
			con.close();
			while(rs.next()) {
				BankModel bank = new BankModel(rs.getString("bankName"), 
						rs.getString("branchName"), 
						rs.getString("code"), 
						rs.getString("ifsc"));
				branches.add(bank);
			}
			return branches;
			
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	public BankModel getBankDetails(String branchCode) {
		try {
			Connection con = Utils.getConnection();
			String query="select * from Bank where code = ?";
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,branchCode);
			ResultSet rs=statement.executeQuery();
			con.close();
			if(rs.next())
				return new BankModel(rs.getString("bankName"), rs.getString("branchName"), rs.getString("code"), rs.getString("ifsc"));
		}catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	public int insertBranch(BankModel bank) {
		int numOfRowsAffected=0;
		String query="insert into Bank (bankName,code,branchName,ifsc) values(?,?,?,?)";
		try {
			
			Connection con =Utils.getConnection();
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,bank.getName());
			statement.setString(2,bank.getCode());
			statement.setString(3,bank.getBranchName());
			statement.setString(4,bank.getIfsc());
			
			numOfRowsAffected=statement.executeUpdate();
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("insert"+e);
		}
		return numOfRowsAffected;
	}
}
