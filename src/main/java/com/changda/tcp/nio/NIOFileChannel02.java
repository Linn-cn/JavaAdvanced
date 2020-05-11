package com.changda.tcp.nio;


import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname NIOFileChannel01
 * @description 读取本地文件数据
 * @create 2020-04-15 11:47
 **/
public class NIOFileChannel02 {
    public static void main(String[] args) throws IOException {
        final File file = new File("d:\\file01.txt");
        try (FileInputStream inputStream = new FileInputStream(file);
             FileChannel fileChannel = inputStream.getChannel()) {
            // 创建缓冲区
            final ByteBuffer buffer = ByteBuffer.allocate(((int) file.length()));
            // 将通道数据读取到缓冲区
            fileChannel.read(buffer);
            System.out.println(new String(buffer.array()));
            inputStream.close();
        }
    }
}
