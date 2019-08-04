package Chap5;

import chap4.Piped;
import org.omg.PortableServer.THREAD_POLICY_ID;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Producer-Consumer Model based on Condition
 */
public class TestCondition {
    private final static Lock lock=new ReentrantLock();
    private final static Condition condition=lock.newCondition();
    private static List<Product> list=new ArrayList<>();
    private static int MAX_SIZE=20;
    private static void put(Product product){
        while (true){
            try {
                lock.lock();
                while (list.size()==MAX_SIZE){
                    System.out.println("The list is full!");
                    condition.await();
                }
                list.add(product);
                System.out.println(Thread.currentThread().getName()+" put a product,now has "+list.size());
                condition.signalAll();
            }catch (Exception e){
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }
    private static void get(){
        while (true){
            lock.lock();
            try {
                while (list.isEmpty()){
                    System.out.println("List empty "+Thread.currentThread().getName()+" is waiting...");
                    condition.await();
                }
                list.remove(0);
                System.out.println(Thread.currentThread().getName()+" remove one,now has "+list.size());
                condition.signalAll();
            }catch (Exception e) {
                e.printStackTrace();
            }finally {
                lock.unlock();
            }
        }
    }

    public static void main(String[] args) {
        Product product=new Product();
        for (int i=0;i<10;i++){
            new Thread(new Producer(product),"Producer-"+i).start();
        }
        for (int i=0;i<5;i++){
            new Thread(new Consumer(),"Consumer-"+i).start();
        }

        System.out.println("Main over");
    }

    static class Consumer implements Runnable{

        @Override
        public void run() {
            try {
                get();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    static class Producer implements Runnable{
        private Product product;

        public Producer(Product product) {
            this.product = product;
        }

        @Override
        public void run() {
            try {
                put(product);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    static class Product{

    }
}
