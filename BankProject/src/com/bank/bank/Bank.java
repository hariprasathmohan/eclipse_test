//$Id$
package com.bank.bank;

import com.bank.database.BankDAO;
import com.bank.models.BankModel;

public class Bank {
	public Bank() {
		BankDAO bankdb = new BankDAO();
		BankModel branch1= new BankModel("SBI", "MDU", "11", "SBI11");
		BankModel branch2= new BankModel("SBI", "CBE", "12", "SBI12");
		BankModel branch3= new BankModel("SBI", "TSI", "13", "SBI13");
		BankModel branch4= new BankModel("HDFC", "MDU", "21", "HDFC21");
		BankModel branch5= new BankModel("HDFC", "CBE", "22", "HDFC22");
		BankModel branch6= new BankModel("HDFC", "TSI", "23", "HDFC23");
		BankModel branch7= new BankModel("TMB", "MDU", "31", "TMB31");
		BankModel branch8= new BankModel("TMB", "CBE", "31", "TMB32");
		BankModel branch9= new BankModel("TMB", "TSI", "33", "TMB33");

		bankdb.insertBranch(branch1);
		bankdb.insertBranch(branch2);
		bankdb.insertBranch(branch3);
		bankdb.insertBranch(branch4);
		bankdb.insertBranch(branch5);
		bankdb.insertBranch(branch6);
		bankdb.insertBranch(branch7);
		bankdb.insertBranch(branch8);
		bankdb.insertBranch(branch9);
		
		System.out.println("Inserted");
	}
}
