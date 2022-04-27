//$Id$
package com.eg;

public class TOSTRING {

	public static void main(String[] args) {
		Dummy d= new Dummy();
		d.stringBuffeg();
		
	}

}
class MyToString extends Object{

	@Override
	public String toString() {
		String s= "hai......";
		return s;
		
	}
}
class Dummy{
	void getBytesEg() {
		byte[] b="hello".getBytes();	
		System.out.println(new String(b));
	}
	void chararreg() {
		MyToString d=new MyToString();
		char[] c= {'h','a','i'};
		String s="hello";
		String s1="HELLO";
		//System.out.println(new String(c,2,1));//offset starting point, length
		System.out.println(s.charAt(3));
		System.out.println(s.codePointAt(4));
		System.out.println(s.codePointBefore(3));
		System.out.println(s.codePointCount(0, 3));
		System.out.println(s.compareTo("elloh")			); //returns the number or
		System.out.println(s.concat("hai")		);
		System.out.println(s.contains("hol")		);
		System.out.println(s.contentEquals("hello")		);
		System.out.println(s.copyValueOf(c)		);
		System.out.println(s		);
		System.out.println(s.endsWith("lo")		);
		System.out.println(s.equalsIgnoreCase(s1)		);
		System.out.println(s1.intern()		);
		System.out.println(s.lastIndexOf("lo")		);
		System.out.println(s.replace('l', 'z')		);
		System.out.println(s1.toLowerCase()		);
		System.out.println(s.valueOf(d)		);
	}
	void stringBuffeg() {
		StringBuffer sb=new StringBuffer("hello");
		boolean b=false;
		MyToString d=new MyToString();
		char[] c= {'h','a','i'};
		
		sb.trimToSize();
		System.out.println(sb.append(d)		);
		System.out.println(sb.capacity()		);
		System.out.println(sb.delete(0, 2)		);
		System.out.println(sb.insert(0, c)		);
		
	}
}






