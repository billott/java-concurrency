package org.billdott.concurrency;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Random;

public class JoinThread implements Runnable {


    public static String getTimeStamp(){
    	Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    	return currentTimestamp.toString()+" ";
    }

    private Random random = new Random(System.currentTimeMillis());

    @Override
    public void run() {
        //simulate some CPU expensive task, loop for 100000000 times
        for(int i = 0; i < 10; i++){
            random.nextInt();
        }
        System.out.println(getTimeStamp()+"[" + Thread.currentThread().getName() + "] finished.");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[20];
        for(int i = 0; i < threads.length; i++){
            threads[i] = new Thread(new JoinThread(), "joinThread-" + i);
            threads[i].start();
            System.out.println(getTimeStamp()+"[" + Thread.currentThread().getName() + "] Starting this thread." + threads[i].getName());
        }
        for(int i = 0; i < threads.length; i++){
            threads[i].join();
            System.out.println(getTimeStamp()+"[" + Thread.currentThread().getName() + "] Joining this thread." + threads[i].getName());
        }
        System.out.println(getTimeStamp()+"[" + Thread.currentThread().getName() + "] All threads have finished.");
    }
}
