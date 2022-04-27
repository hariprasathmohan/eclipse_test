//$Id$
package com.bank.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.bank.models.UserModel;
import com.bank.utils.EncryptDecrypt;
import com.bank.utils.Utils;

public class UserDAO {
	
	public UserModel getUser(String userId) {
		UserModel user;
		try {
			Connection con= Utils.getConnection();
			String query="select * from Users where userId = ?";
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,userId);
			ResultSet rs=statement.executeQuery();
			con.close();
			if(rs.next()) {
				user = new UserModel(rs.getString("accountNumber"),
						rs.getString("userId"), 
						rs.getString("password"), 
						rs.getBoolean("isFirst"));
				return user;
			}
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println("user"+e.getMessage());
		}
		return null;
	}
	public int insertUser(UserModel user,String password) {
		int numOfRows = 0;
		try {
			Connection con = Utils.getConnection();
			String query="insert into Users (userId,password,isFirst,accountNumber) values (?,?,?,?);";
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,user.getUserId());
			statement.setString(2,password);
			statement.setBoolean(3, user.isFirst());
			statement.setString(4,user.getAccountNumber());
			
			numOfRows = statement.executeUpdate();
			con.close();
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return numOfRows;
	}
	public boolean validatePassword(String accountNumber,String password) {
		EncryptDecrypt encDec = new EncryptDecrypt();
		try {
			Connection con = Utils.getConnection();
			String query = "select password from Users where accountNumber = ?;";
			PreparedStatement statement = con.prepareStatement(query);
			statement.setString(1, accountNumber);
			ResultSet rs = statement.executeQuery();
			con.close();
			if(rs.next()) {
				if(password.equals(encDec.decrypt(rs.getString("password"))))
					return true;
			}
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
	public int updatePassword(String accountNumber , String password , boolean isFirst) {
		int numOfRowsAffected=0;
		try {
			String query = "update Users set password = ?,isFirst = ?  where accountNumber = ?;";
			Connection con=Utils.getConnection();
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1, password);
			statement.setBoolean(2, isFirst);
			statement.setString(3, accountNumber);
			
			numOfRowsAffected=statement.executeUpdate();
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		return numOfRowsAffected;
	}
}
