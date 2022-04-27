//$Id$
package com.eg;

public class SyncEg {

	public static void main(String[] args) {
		Print p=new Print();
		
		Thread t1=new Thread(){
			public void run() {
				p.print(1);
			}
		};
		Thread t2=new Thread(){
			public void run() {
				Print p1=new Print();
				p1.print(2);
				//p.printing(2);
			}
		};
		t1.start();
		
		t2.start();

	}

}

class Print{
	
	static synchronized void print(int data) {
		for(int i=1;i<=10;i++) {
			System.out.println("calling print"+ data);
			
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
		synchronized void printing(int data) {
			for(int i=1;i<=10;i++) {
				System.out.println("calling printing"+ data);
				
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	}
}
