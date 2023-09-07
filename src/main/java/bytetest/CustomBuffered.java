package bytetest;


import java.io.IOException;
import java.io.InputStream;

public class CustomBuffered {
    private static final int SOFT_MAX_ARRAY_LENGTH = Integer.MAX_VALUE - 8;
    private static final int BUCKET_SIZE = 10;
    private final InputStream in;
    private byte[] buffer;
    private int pos = 0;
    private int count = 0;

    public CustomBuffered(InputStream in) {
        this.in = in;
        buffer = new byte[10];
    }

    public int read() throws IOException {
        if (count == pos) {
            fill();
        }
        return buffer[count++];
    }

    private void fill() throws IOException {
        if (isFullBuffer()) {
            int nsz = newLength(pos, 1, pos);
            byte[] nbuf = new byte[nsz];
            System.arraycopy(buffer, 0, nbuf, 0, pos);
            buffer = nbuf;
        }
        while (!isFullBuffer()) {
            byte character = (byte) in.read();
            buffer[pos++] = character;
        }
    }

    private boolean isFullBuffer() {
        return pos == buffer.length;
    }

    private int newLength(int oldLength, int minGrowth, int prefGrowth) {
        // preconditions not checked because of inlining
        // assert oldLength >= 0
        // assert minGrowth > 0

        int prefLength = oldLength + Math.max(minGrowth, prefGrowth); // might overflow
        if (0 < prefLength && prefLength <= SOFT_MAX_ARRAY_LENGTH) {
            return prefLength;
        } else {
            // put code cold in a separate method
            return hugeLength(oldLength, minGrowth);
        }
    }

    private int hugeLength(int oldLength, int minGrowth) {
        int minLength = oldLength + minGrowth;
        if (minLength < 0) { // overflow
            throw new OutOfMemoryError(
                    "Required array length " + oldLength + " + " + minGrowth + " is too large");
        } else if (minLength <= SOFT_MAX_ARRAY_LENGTH) {
            return SOFT_MAX_ARRAY_LENGTH;
        } else {
            return minLength;
        }
    }
}
