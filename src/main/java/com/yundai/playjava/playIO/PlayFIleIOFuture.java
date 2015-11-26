package com.yundai.playjava.playio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousFileChannel;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * Created by yundai on 11/25/15.
 */
public class PlayFIleIOFuture {

    public static void main(String[] args) {

        try {
            Path file = Paths.get("/Users/yundai/test.txt");

            AsynchronousFileChannel channel = AsynchronousFileChannel.open(file);

            ByteBuffer buffer =  ByteBuffer.allocate(100_000);
            Future<Integer> result = channel.read(buffer, 0);

            while(!result.isDone()) {
                System.out.println("Hello World");
            }

            Integer byteReads = result.get();
            System.out.println(byteReads);


        } catch (IOException | ExecutionException | InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }
}
