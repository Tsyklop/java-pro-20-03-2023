package ua.hillel.classwork.threading;

public class EmailSender implements Runnable {

    private EmailQueue emailQueue;

    public EmailSender(EmailQueue emailQueue) {
        this.emailQueue = emailQueue;
    }

    @Override
    public void run() {

        try {
            while (true) {

                String value = this.emailQueue.getOrWait();

                System.out.println(value);

            }
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }

}
