package ua.hillel.classwork.threading;

import java.util.Arrays;
import java.util.Random;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Random random = new Random();

        int[] numbers = new int[10000];

        for (int i = 0; i < numbers.length; i++) {
            numbers[i] = random.nextInt(100);
        }

        long startMills = System.currentTimeMillis();

        int correctSum = 0;
        for (int i = 0; i < numbers.length; i++) {
            correctSum += numbers[i];
        }

        System.out.println("Correct: " + correctSum);

        long endMills = System.currentTimeMillis();

        System.out.println("correct diff: " + (endMills - startMills));

        startMills = System.currentTimeMillis();

        int chunkSize = 400;

        int chunksCount = (int) Math.ceil((double) numbers.length / chunkSize);

        int[][] numbersChunks = new int[chunksCount][chunkSize];

        int chunkIndex = 0;
        for (int i = 0; i < numbers.length; i += chunkSize) {
            numbersChunks[chunkIndex] = Arrays.copyOfRange(numbers, i, Math.min(numbers.length, i + chunkSize));
            chunkIndex++;
        }

        Thread[] threads = new Thread[numbersChunks.length];

        NumbersCalculator[] numbersCalculators = new NumbersCalculator[numbersChunks.length];

        for (int i = 0; i < numbersChunks.length; i++) {

            NumbersCalculator numbersCalculator = new NumbersCalculator(numbersChunks[i]);

            numbersCalculators[i] = numbersCalculator;

            Thread thread = new Thread(numbersCalculator);

            threads[i] = thread;

            thread.start();
            thread.stop();

        }

        int sum = 0;
        for (int i = 0; i < threads.length; i++) {
            threads[i].join();
            sum += numbersCalculators[i].getSum();
        }

        System.out.println("Calculated: " +sum);

        endMills = System.currentTimeMillis();

        System.out.println("diff: " + (endMills - startMills));

    }

    public static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("From MyThread " + Thread.currentThread().getName());
        }

    }

    public static class NumbersCalculator implements Runnable {

        private int sum = 0;

        private final int[] numbers;

        public NumbersCalculator(int[] numbers) {
            this.numbers = numbers;
        }

        @Override
        public void run() {
            System.out.println("From MyRunnable " + Thread.currentThread().getName());
            for (int i = 0; i < numbers.length; i++) {
                sum += numbers[i];
            }
        }

        public int getSum() {
            return sum;
        }

    }

}
