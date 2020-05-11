package com.changda.tcp.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Arrays;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname ScatteringAndGathering
 * @description Scattering:将数据写到buffer时，可以采用buffer数组，依次写入    【分散】
 * Gathering：从buffer读取数据时，可以采用buffer数组，依次读
 * @create 2020-04-15 17:24
 **/
public class ScatteringAndGathering {
    public static void main(String[] args) throws IOException {
        final ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        final InetSocketAddress inetSocketAddress = new InetSocketAddress(7000);

        //绑定端口到socket，并启动
        serverSocketChannel.socket().bind(inetSocketAddress);

        //创建buffer数组
        ByteBuffer[] buffers = new ByteBuffer[2];
        buffers[0] = ByteBuffer.allocate(5);
        buffers[0] = ByteBuffer.allocate(3);

        //等待客户端连接
        SocketChannel socketChannel = serverSocketChannel.accept();
        int messageLength = 8;  // 假定从客户端接收8个字节
        //循环读取
        while (true) {
            int byteRead = 0;
            while (byteRead < messageLength) {
                long l = socketChannel.read(buffers);
                byteRead += l;  //累计读取的字节数
                System.out.println("byteRead = " + byteRead);
                // 使用流打印，看看当前的buffer的position和limit
                Arrays.stream(buffers).map(buffer ->
                        "buffer.position() = " + buffer.position() + "buffer.limit() = " + buffer.limit()).forEach(System.out::println);
            }

            // 将所有的buffer进行flip
            Arrays.stream(buffers).forEach(Buffer::flip);

            // 将数据读出显示到客户端
            long byteWirte = 0;
            while (byteWirte < messageLength) {
                long l = socketChannel.write(buffers);
                byteWirte += l;
            }

            Arrays.stream(buffers).forEach(Buffer::clear);
            System.out.println("byteRead = " + byteRead);
            System.out.println("byteWirte = " + byteWirte);
            System.out.println("messageLength = " + messageLength);
        }
    }

}
