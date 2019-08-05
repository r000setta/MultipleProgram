package chap8;

import java.util.concurrent.CyclicBarrier;

public class BarrierTest {
    static CyclicBarrier cyclicBarrier=new CyclicBarrier(3);

    public static void main(String[] args) throws Exception{
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(1);
                    cyclicBarrier.await();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println(2);
                    cyclicBarrier.await();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        }).start();
        cyclicBarrier.await();
        System.out.println(3);
    }
}
