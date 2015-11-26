package com.yundai.playjava.playio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.channels.CompletionHandler;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Created by yundai on 11/25/15.
 */
public class PlayFileIOCallback {

    public static void main(String[] args) {

        try {
            Path file = Paths.get("/Users/yundai/test.txt");

            AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);

            ByteBuffer buffer =  ByteBuffer.allocate(100_000);

            channel.read(buffer, 0, buffer, new CompletionHandler<Integer, ByteBuffer>() {
                @Override
                public void completed(Integer result, ByteBuffer attachment) {
                    System.out.println(result);
                }

                @Override
                public void failed(Throwable exc, ByteBuffer attachment) {
                    System.out.println(exc.getMessage());
                }
            });

        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
