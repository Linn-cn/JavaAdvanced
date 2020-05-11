package com.changda.tcp.nio;


import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
public class NIOFileChannel03 {
    public static void main(String[] args) throws IOException {
        try (FileInputStream inputStream = new FileInputStream("src\\main\\resources\\1.txt");
             FileChannel source = inputStream.getChannel();
             FileOutputStream outputStream = new FileOutputStream("src\\main\\resources\\2.txt");
             FileChannel target = outputStream.getChannel()) {
            ByteBuffer buffer = ByteBuffer.allocate(512);
            while (true){
                // 这里有一个重要的操作，一定不要忘了
                buffer.clear();
                int read = source.read(buffer);
                if (read == -1) break;  // 表示读完
                // 将buffer中的数据写入到target
                buffer.flip();
                target.write(buffer);
            }
        }
    }
}
