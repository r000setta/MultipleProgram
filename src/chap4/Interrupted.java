package chap4;

public class Interrupted {
    public static void main(String[] args) throws Exception{
        Thread sleepThread=new Thread(new SleepRunner(),"SleepThread");
        sleepThread.setDaemon(true);
        Thread busyThread=new Thread(new BusyRunner(),"BusyThread");
        busyThread.setDaemon(true);
        sleepThread.start();
        busyThread.start();
        Thread.sleep(2000);
        sleepThread.interrupt();
        busyThread.interrupt();
        System.out.println("SleepThread interrupted is "+sleepThread.isInterrupted());
        System.out.println("BusyThread interrupted is "+busyThread.isInterrupted());
        Thread.sleep(2000);
    }

    //抛出异常时中断标志位被清除
    static class SleepRunner implements Runnable{
        @Override
        public void run() {
            while (true){
                try {
                    Thread.sleep(10000);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    static class BusyRunner implements Runnable{
        @Override
        public void run() {
            while (true){

            }
        }
    }
}
