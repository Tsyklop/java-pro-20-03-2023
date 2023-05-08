package ua.hillel.classwork.io;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.FileVisitResult;
import java.nio.file.FileVisitor;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;

public class NIOMain {

    public static void main(String[] args) throws IOException {

        Path path = Path.of("src", "main", "java"); // src/main/java

        System.out.println("getFileName: " + path.getFileName());

        System.out.println("toAbsolutePath: " + path.toAbsolutePath());

        System.out.println("size: " + Files.size(path));
        System.out.println("exists: " + Files.exists(path));
        System.out.println("isDirectory: " + Files.isDirectory(path));
        System.out.println("isRegularFile: " + Files.isRegularFile(path));

        RandomAccessFile aFile = new RandomAccessFile(".gitignore", "rw");
        FileChannel inChannel = aFile.getChannel();

        //create buffer with capacity of 48 bytes
        ByteBuffer buf = ByteBuffer.allocate(48);

        int bytesRead = inChannel.read(buf); //read into buffer.
        while (bytesRead != -1) {

            buf.flip();  //make buffer ready for read

            while(buf.hasRemaining()){
                System.out.print((char) buf.get()); // read 1 byte at a time
            }

            buf.clear(); //make buffer ready for writing
            bytesRead = inChannel.read(buf);
        }
        aFile.close();

    }

}
