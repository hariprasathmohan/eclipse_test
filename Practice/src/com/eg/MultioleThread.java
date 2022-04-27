//$Id$
package com.eg;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class MultioleThread {

	public static void main(String[] args) {
		ScheduledExecutorService ex=Executors.newScheduledThreadPool(10);
		
		ex.schedule(new Tester(), 10, TimeUnit.SECONDS);
		ex.scheduleAtFixedRate(new Tester(), 15, 10, TimeUnit.SECONDS);
		ex.scheduleWithFixedDelay(new Tester(), 15, 10, TimeUnit.SECONDS);
		//ex.shutdown();
		/*
		 * System.out.println(Runtime.getRuntime().availableProcessors()); for(int i=0;i<10;i++) { ex.execute(new Tester()); }
		 */
		System.out.println("current thread "+Thread.currentThread().getName());
	}

}
class Tester implements Runnable{

	@Override
	public void run() {
		System.out.println("current thread "+Thread.currentThread().getName());
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
}
