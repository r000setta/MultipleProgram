package chap1;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CounterTest {
    private AtomicInteger atomicInteger=new AtomicInteger(0);
    private int i=0;

    public static void main(String[] args) {
        final CounterTest counterTest=new CounterTest();
        List<Thread> ts=new ArrayList<>(600);
        long start=System.currentTimeMillis();
        for(int j=0;j<100;j++){
            Thread t=new Thread(new Runnable() {
                @Override
                public void run() {
                    for(int i=0;i<10000;i++){
                        counterTest.count();
                        counterTest.safeCount();
                    }
                }
            });
            ts.add(t);
        }
        for(Thread t:ts){
            t.start();
        }
        for (Thread t:ts){
            try {
                t.join();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        System.out.println(counterTest.i);
        System.out.println(counterTest.atomicInteger.get());
        System.out.println(System.currentTimeMillis()-start);
    }

    private void safeCount(){
        for(;;){
            int i=atomicInteger.get();
            boolean suc=atomicInteger.compareAndSet(i,++i);
            if (suc){
                break;
            }
        }
    }

    private void count(){
        i++;
    }
}
