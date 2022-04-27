//$Id$
package com.sample;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBTEST {

	public static void main(String[] args) {
		DBTEST db = new DBTEST();
		db.dbCreate();
		System.out.println(db.insertTbl());
		System.out.println(db.select("'do' or '1'='1'"));

	}
	Connection getConnection() throws SQLException, ClassNotFoundException {
		Class.forName("org.postgresql.Driver");
		Connection con=DriverManager.getConnection("jdbc:postgresql://localhost:5432/Test",
				"postgres","123");
		return con;
	}
	void dbCreate() {
		try {
			Connection con = getConnection();
			con.createStatement().executeUpdate("DROP TABLE IF EXISTS Test");
			con.createStatement().executeUpdate("create table Test("
					+ "name VARCHAR(50) NOT NULL," 
					+ "address VARCHAR(250) NOT NULL)");
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	int insertTbl() {
		int numOfRowsAffected=0;
		String query="insert into Test(name,address) values(?,?)";
		
		try {
			Connection con = getConnection();
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,"hari");
			statement.setString(2,"test1");
			
			numOfRowsAffected=statement.executeUpdate();
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return numOfRowsAffected;
	}
	String select(String name) {
		try {
			Connection con = getConnection();
			String query="select * from Test where name = ?";
			
			  PreparedStatement statement = con.prepareStatement(query); 
			  statement.setString(1,name); 
			  ResultSet rs=statement.executeQuery();
			 
			//Statement st = con.createStatement();
			//ResultSet rs = st.executeQuery(query);
			while(rs.next()) {
				System.out.println("IN rs");
				return rs.getString("name");
			}
			con.close();
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}
