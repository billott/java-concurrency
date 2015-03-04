package org.billdott.concurrency;

public class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        Thread myThread = new Thread(new MyRunnable(), "myRunnalbe");
        myThread.start();
    }
}
