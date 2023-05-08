package ua.hillel.classwork.threading;

public class EmailProducer implements Runnable {

    private EmailQueue emailQueue;

    public EmailProducer(EmailQueue emailQueue) {
        this.emailQueue = emailQueue;
    }

    @Override
    public void run() {

        try {
            for (int i = 0; i < 100; i++) {
                this.emailQueue.add("" + i);
                Thread.sleep(200);
            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
