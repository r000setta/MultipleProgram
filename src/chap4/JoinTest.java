package chap4;

public class JoinTest {
    public static void main(String[] args) throws Exception{
        Thread previous=Thread.currentThread();
        for (int i=0;i<10;i++){
            Thread thread=new Thread(new Domino(previous),String.valueOf(i));
            thread.start();
            previous=thread;
        }
        try {
            Thread.sleep(2000);
        }catch (Exception e){

        }
        System.out.println(Thread.currentThread().getName()+" terminated");
    }

    static class Domino implements Runnable{
        private Thread thread;

        public Domino(Thread thread) {
            this.thread = thread;
        }

        @Override
        public void run() {
            try {
                thread.join();
            }catch (Exception e){
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName()+" terminated");
        }
    }
}
