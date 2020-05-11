package com.changda.tcp.nio;

import java.nio.ByteBuffer;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname NIOByteBufferPutGet
 * @description
 * @create 2020-04-15 15:18
 **/
public class NIOByteBufferPutGet {
    public static void main(String[] args) {
        // 创建一个buffer
        final ByteBuffer buffer = ByteBuffer.allocate(64);

        // 类型化方式放入数据
        buffer.putInt(100);
        buffer.putInt(9);
        buffer.putChar('南');

        buffer.flip();
        // 类型化读取数据
        System.out.println("buffer.getInt() = " + buffer.getInt());
        System.out.println("buffer.getInt() = " + buffer.getInt());
        System.out.println("buffer.getInt() = " + buffer.getInt());
    }
}
