package ua.hillel.classwork.threading.race;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Counter {

//    private volatile AtomicInteger counter = new AtomicInteger(0);

    private int counter = 0;

    //private Object lock = new Object();

    private Lock lock = new Lock() {

        private boolean locked = false;

        @Override
        public synchronized void lock() {
            while (locked) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
            }
            locked = true;
        }

        @Override
        public void lockInterruptibly() throws InterruptedException {

        }

        @Override
        public boolean tryLock() {
            return false;
        }

        @Override
        public boolean tryLock(long time, TimeUnit unit) throws InterruptedException {
            return false;
        }

        @Override
        public void unlock() {
            locked = false;
            notifyAll();
        }

        @Override
        public Condition newCondition() {
            return null;
        }

    };

    public void add(int value) {

        System.out.println("");

        /*synchronized (lock) {
            System.out.println(Thread.currentThread().getName() + " before " + counter + " add " + value);
            counter += value;
        }*/

        try {

            // t 0 get lock
            lock.lock(); // locked = true

            // t 0 do code
            System.out.println(Thread.currentThread().getName() + " before " + counter + " add " + value);
            counter += value;

        } finally {
            lock.unlock(); // locked = false, notifyAll
        }

        System.out.println("");

        /*wait();
        notify();
        notifyAll();*/

    }

    public synchronized void remove() {

    }

    public int getCounter() {
        return counter;
    }

    public synchronized static void staticM() {

    }

    public static void staticMS() {

        synchronized(Counter.class) {

        }

    }

}
