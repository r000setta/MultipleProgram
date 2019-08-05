package chap7;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 原子更新数组
 */
public class AtomicArray {
    static int[] value=new int[]{1,2,3};
    static AtomicIntegerArray ai=new AtomicIntegerArray(value);

    public static void main(String[] args) {
        ai.getAndSet(0,3);
        System.out.println(ai.get(0));
        System.out.println(value[0]);
    }
}
