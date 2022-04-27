//$Id$
package com.example;

public class StsticTest {

	public static void main(String[] args) {
		int i=Some.n;
		i=6;
		System.out.println(Some.n);

	}

}
class Some{
	public static int n=5;
}
