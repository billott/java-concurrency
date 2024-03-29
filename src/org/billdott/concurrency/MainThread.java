package org.billdott.concurrency;

public class MainThread {

    public static void main(String[] args) {
        long id = Thread.currentThread().getId();
        String name = Thread.currentThread().getName();
        int priority = Thread.currentThread().getPriority();
        Thread.State state = Thread.currentThread().getState();
        String threadGroupName = Thread.currentThread().getThreadGroup().getName();
        System.out.println("id = " + id);
        System.out.println("name = " + name);
        System.out.println("priority = " + priority);
        System.out.println("state = " + state);
        System.out.println("threadGroupName = " + threadGroupName);
    }
}
