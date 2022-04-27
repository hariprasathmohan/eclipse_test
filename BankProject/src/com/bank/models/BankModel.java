//$Id$
package com.bank.models;

public class BankModel {
	private String name;
	private String branchName;
	private String code;
	private String ifsc;
	public BankModel(String name, String branchName, String code, String ifsc) {
		super();
		this.name = name;
		this.branchName = branchName;
		this.code = code;
		this.ifsc = ifsc;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getIfsc() {
		return ifsc;
	}
	public void setIfsc(String ifsc) {
		this.ifsc = ifsc;
	}
}
