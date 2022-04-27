package com.sample;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class ExHandling {

	public static void main(String[] args) {
		/*
		 * TCF t=new TCF() ; t.run();
		 */
		String s = "I want to walk my dog, and! why not?";
		Pattern pattern = Pattern.compile("\\w+");
		Matcher matcher = pattern.matcher(s);
		while (matcher.find()) {
		    System.out.println(matcher.group());
		}try {
			run();
		} catch (NullPointerException e) {

			System.out.println("Exception caught here");
		}
		

		System.out.println("End of code");
	}
	static void run() {
	
		throw new NullPointerException();
	}
	
}

class TCF{
	void run() {
int i=0;
		
		InputStreamReader isr1 = new InputStreamReader(System.in);
		BufferedReader br2 = new BufferedReader(isr1);
		System.out.println("Enter value");
		try {
			i=Integer.parseInt(br2.readLine());
			System.out.println("i= "+i);
		} catch (NumberFormatException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			System.out.println("finally executed");						
		}
		
		System.out.println("End of code");
	}
}
