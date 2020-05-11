package com.changda.tcp.nio;

import java.io.ByteArrayInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname NIOFileChannel01
 * @description 写入本地文件
 * @create 2020-04-15 11:47
 **/
public class NIOFileChannel01 {
    public static void main(String[] args) throws IOException {
        String str = "Hello,南街";
        // 创建一个输出流 -> outputStream
        FileOutputStream outputStream = new FileOutputStream("d:\\file01.txt");
        // 获取输出流的channel，真实类型其实是FileChannelImpl
        FileChannel targetChannel = outputStream.getChannel();
        ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
//        ByteBuffer byteBuffer = ByteBuffer.allocate(5);
//        byteBuffer.put(str.getBytes());
//        byteBuffer.flip();
//        targetChannel.write(byteBuffer);
        ByteArrayInputStream inputStream = new ByteArrayInputStream(str.getBytes());
        ReadableByteChannel sourceChannel = Channels.newChannel(inputStream);
        sourceChannel.read(byteBuffer);
        byteBuffer.flip();
        // 将byteBuffer的数据写入到targetChannel
        targetChannel.write(byteBuffer);
        outputStream.close();
        inputStream.close();
    }
}
