//$Id$
package com.sample;
interface i{
	default void show() {
		System.out.println("i show");
	}
}
interface j{
	default void show() {
		System.out.println("j show");
	}
	
}
class Tests implements i,j{

	@Override
	public void show() {
		// TODO Auto-generated method stub
		//i.super.show();
		System.out.println("show");
	}

	/*
	 * @Override public void show() { System.out.println("show called");
	 * 
	 * }
	 */
	
}
public class InterfaceTest {

	public static void main(String[] args) {
		Tests t=new Tests();
		t.show();
	}

}
