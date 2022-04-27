//$Id$
package com.eg;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

class Task implements Runnable   
{
    private String name;
    ThreadLocal<String> tl=new ThreadLocal<String>() { };
    public Task(String s)
    {
        name = s;
        Lock lock= new ReentrantLock();
        lock.lock();
        lock.unlock();
    }
    public void run()
    {
       try
        {
            for (int i = 0; i<=5; i++)
            {
                if (i==0) System.out.println("Initialization Time for"+" task name - "+ name +" = " +i);
                else System.out.println("Executing Time for task name - "+ name +" = " +i);  
                Thread.sleep(1000);
            }
            System.out.println(name+" complete");
        }
        catch(InterruptedException e) { e.printStackTrace(); }
    }
}
public class Testing
{    
    public static void main(String[] args)
    {    	
        Runnable r1 = new Task("task 1");
        Runnable r2 = new Task("task 2");
        Runnable r3 = new Task("task 3");
        Runnable r4 = new Task("task 4");
        Runnable r5 = new Task("task 5"); 
        Runnable r6 = new Task("task 6"); 
        Runnable r7 = new Task("task 7"); 
        Runnable r8 = new Task("task 8"); 
        Runnable r9 = new Task("task 9"); 
          
        ExecutorService pool = Executors.newFixedThreadPool(10);  
         
        pool.execute(r1);
        pool.execute(r2);
        pool.execute(r3);
        pool.execute(r4);
        pool.execute(r5);
        pool.execute(r6);
        pool.execute(r7);
        pool.execute(r8);
        pool.execute(r9);
          
        pool.shutdown();    
    }
}