//$Id$
package com.bank.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;

public class Utils {
	public static double FD_INTEREST;
	public static double RD_INTEREST;
	public static double LOAN_INTEREST;
	public static double ACCOUNT_INTEREST;
	public static final int MIN_BAL = 1000;
	public static String connectionClass;
	public static String connectionUrl;
	public static String connectionUsername;
	public static String connectionPassword;
	
	public static Connection getConnection() throws SQLException, ClassNotFoundException {
			Class.forName(connectionClass);
			Connection con=DriverManager.getConnection(connectionUrl,connectionUsername,connectionPassword);
			return con;
	}
	public static Date curr_date() {
		try {
			Connection con=getConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("SELECT LOCALTIMESTAMP :: Date as currDate");
			rs.next();
			con.close();
			return rs.getDate("currDate");
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	public static int dateDiff(Date startDate) {
		int diffMonths = 0;
		try {
			Connection con = getConnection();
			String query = "SELECT (DATE_PART('year', ?::date) - DATE_PART('year',?::date)) * 12 +" + 
					"(DATE_PART('month', ?::date) - DATE_PART('month', ?::date)) as monthDiff;";
			PreparedStatement statement=con.prepareStatement(query);
			statement.setDate(1, curr_date());
			statement.setDate(2, startDate);
			statement.setDate(3, curr_date());
			statement.setDate(4, startDate);
			ResultSet rs=statement.executeQuery();
			con.close();
			if(rs.next()) return rs.getInt("monthDiff");
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());;
		}
		return diffMonths;
	}
}
