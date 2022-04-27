//add two String,int,double
package com.sample;

public class PolyMorph {

	public static void main(String[] args) {
		PolyMorph o=new PolyMorph();
		System.out.println(o.add(2, 3));
		System.out.println(o.add(2.57, 3.16));
		System.out.println(o.add("hai", " Hari"));
		System.out.println(o.add());
		
		Test1 obj=new Test1();
		Test2 obj1=new Test2();
		Test3 obj2=new Test3();
		int a=2;
		int b=2;
		System.out.println(obj.add(a, b));
		System.out.println(obj1.add(a, b));
		System.out.println(obj2.add(a, b));
	}
	int add(int a,int b) {
		return a+b;
	}
	int add() {
		return 10;
	}
	double add(double a,double b) {
		return a+b;
	}
	String add(String a,String b) {
		return a+b;
	}

}
interface Testing{
	int add(int a,int b);
}
class Test1 implements Testing{

	@Override
	public int add(int a, int b) {
		// TODO Auto-generated method stub
		return a+b;
	}
	
}
class Test2 implements Testing{

	@Override
	public int add(int a, int b) {
		// TODO Auto-generated method stub
		return a*a+b*b;
	}
	
}
class Test3 implements Testing{

	@Override
	public int add(int a, int b) {
		// TODO Auto-generated method stub
		return a*b+b*a;
	}
	
}
