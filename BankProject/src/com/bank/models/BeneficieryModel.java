//$Id$
package com.bank.models;

public class BeneficieryModel extends AccountModel{
	private String nickName;
	private String beneAccNo;
	private String ifsc;
	
	public BeneficieryModel(String accountNumber, String nickName, String beneAccNo, String ifsc) {
		super(accountNumber);
		this.nickName = nickName;
		this.beneAccNo = beneAccNo;
		this.ifsc = ifsc;
	}
	
	public String getNickName() {
		return nickName;
	}
	public void setNickName(String nickName) {
		this.nickName = nickName;
	}
	public String getBeneAccNo() {
		return beneAccNo;
	}
	public void setBeneAccNo(String beneAccNo) {
		this.beneAccNo = beneAccNo;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
}
