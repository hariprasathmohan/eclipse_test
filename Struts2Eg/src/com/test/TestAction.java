//$Id$
package com.test;

import com.opensymphony.xwork2.ActionSupport;

public class TestAction extends ActionSupport {
	private static final long serialVersionUID = 1L;
	
	
	private String name;
	
	
	  public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String execute() throws Exception {
		Thread.sleep(3000);
		System.out.println("Inside action....");
		
		return SUCCESS;
	}
	  
	

}
