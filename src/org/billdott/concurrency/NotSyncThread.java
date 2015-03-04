package org.billdott.concurrency;

import java.sql.Timestamp;
import java.util.Calendar;

public class NotSyncThread implements Runnable {

    private static int counter = 0;

    public static String getTimeStamp(){
    	Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    	return currentTimestamp.toString()+" ";
    }
    
    @Override
    public void run() {
        while(counter < 10){
            System.out.println(getTimeStamp() + "[" + Thread.currentThread().getName() + "] before: " + counter);
            counter++;
            System.out.println(getTimeStamp() + "[" + Thread.currentThread().getName() + "] after: " + counter);
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println(getTimeStamp() + "[main] START.");
        Thread[] threads = new Thread[5];
        for(int i = 0; i < threads.length; i++){
            threads[i] = new Thread(new NotSyncThread(), "thread-" + i);
            threads[i].start();
        }
        for(int i = 0; i < threads.length; i++){
            threads[i].join();
        }
        System.out.println(getTimeStamp() + "[main] END.");
    }
}
