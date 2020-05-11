package com.changda.tcp.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname NIOcLIENT
 * @description
 * @create 2020-04-20 16:58
 **/
public class NIOClient {
    public static void main(String[] args) throws IOException {

        // 得到一个网络通道
        SocketChannel socketChannel = SocketChannel.open();
        // 设置非阻塞模式
        socketChannel.configureBlocking(false);
        // 提供服务器的ip和端口
        InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 6666);

        // 连接服务器
        if (!socketChannel.connect(inetSocketAddress)) {
            while (!socketChannel.finishConnect()){
                System.out.println("因为连接需要时间，客户端不会阻塞，我们可以做其他工作");
            }
        }
        // ...如果连接成功，就发送数据
        String str = "hello,朱林";
        // 自动判断字节数组大小，生成一致大小的ByteBuffer并包裹传入的字节数组，这样就不需要自己指定大小了
        ByteBuffer buffer = ByteBuffer.wrap(str.getBytes());
        // 发送数据，将buffer 数据写入channel
        socketChannel.write(buffer);
        System.in.read();
    }
}
