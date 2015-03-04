package org.billdott.concurrency;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class AtomicThread implements Runnable {

	
    private static final SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss:SSS");
    private static Map<String, String> configuration = new HashMap<String, String>();

    public static String getTimeStamp(){
    	Timestamp currentTimestamp = new java.sql.Timestamp(Calendar.getInstance().getTime().getTime());
    	return currentTimestamp.toString()+" ";
    }

    @Override
    public void run() {
        for(int i = 0; i < 10; i++){
            Map<String, String> currConfig = configuration;
            String value1 = currConfig.get("key-1");
            String value2 = currConfig.get("key-2");
            String value3 = currConfig.get("key-3");
            if(!(value1.equals(value2) && value2.equals(value3))){
                throw new IllegalStateException("Values are not equal.");
            }
            try{
                Thread.sleep(1);
            }catch(InterruptedException e){
                e.printStackTrace();
            }
        }
    }

    public static void readConfig(){
        Map<String, String> newConfig = new HashMap<String, String>();
        Date now = new Date();
        newConfig.put("key-1", sdf.format(now));
        newConfig.put("key-2", sdf.format(now));
        newConfig.put("key-3", sdf.format(now));
        configuration = newConfig;
        System.out.println(getTimeStamp()+"[readConfig] Load static map with keys.");

    }

    public static void main(String[] args) throws InterruptedException {
        readConfig();
        Thread configThread = new Thread(new Runnable(){
           public void run(){
               for(int i = 0; i < 10; i++){
                   readConfig();
                   try{
                       Thread.sleep(1);
                   }catch(InterruptedException e){
                       e.printStackTrace();
                   }
               }
           }
        }, "configuration-thread");
        System.out.println(getTimeStamp()+"[main] create configuration-thread.");

        configThread.start();
        Thread[] threads = new Thread[5];
        for(int i = 0; i < threads.length; i++){
            threads[i] = new Thread(new AtomicThread(), "thread-" + i);
            threads[i].start();
            System.out.println(getTimeStamp()+"[" + Thread.currentThread().getName() + "] Starting this Atomic thread." + threads[i].getName());
        }
        System.out.println(getTimeStamp()+"[main] ready to call join threads.join()");
        for(int i = 0; i < threads.length; i++){
            threads[i].join();
            System.out.println(getTimeStamp()+"[" + Thread.currentThread().getName() + "] Joining this thread." + threads[i].getName());
        }
        System.out.println("[main] ready to call configThread.join()");
        configThread.join();
        System.out.println(getTimeStamp()+"[" + Thread.currentThread().getName() + "] All threads have finished.");
    }
}
