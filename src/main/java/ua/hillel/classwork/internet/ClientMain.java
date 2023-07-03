package ua.hillel.classwork.internet;

import org.apache.commons.io.IOUtils;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientMain {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);

        try (Socket socket = new Socket("localhost", 8080)) {

            DataInputStream dis = new DataInputStream(socket.getInputStream()); // read from server
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream()); // write to server

            while (true) {

                String text = scanner.nextLine();

                if ("exit".equals(text)) {
                    break;
                }

                // file: C:\Users\Tsyklop\Desktop\images.jpg
                if (text.startsWith("file:")) {

                    File file = new File(text.replace("file:", "").trim());

                    byte[] buffer = new byte[(int) file.length()];

                    IOUtils.readFully(new FileInputStream(file), buffer);

                    dos.writeUTF("file");
                    dos.writeUTF(file.getName());
                    dos.writeLong(file.length());

                    dos.write(buffer);
                    //dos.flush();

                    //dos.writeUTF("file-end");

                } else {
                    dos.writeUTF(text);
                    //dos.flush();
                }

                String serverResponse = dis.readUTF();

                System.out.println("Server: " + serverResponse);

            }

        }

    }

}
