//$Id$
package com.eg;

public class Some {

	public static void main(String[] args) throws InterruptedException {
		
		
		System.out.println(Thread.currentThread().getThreadGroup().getParent().getName());
		
		
		Dummies t=new Dummies();
		t.start();
	//	Thread.sleep(10000);
		synchronized (t) {
			System.out.println("main start");
			t.wait(10000);
			System.out.println("main end");
			System.out.println(t.total);			
		}

	}

}
class Dummies extends Thread{
	int total=0;
	public void run() {
		
		System.out.println(Thread.currentThread().getThreadGroup().getName());
		synchronized (this) {
			System.out.println("child start");
		
		for(int i=1;i<=100;i++) {
			total+=i;
		}
		System.out.println("child end");
		this.notify();
		}
	}
}
