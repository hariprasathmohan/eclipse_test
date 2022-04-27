//$Id$
package com.eg;

public class SuperExample {

	public static void main(String[] args) {
		SubCls o=new SubCls(10);
		o.op();
	}

}
class SuperCls{
	
	public SuperCls(int i) {
		i=10;
		
	}
	void print() {
		System.out.println("im from super class");
	}
}
class SubCls extends SuperCls{
	
	public SubCls(int i) {
		super(i);
	}
	void print() {
		System.out.println("im from sub class");
	}
	void op() {
		super.print();
		print();
	}
}