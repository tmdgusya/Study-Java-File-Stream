package bytetest;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class TestUsingBytes {
    public static void main(String[] args) throws IOException {
        InputStream in = null;
        byte[] buffer = new byte[10];
        try {

            in = new FileInputStream("src/main/java/bytetest/test.txt");
            int c = 10;
            StringBuilder test = new StringBuilder();
            var start = System.currentTimeMillis();

            while (c == 10) {
                byte[] characters = in.readNBytes(10);
                test.append(characters);
                c = characters.length;
            }
            var end = System.currentTimeMillis();
            System.out.print("TEST");
            System.out.print(test.toString()); // 9
            System.out.println();
            System.out.println(end - start);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}
