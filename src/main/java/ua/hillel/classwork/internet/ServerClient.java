package ua.hillel.classwork.internet;

import org.apache.commons.io.IOUtils;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ServerClient implements Runnable {

    private final Socket socket;

    public ServerClient(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {

        try {

            DataInputStream dis = new DataInputStream(socket.getInputStream()); // read from client
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream()); // // write to client

            while (true) {

                String clientMessage = dis.readUTF();

                if ("file".equals(clientMessage)) {

                    String fileName = dis.readUTF();
                    long fileLength = dis.readLong();

                    byte[] buffer = new byte[(int) fileLength];

                    dis.readFully(buffer, 0, (int) fileLength);

                    IOUtils.write(buffer, new FileOutputStream("C:\\Users\\Tsyklop\\Desktop\\server\\" + fileName));

                    dos.writeUTF("File transfer ok");

                } else {
                    System.out.println("from client: " + clientMessage);
                    dos.writeUTF(clientMessage);
                }

            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }

}
