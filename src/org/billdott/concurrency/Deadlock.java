package org.billdott.concurrency;

import java.util.Random;

public class Deadlock implements Runnable {

/*    
 * private static final Object resource1 = new Object();
 * private static final Object resource2 = new Object();
*/    
	private final Random random = new Random(System.currentTimeMillis());

    public static void main(String[] args) {
    	
//    	Deadlock occurs when a thread is waiting for an object lock 
//    	that is acquired by another thread and 
//    	the second thread is waiting for an object lock 
//    	that is acquired by the first thread.
    	
//    	Above description explains Deadlock situation where two or more threads 
//    	are blocked forever, waiting for each other.

        Thread myThread1 = new Thread(new Deadlock(), "thread-1");
        Thread myThread2 = new Thread(new Deadlock(), "thread-2");
        
        myThread1.start();
        myThread2.start();
    }

    @Override
    public void run() {
        for(int i =0; i < 10000; i++){
            boolean b  = random.nextBoolean();
            if(b){

            }else{

            }
        }
    }
}

/*
 * http://docs.oracle.com/javase/tutorial/essential/concurrency/deadlock.html
 * http://javarevisited.blogspot.com/2010/10/what-is-deadlock-in-java-how-to-fix-it.html
 * http://www.javatpoint.com/deadlock-in-java
 * 
 */
