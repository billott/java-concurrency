package org.billdott.concurrency;

public class MyThread extends Thread {
    
    public MyThread(String name){
        super(name);
    }
    
    @Override
    public void run(){
        System.out.println("Thread.currentThread().getName() = " + Thread.currentThread().getName());
    }

    public static void main(String[] args) throws InterruptedException {
        MyThread myThread = new MyThread("myThread");
        myThread.start();
    }
    
}
