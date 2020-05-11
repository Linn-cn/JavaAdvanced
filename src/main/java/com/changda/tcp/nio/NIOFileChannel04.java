package com.changda.tcp.nio;


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
public class NIOFileChannel04 {
    public static void main(String[] args) throws IOException {
        try (FileInputStream inputStream = new FileInputStream("src\\main\\resources\\2.txt");
             FileChannel source = inputStream.getChannel();
             FileOutputStream outputStream = new FileOutputStream("src\\main\\resources\\3.txt");
             FileChannel target = outputStream.getChannel()) {
            for (long count = source.size(); count > 0;) {
                long to = source.transferTo(source.position(), count, target);
                source.position(source.position() + to);
                count -= to;
            }
        }
    }
}
