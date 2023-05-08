package ua.hillel.classwork.threading;

public class EmailQueue {

    private String[] queue = new String[100];

    public synchronized String getOrWait() throws InterruptedException {
        /*while (queue.isEmpty()) {
            wait();
        }*/
        return queue[0];
    }

    public synchronized void add(String value) {
        queue[0] = value;
        notifyAll();
    }

}
