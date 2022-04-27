//$Id$
package com.bank.models;

public class Address {
	private String addressLine1;
	private String addressLine2;
	private String state;
	private String city;
	private String address;
	public Address(String addressLine1, String addressLine2, String state, String city) {
		super();
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.state = state;
		this.city = city;
	}
	public Address(String address) {
		this.address=address;
	}
	@Override
	public String toString() {
		if(address != null) return address;
		else return this.addressLine1 +","+this.addressLine2+","+this.state+","+this.city;
	}
}