package bytetest;

import java.io.OutputStream;

public class ByteTest {
    public static void main(String[] args) {
        byte b1 = 0;
        byte b2 = 10;
        test((byte) 0);
    }

    private static void test(byte b) {
        System.out.println(b);
    }
}
