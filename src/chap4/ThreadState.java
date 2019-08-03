package chap4;

import java.util.concurrent.TimeUnit;

public class ThreadState {
    public static void main(String[] args) {
        new Thread(new TimeWaiting(),"TimeWaitingThread").start();
        new Thread(new Waiting(),"WaitingThread").start();
        new Thread(new Blocked(),"Block1").start();
        new Thread(new Blocked(),"Block2").start();
    }

    static class TimeWaiting implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    TimeUnit.SECONDS.sleep(10000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    static class Waiting implements Runnable{
        @Override
        public void run() {
            synchronized (Waiting.class){
                try {
                    Waiting.class.wait();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    static class Blocked implements Runnable{
        @Override
        public void run() {
            synchronized (Blocked.class){
                while (true){
                    try {
                        TimeUnit.SECONDS.sleep(10000);
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
