package chap4;

/**
 * 使用javap进行分析
 */
public class SyncTest {
    public static void main(String[] args) {
        synchronized (SyncTest.class){
            m();
        }
    }
    public static synchronized void m(){

    }
}
