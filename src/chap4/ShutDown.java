package chap4;

public class ShutDown {
    public static void main(String[] args) throws Exception{
        Runner one=new Runner();
        Thread countThread=new Thread(one);
        countThread.start();
        Thread.sleep(1000);
        countThread.interrupt();
        Runner two=new Runner();
        countThread=new Thread(two);
        countThread.start();
        Thread.sleep(1000);
        two.cancel();
    }

    static class Runner implements Runnable{
        private long i;
        private volatile boolean on=true;

        @Override
        public void run() {
            while (on&&!Thread.currentThread().isInterrupted()){
                i++;
            }
            System.out.println("Count i="+i);
        }
        public void cancel(){
            on=false;
        }
    }
}
