package ua.hillel.classwork.internet;

import org.apache.commons.io.IOUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ServerMain {

    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(8080)) {

            while (true) {

                Socket socket = serverSocket.accept();

                Thread clientThread = new Thread(new ServerClient(socket));
                clientThread.setDaemon(true);
                clientThread.start();

            }

        }

    }

}
