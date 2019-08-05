package chap8;

import java.util.concurrent.CountDownLatch;

public class CountDownTest {
    static CountDownLatch c=new CountDownLatch(2);

    public static void main(String[] args) throws Exception{
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(1);
                c.countDown();
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(2);
                c.countDown();
            }
        }).start();
        c.await();
        System.out.println(3);
    }
}
