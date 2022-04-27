package com.example;

public class AccesMod {
	int i=5;
	public int j=6;
	private int k=7;
	protected int l=8;
	public static void main(String[] args) {
		/*
		 * AccesMod o=new AccesMod(); o.mtd();
		 * System.out.println("i= "+o.i+"\nj= "+o.j+"\nk= "+o.k+"\nl= "+o.l);
		 */
		Test test = new Test();
		test.mtd();
	}
	
	void mtd() {
		System.out.println("inside class");
		System.out.println("i= "+i+"\nj= "+j+"\nk= "+k+"\nl= "+l);
	}

}
class Test{
	void mtd() {
		AccesMod o=new AccesMod();
		o.mtd();
		System.out.println("outside class");
		System.out.println("i= "+o.i+"\nj= "+o.j+"\nk= **private variable** "+"\nl= "+o.l);//private variable is not accessed here.
	}
}
