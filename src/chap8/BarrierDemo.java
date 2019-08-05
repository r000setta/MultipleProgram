package chap8;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * CyclicBarrier应用场景
 */
public class BarrierDemo implements Runnable{
    private CyclicBarrier barrier=new CyclicBarrier(4,this);
    private Executor executor= Executors.newFixedThreadPool(4);
    private ConcurrentHashMap<String,Integer> sheet=new ConcurrentHashMap<>();
    private void count(){
        for (int i=0;i<4;i++){
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    sheet.put(Thread.currentThread().getName(),1);
                    try {
                        barrier.await();
                    }catch (Exception e){
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    @Override
    public void run() {
        int result=0;
        for (Map.Entry<String,Integer> sheet:sheet.entrySet()){
            result+=sheet.getValue();
        }
        sheet.put("result",result);
        System.out.println(result);
    }

    public static void main(String[] args) {
        BarrierDemo demo=new BarrierDemo();
        demo.count();
    }
}
