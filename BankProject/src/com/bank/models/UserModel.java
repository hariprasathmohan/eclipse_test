//$Id$
package com.bank.models;

public class UserModel extends AccountModel {
	private String userId;
	private String password;
	private boolean isFirst;
	
	public UserModel(String accountNumber, String userId, String password, boolean isFirst) {
		super(accountNumber);
		this.userId = userId;
		this.password = password;
		this.isFirst = isFirst;
	}
	public UserModel() {
		super();
	}
	
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public boolean validatePassword(String password) {
		if(this.password.equals(password))
			return true;
		return false;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public boolean isFirst() {
		return isFirst;
	}
	public void setFirst(boolean isFirst) {
		this.isFirst = isFirst;
	}
}
