package com.sample;

public class StaticUsages {
	int j;
	  static int i;
	static {
		i=10;
	}
	public static void main(String[] args) {
		System.out.println("i = "+i);
		//j=10;
		
		Test.temp te=new Test.temp();
		te.display();
		Test.temp.display1();
		
	}
	void op(int j) {
		j=100;
	}
	
}

class Test{
	static class temp{
		void display() {
			System.out.println("Static class");
		}
		static void display1() {
			System.out.println("Static class MTD");
		}
	}
}
