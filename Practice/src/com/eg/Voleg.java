//$Id$
package com.eg;
class App extends Thread{
	boolean counter;
	public void run() {
		counter=true;
		while(counter) System.out.println("RUNNING.................");
	}
	public void shutdown() {
		counter=false;
		System.out.println("STOPPED####################");
	}
}
public class Voleg {
	public static void main(String[] args) {
		
		App a=new App();
		
		Thread t1=new Thread(a);
		Thread t2= new Thread(a);
		t1.start();
		t2.start();
		((App) t1).shutdown();
		((App) t1).shutdown();
	}
}
