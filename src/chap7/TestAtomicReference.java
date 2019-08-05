package chap7;

import java.util.concurrent.atomic.AtomicReference;

public class TestAtomicReference {
    public static AtomicReference<User> atomicReference=new AtomicReference<>();

    public static void main(String[] args) {
        User user=new User("aaa",10);
        atomicReference.set(user);
        User update=new User("bbb",12);
        atomicReference.compareAndSet(user,update);
        System.out.println(atomicReference.get().getName());
        System.out.println(atomicReference.get().getOld());
    }

    static class User{
        private String name;
        private int old;

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
