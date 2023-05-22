package ua.hillel.classwork.threading;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class EmailQueue {

    private Queue<String> queue = new LinkedList<>();

    public synchronized String getOrWait() throws InterruptedException {
        while (queue.isEmpty()) {
            wait();
        }
        return queue.poll();
    }

    public synchronized void add(String value) {
        queue.add(value);
        notifyAll();
    }

}
