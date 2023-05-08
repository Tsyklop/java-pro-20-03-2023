package ua.hillel.classwork.io;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Serializable;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class IOMain {

    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

//        File

        File file = new File(".gitignore");

        File fileNotExisted = new File("E:\\IDEA1\\hillel\\java-20-03-23\\class-work\\mkdir");

        //fileNotExisted.mkdir();
        //fileNotExisted.mkdirs();
        //fileNotExisted.createNewFile();

        File outputFile = new File("./custom.txt");

        File directory = new File("./src");

        System.out.println("getName: " + file.getName());

        System.out.println("getFreeSpace: " + file.getFreeSpace());
        System.out.println("getTotalSpace: " + file.getTotalSpace());
        System.out.println("getUsableSpace: " + file.getUsableSpace());

        System.out.println("getAbsoluteFile: " + file.getAbsoluteFile());
        System.out.println("getCanonicalFile: " + file.getCanonicalFile());

        System.out.println("exists: " + file.exists());
        System.out.println("isFile: " + file.isFile());
        System.out.println("length: " + file.length());
        System.out.println("isDirectory: " + file.isDirectory());
        System.out.println("isAbsolute: " + file.isAbsolute());
        System.out.println("isHidden: " + file.isHidden());

        /*System.out.println("mkdir: " + file.mkdir());
        System.out.println("mkdirs: " + file.mkdirs());*/

        // E:\IDEA\hillel\java-20-03-23\class-work\.gitignore

//        InputStream

        if (!file.getCanonicalFile().getParentFile().exists()) {
            //file.mkdir(); // Создаст только папку class-work
            file.mkdirs(); // Создаст папки по пути к папке class-work, включая саму папку class-work, если их нет
        }

        if (!file.exists()) {
            file.createNewFile();
        }

        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();

        try (InputStream is = new FileInputStream(file)) {

            // Считывание по одному байту
            /*int b;
            while ((b = is.read()) != -1) {
                System.out.print((char) b);
            }*/

            // Считывание N-го количества байт сразу

            StringBuilder sb = new StringBuilder();

            byte[] bytes = new byte[200];

            int readedBytesCount;
            while ((readedBytesCount = is.read(bytes)) != -1) {
                for (int i = 0; i < readedBytesCount; i++) {
                    sb.append((char) bytes[i]);
                }
            }

            /*

            1. 200
            2. 200
            3. 90

             */

            System.out.println(sb);

        }

        /*Scanner scanner = new Scanner(file);

        try {
            while (true) {
                System.out.println(scanner.nextLine());
            }
        } catch (NoSuchElementException e) {
            e.printStackTrace();
        }*/

        /*BufferedReader d = new BufferedReader(new InputStreamReader(new FileInputStream(file)));

        String line;
        while ((line = d.readLine()) != null) {
            System.out.println(line);
        }*/

//        OutputStream

        /*OutputStream os = new FileOutputStream(outputFile, false);
        os.write(sb.toString().getBytes());
        os.close();*/

        /*Writer writer = new OutputStreamWriter(new FileOutputStream(outputFile), StandardCharsets.UTF_8);
        writer.write(sb.toString());*/

        /*Scanner scanner = new Scanner(System.in);

        Writer writer = new OutputStreamWriter(new FileOutputStream(new File("from_keyboard.txt")), StandardCharsets.UTF_8);

        while (true) {

            String line = scanner.nextLine();

            if (line == null) {
                break;
            }

            writer.append(line);
            writer.flush();

        }

        writer.close();*/

        //ObjectInputStream

        User user = new User(1, "Alex", "test", "qwerty");
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("user.dat"));
        oos.writeObject(user);
        oos.close();

        ObjectInputStream ois = new ObjectInputStream(new FileInputStream("user.dat"));

        User userReaded = (User) ois.readObject();

        System.out.println(userReaded);

    }

    public static class User implements Serializable {

        private int id;

        private String name;

        private String email;

        private String password;

        public User() {
        }

        public User(int id, String name, String email, String password) {
            this.id = id;
            this.name = name;
            this.email = email;
            this.password = password;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", email='" + email + '\'' +
                    ", password='" + password + '\'' +
                    '}';
        }
    }

    /*public static void method(File file) {
        if (!file.getParentFile().getName().equals("example")) {
            throw new IllegalArgumentException("");
        }
    }*/

}
