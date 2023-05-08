package ua.hillel.classwork.threading.race;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Counter counter = new Counter();

        Thread thread1 = new Thread(new CounterThread(counter));
        Thread thread2 = new Thread(new CounterThread(counter));

        thread1.start();
        thread2.start();

        Thread.sleep(2000);

        System.out.println(counter.getCounter());

        /*NotThreadSafe sharedInstance = new NotThreadSafe();

        new Thread(new MyRunnable(sharedInstance)).start();
        new Thread(new MyRunnable(sharedInstance)).start();

        Thread.sleep(2000);

        System.out.println(sharedInstance.builder.toString());*/

    }

}
