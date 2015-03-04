package org.billdott.concurrency;

import java.sql.Timestamp;
import java.util.Calendar;

public class InterruptThread implements Runnable {

    public static String getTimeStamp(){
    	Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    	return currentTimestamp.toString()+" ";
    }

    @Override
    public void run() {
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            System.out.println(getTimeStamp()+"[" + Thread.currentThread().getName() + "] Interrupted by exception!");
        }
        while(!Thread.interrupted()){
        }
        System.out.println(getTimeStamp()+"[" + Thread.currentThread().getName() + "] Interrupted for the second time.");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread(new InterruptThread(), "myThread");
        myThread.start();

        System.out.println(getTimeStamp()+"[" + Thread.currentThread().getName() + "] Sleeping in main thread for 5s...");
        Thread.sleep(5000);

        System.out.println(getTimeStamp()+"[" + Thread.currentThread().getName() + "] Interrupting myThread");
        Thread.interrupted();

        System.out.println(getTimeStamp()+"[" + Thread.currentThread().getName() + "] Sleeping in main thread for 5s...");
        Thread.sleep(5000);

        System.out.println(getTimeStamp()+"[" + Thread.currentThread().getName() + "] Interrupting myThread");
        Thread.interrupted();
    }
}
