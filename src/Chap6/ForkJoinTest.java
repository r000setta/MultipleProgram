package Chap6;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.Future;
import java.util.concurrent.RecursiveTask;

public class ForkJoinTest extends RecursiveTask<Integer> {
    private static final int THRESHOLD=2;
    private int start;
    private int end;

    public ForkJoinTest(int start, int end) {
        this.start = start;
        this.end = end;
    }

    @Override
    protected Integer compute() {
        int sum=0;
        //如果任务足够小就执行
        boolean canCompute=(end-start)<=THRESHOLD;
        if (canCompute){
            for (int i=start;i<=end;i++){
                sum+=i;
            }
        }else {
            //任务大于阈值，分裂成两个子任务
            int middle=(start+end)/2;
            ForkJoinTest leftTask=new ForkJoinTest(start,middle);
            ForkJoinTest rightTask=new ForkJoinTest(middle+1,end);
            //执行子任务
            leftTask.fork();
            rightTask.fork();
            int leftResult=leftTask.join();
            int rightResult=rightTask.join();
            //合并子任务
            sum=leftResult+rightResult;
        }
        return sum;
    }

    public static void main(String[] args) {
        ForkJoinPool forkJoinPool=new ForkJoinPool();
        ForkJoinTest task=new ForkJoinTest(1,4);
        Future<Integer> result=forkJoinPool.submit(task);
        try {
            System.out.println(result.get());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
