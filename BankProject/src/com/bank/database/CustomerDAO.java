//$Id$
package com.bank.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.bank.models.Address;
import com.bank.models.CustomerModel;
import com.bank.utils.Utils;

public class CustomerDAO {
	
	public CustomerModel getCustomerDetails(int customerId) {
		CustomerModel customer;
		try {
			Connection con = Utils.getConnection();
			String query="select * from Customer where id = ?";
			PreparedStatement statement=con.prepareStatement(query);
			statement.setInt(1,customerId);
			ResultSet rs=statement.executeQuery();
			con.close();
			if(rs.next()) {
				Address address = new Address(rs.getString("address"));
				customer = new CustomerModel(rs.getString("name"), 
						address,
						rs.getString("email"), 
						rs.getString("mobile"));
				return customer;
			}
			
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	public int insertCustomerDetails(CustomerModel customer) {
		int numOfRowsAffected=0;
		String query="insert into Customer (name,address,email,mobile) values(?,?,?,?) RETURNING id";
		try {
			Connection con=Utils.getConnection();
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,customer.getName());
			statement.setString(2,customer.getAddress());
			statement.setString(3,customer.getEmail());
			statement.setString(4,customer.getMobile());
			
			ResultSet rs = statement.executeQuery();
			rs.next();
			numOfRowsAffected=rs.getInt("id");
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("insert"+e);
		}
		return numOfRowsAffected;
	}
	public int getSize() {
		int size = 0;
		try {
			Connection con=Utils.getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select count(*) as size from Customer");
			rs.next();
			size=rs.getInt("size");
			con.close();
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return size;
	}
	
	public boolean isEmailAvailable(String email) {
		String query="select * from Customer where email = ?";
		try {
			Connection con = Utils.getConnection();
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,email);
			ResultSet rs=statement.executeQuery();
			con.close();
			return rs.next();
		}catch (SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return false;
	}
}
