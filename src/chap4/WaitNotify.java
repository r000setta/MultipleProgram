package chap4;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WaitNotify {
    static boolean flag=true;
    static Object lock=new Object();

    public static void main(String[] args) throws Exception{
        Thread wait=new Thread(new Wait());
        wait.start();
        Thread.sleep(1000);
        Thread notify=new Thread(new Notify());
        notify.start();
    }

    static class Wait implements Runnable{
        @Override
        public void run() {
            synchronized (lock){
                while (flag){
                    try {
                        System.out.println(Thread.currentThread()+ " flag is true. waiting@ "+new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();    //release the lock
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
            System.out.println(Thread.currentThread()+ " flag is false. running@ "+new SimpleDateFormat("HH:mm:ss").format(new Date()));
        }
    }

    static class Notify implements Runnable{

        @Override
        public void run() {
            synchronized (lock){
                System.out.println(Thread.currentThread()+" hold lock. notify@"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag=false;
                try {
                    Thread.sleep(3000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            synchronized (lock){
                System.out.println(Thread.currentThread()+" hold lock again. sleep@"+new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    Thread.sleep(2000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
}
