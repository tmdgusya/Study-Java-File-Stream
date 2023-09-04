package iotest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class InputStreamTest {
    public static void main(String[] args) throws IOException {
        InputStream input = new FileInputStream("src/main/java/iotest/Hello.txt");

        byte[] data = new byte[2];
        int count = 0;

        while (data.length == 2) {
            count++;
            data = input.readNBytes(1);
            printBytes(data);
            System.out.println(" : " + count);
        }
    }

    private static void printBytes(byte[] data) {
        for (byte b : data) {
            System.out.print((char) b);
        }
    }
}
