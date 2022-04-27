package com.example;

import java.util.concurrent.*;
import java.util.*;
  
class SchedulerExecutorServiceExample {
    
    public static void main(String[] args)
    {
        System.out.println(
            "A count-down-clock program that counts from 10 to 0");
  
        // creating a ScheduledExecutorService object
        ScheduledExecutorService scheduler
            = Executors.newScheduledThreadPool(1);
  
        // printing the current time
        System.out.println(
            "Current time : "
            + Calendar.getInstance().get(Calendar.SECOND));
  
        // Scheduling the tasks
		/*
		 * for (int i = 10; i >= 0; i--) { scheduler.scheduleWithFixedDelay(new Task(i), 10, 10, TimeUnit.SECONDS); //scheduler.schedule(new Task(i), 10-i , TimeUnit.SECONDS); }
		 */
        scheduler.scheduleWithFixedDelay(new Task(10), 0, 1, TimeUnit.SECONDS);
  
        // remember to shutdown the scheduler
        // so that it no longer accepts
          // any new tasks
      //  scheduler.shutdown();
    }
}
  
class Task implements Runnable {
    private int num;
    public Task(int num) { this.num = num; }
    public void run()
    {
        System.out.println(
            "Number " + num + " Current time : "
            + Calendar.getInstance().get(Calendar.SECOND));
    }
}