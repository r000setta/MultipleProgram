package chap4.example;

/**
 * 场景：调用一个方法等待一段时间，给定时间内能得到结果，
 * 则返回结果，反之返回默认结果.
 */
public class Timeout {
    public synchronized Object get(long mills) throws Exception{
        long future=System.currentTimeMillis()+mills;
        long remaining=mills;
//        while ((result))

        //TODO
        return null;
    }
}
