package ua.hillel.classwork.exception;

import java.io.IOException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        /*int[] array = new int[-10];

        Random random = new Random();

        //ArithmeticException
        int a = 10 / random.nextInt(10);

        //NullPointerException
        String str = null;
        str.length();
        Objects.requireNonNull(null);

        //StringIndexOutOfBoundsException
        "ffff".charAt(100);

        //  ArrayIndexOutOfBoundsException
        int g = new int[]{1, 2, 3, 4, 5}[10];

        // NumberFormatException
        int result = Integer.parseInt("1g");

        ioMethod();*/

        try {

            Scanner scanner = new Scanner(System.in);

            int c = scanner.nextInt();
            int b = scanner.nextInt();

            //throw new IOException();

            System.out.println(c / b);

            //recursion();

        } catch (NullPointerException | IndexOutOfBoundsException e) {
            e.printStackTrace();
            System.err.println("Something go wrong!");
        } catch (ArithmeticException e) {
            e.printStackTrace();
            System.err.println("You cannot / by zero!");
        } catch (MyException error) {
            error.printStackTrace();
            System.err.println("MyException");
        } catch (Exception e) {
            e.printStackTrace();
        } catch (Error error) {
            error.printStackTrace();
            System.err.println("Stack");
        } finally {
            System.out.println("finally");
        }

        try {
            System.out.println("try");
        } finally {

        }

        int j = 66;

        System.out.println("..................");

        method1();

    }

    public static void recursion() {
        int g = 10000000;
        recursion();
    }

    public static void ioMethod() throws IOException {
        System.out.println("ioMethod");
    }

    public static void method1() {
        method2();
        System.out.println("method1 after");
    }

    public static void method2() {
        method3();
        System.out.println("method2 after");
    }

    public static void method3() {
        method4();
        System.out.println("method3 after");
    }

    public static void method4() {
        method5();
        System.out.println("method4 after");
    }

    public static void method5() {
        try {
            method6();
            System.out.println("method5 after");
        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
        }
    }

    public static void method6() {
        method7();
        System.out.println("method6 after");
    }

    public static void method7() {
        method8();
        System.out.println("method7 after");
    }

    public static void method8() {
        method9();
        System.out.println("method8 after");
    }

    public static void method9() {
        method10();
        System.out.println("method9 after");
    }

    public static void method10() {
        throw new MyException();
    }

}