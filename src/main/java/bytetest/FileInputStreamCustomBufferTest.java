package bytetest;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class FileInputStreamCustomBufferTest {

    public static void main(String[] args) throws IOException {
        InputStream in = null;
        try {
            in = new FileInputStream("src/main/java/bytetest/test.txt");
            CustomBuffered _in = new CustomBuffered(in);
            int c = 0;
            StringBuilder test = new StringBuilder();
            var start = System.currentTimeMillis();
            while ((c = (_in).read()) != -1) {
                test.append(c);
            }
            var end = System.currentTimeMillis();
            System.out.print("TEST");
            System.out.print(test.toString());
            System.out.println();
            System.out.println(end - start); // 25
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}
