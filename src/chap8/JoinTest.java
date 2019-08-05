package chap8;

/**
 * 需要等parser解析完成后才能继续工作
 */
public class JoinTest {
    public static void main(String[] args) throws Exception{
        Thread parser1=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser1 finish");
            }
        });

        Thread parser2=new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("parser2 finish");
            }
        });
        parser1.start();
        parser2.start();
        parser1.join();
        parser2.join();
        System.out.println("All down");
    }
}
