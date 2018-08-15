package github.weizibin.io;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class GetChannel {
    private static final int SIZE = 1024;
    public static void main(String[] args) throws IOException {
        FileChannel fileChannel = new FileInputStream("README.md").getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(SIZE);
        fileChannel.read(byteBuffer);
        byteBuffer.flip();
        while (byteBuffer.hasRemaining()) {
            System.out.print((char)byteBuffer.get());
        }
    }

}
