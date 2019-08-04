package Chap5;

import java.util.concurrent.locks.Lock;

public class TwinLockTest {
    public static void main(String[] args) {
        final Lock lock=new TwinsLock();
        for (int i=0;i<10;i++){
            Worker w=new Worker(lock);
            w.setDaemon(true);
            w.start();
        }
        for (int i=0;i<10;i++){
            try {
                Thread.sleep(1000);
                System.out.println();
            }catch (Exception e){

            }
        }
    }


    static class Worker extends Thread{
        private Lock lock;

        public Worker(Lock lock) {
            this.lock = lock;
        }

        @Override
        public void run() {
            while (true){
                lock.lock();
                try {
                    Thread.sleep(1000);
                    System.out.println(Thread.currentThread().getName());
                    Thread.sleep(1000);
                }catch (Exception e){
                    e.printStackTrace();
                }finally {
                    lock.unlock();
                }
            }
        }
    }
}
