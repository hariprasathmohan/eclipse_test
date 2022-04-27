//$Id$
package com.eg;



class T1 extends Thread{
	public void run() {
		try {
		for(int i=1;i<=30;i++) {
			
		System.out.println("t1 is running "+i);
		Thread.yield();
				Thread.sleep(1000);
		}
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("blocked");
			}
		
		
	}
	
}

class T2 extends Thread{

	@Override
	public void run() {
		for(int i=1;i<=10;i++) {
		System.out.println("t2 is running "+i);
		//this.setPriority(1);
		}
		
	}
	
}
class T3 implements Runnable{

	@Override
	public void run() {
		System.out.println("runnable run"+Thread.currentThread().getPriority());
		
	}
	
}




public class ThreadExample {
	public static void main(String[] args) {
		/*
		 * System.out.println(Thread.MAX_PRIORITY); Thread.currentThread().setName("hari"); System.out.println(Thread.currentThread().getName()); // int a=1/0; T1 t1=new T1(); //t1.setPriority(100);
		 * //Thread.sleep(1000); //T2 t2=new T2(); Thread t3=new Thread(new T3()); Thread.currentThread().setPriority(9); T2 t2=new T2(); System.out.println(t2.getPriority()+"  "+t1.getPriority());
		 * t3.start(); //t2.setPriority(10); //t1.setPriority(1); //t1.setDaemon(true); t1.start(); //t1.join(); //t2.start();
		 * 
		 */
		
		T1 t1=new T1();
		//t1.setPriority(10);
		t1.start();
		
		//t1.interrupt();
		T2 t2=new T2();
		t2.start();
		try {
			t1.join(10000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		  for(int i=1;i<=10;i++) { System.out.println("main running "+i); }
		 
	}
}
