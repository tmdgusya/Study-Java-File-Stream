package bytetest;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;

public class FileInputStreamBufferedTest {

    public static void main(String[] args) throws IOException {
        InputStream in = null;
        try {

            in = new FileInputStream("src/main/java/bytetest/test.txt");
            in = new BufferedInputStream(in);
            int c = 0;
            StringBuilder test = new StringBuilder();
            var start = System.currentTimeMillis();
            while ((c = (in).read()) != -1) {
                test.append(c);
            }
            var end = System.currentTimeMillis();
            System.out.print("TEST");
            System.out.print(test.toString()); // 3
            System.out.println();
            System.out.println(end - start);
        } finally {
            if (in != null) {
                in.close();
            }
        }
    }
}
