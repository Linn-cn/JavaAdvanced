package com.changda.tcp.nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @program: JucAndJvm
 * @classname: NIODemo
 * @description:
 * @author: 南街
 * @create: 2019-12-09 12:20
 **/
public class NIOCopyFileDemo {
    public static void main(String[] args) throws URISyntaxException, IOException {
//        copyFile(new File("D:\\111.txt"), new File("D:\\test.txt"));
        copyFileByBuffer(new File("D:\\111.txt"), new File("D:\\test.txt"));
    }

    public static void copyFile(File source, File des) throws IOException {
        try (FileChannel sourceChannel = new FileInputStream(source).getChannel();
             FileChannel targetChannel = new FileOutputStream(des).getChannel()) {
            for (long count = sourceChannel.size(); count > 0; ) {
                long transferred = sourceChannel.transferTo(sourceChannel.position(), count, targetChannel);
                sourceChannel.position(sourceChannel.position() + transferred);
                count -= transferred;
            }
        }
    }

    public static void copyFileByBuffer(File source, File des) throws IOException {
        FileChannel inChannel = new FileInputStream(source).getChannel();
        FileChannel outChannel = new FileOutputStream(des).getChannel();

        MappedByteBuffer map = inChannel.map(FileChannel.MapMode.READ_ONLY, 0, source.length());
        outChannel.write(map);
        inChannel.close();
        outChannel.close();
    }
}
