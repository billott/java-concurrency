package org.billdott.concurrency;

public class InterruptExample implements Runnable {

    @Override
    public void run() {
        try{
            Thread.sleep(1000);
        }catch(InterruptedException e){
            System.out.println("[" + Thread.currentThread().getName() + "] Interrupted by exception!");
        }
        while(!Thread.interrupted()){
        }
        System.out.println("[" + Thread.currentThread().getName() + "] Interrupted for the second time.");
    }

    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread(new InterruptExample(), "myThread");
        myThread.start();

        System.out.println("[" + Thread.currentThread().getName() + "] Sleeping in main thread for 5s...");
        Thread.sleep(5000);

        System.out.println("[" + Thread.currentThread().getName() + "] Interrupting myThread");
        Thread.interrupted();

        System.out.println("[" + Thread.currentThread().getName() + "] Sleeping in main thread for 5s...");
        Thread.sleep(5000);

        System.out.println("[" + Thread.currentThread().getName() + "] Interrupting myThread");
        Thread.interrupted();

    }
}
