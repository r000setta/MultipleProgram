package chap7;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 原子更新字段
 */
public class AtomicFiledTset {
    private static AtomicIntegerFieldUpdater<User> a=AtomicIntegerFieldUpdater.newUpdater(User.class,"old");

    public static void main(String[] args) {
        User user=new User("aaa",10);
        System.out.println(a.getAndIncrement(user));
        System.out.println(a.get(user));
    }
    public static class User{
        private String name;
        public volatile int old;

        public User(String name, int old) {
            this.name = name;
            this.old = old;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getOld() {
            return old;
        }

        public void setOld(int old) {
            this.old = old;
        }
    }
}
