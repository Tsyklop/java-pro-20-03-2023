package ua.hillel.classwork.threading.race;

public class MyRunnable implements Runnable {

    static int counter;

    int id;
    NotThreadSafe instance = null;

    public MyRunnable(NotThreadSafe instance) {
        id = ++counter;
        this.instance = instance;
    }

    public void run(){
        this.instance.add("some text");
    }
}
