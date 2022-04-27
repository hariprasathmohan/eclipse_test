//$Id$
package com.bank.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.bank.models.BeneficieryModel;
import com.bank.utils.Utils;

public class BeneficieryDAO {
	
	public int insertBeneficiery(BeneficieryModel beneficiery) {
		int numOfRowsAffected=0;
		String query="insert into Beneficiery(accountNumber,nickName,beneAccNo,ifsc) values(?,?,?,?)";
		try {
			Connection con=Utils.getConnection();
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,beneficiery.getAccountNumber());
			statement.setString(2,beneficiery.getNickName());
			statement.setString(3,beneficiery.getBeneAccNo());
			statement.setString(4,beneficiery.getIfsc());
			
			numOfRowsAffected=statement.executeUpdate();
			con.close();
		} catch (SQLException | ClassNotFoundException e) {
			System.out.println("insert"+e);
		}
		return numOfRowsAffected;
	}
	public List<BeneficieryModel> getBeneficiery(String accountNumber){
		List<BeneficieryModel> accounts = new ArrayList<>();
		try {
			Connection con = Utils.getConnection();
			String query="select * from Beneficiery where accountNumber = ?";
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,accountNumber);
			ResultSet rs=statement.executeQuery();
			con.close();
			while(rs.next()) {
				BeneficieryModel beneModel = new BeneficieryModel(rs.getString("accountNumber"), 
						rs.getString("nickName"), 
						rs.getString("beneAccNo"), 
						rs.getString("ifsc"));
						
				accounts.add(beneModel);
			}
			return accounts;
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	public int updateBeneficiery(BeneficieryModel bene) {
		int numOfRowsAffected=0;
		String query = "update Beneficiery set nickName = ?,beneAccNo = ?,ifsc = ? where accountNumber =";
		try {
			Connection con = Utils.getConnection();
			PreparedStatement statement=con.prepareStatement(query);
			statement.setString(1,bene.getNickName());
			statement.setString(2,bene.getBeneAccNo());
			statement.setString(3,bene.getIfsc());
			statement.setString(4,bene.getAccountNumber());
			
			numOfRowsAffected=statement.executeUpdate();
			con.close();
		} catch(SQLException | ClassNotFoundException e) {
			System.out.println(e.getMessage());
		}
		return numOfRowsAffected;
	}
}
