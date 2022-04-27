//$Id$
package com.bank.database;


public interface DatabaseOperations {
	public int updateBalance(String accountNumber,double balance);
	public int getSize();
	public double getBalance(String accountNumber);

}
