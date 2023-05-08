package ua.hillel.classwork.threading.race;

public class CounterThread implements Runnable {

    private Counter counter;

    public CounterThread(Counter counter) {
        this.counter = counter;
    }

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            counter.add(i);
            System.out.println(counter.getCounter());
        }
    }
}
