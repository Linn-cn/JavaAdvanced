package com.changda.tcp.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.*;
import java.util.Iterator;
import java.util.Set;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname NIOServer
 * @description NIO非阻塞案例
 * @create 2020-04-18 20:02
 **/
public class NIOServer {
    public static void main(String[] args) throws IOException {
        // 创建ServerSocketChannel -> ServerSocket
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        // 绑定一个端口6666，在服务器端监听
        serverSocketChannel.socket().bind(new InetSocketAddress(6666));
        // 设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);

        // 得到一个selector对象
        Selector selector = Selector.open();
        // 把 serverSocketChannel 注册到 selector 关心事件为OP_ACCEPT
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("selector.keys() = " + selector.keys().size());

        // 循环等待客户端连接
        while(true){
            // 等待一秒，如果没有事件发生
            if (selector.select(1000) == 0){
                System.out.println("无连接就不等了");
                continue;
            }

            // 如果返回的大于0 获取有时间发生的key
            Set<SelectionKey> selectionKeySet = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeySet.iterator();
            while (iterator.hasNext()){
                SelectionKey key = iterator.next();
                // 根据key 对应的通道进行相应的处理
                if (key.isAcceptable()) {   // 如果是OP_ACCEPT说明有客户端来连接
                    // 给该客户端生成一个SocketChannel
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    socketChannel.configureBlocking(false);
                    System.out.println("客户端连接成功，生成了一个socketChanel：" + socketChannel.hashCode());
                    // 将当前的socketChannel注册到selector,关注事件为OP_READ,同时关联一个Buffer
                    socketChannel.register(selector,SelectionKey.OP_READ, ByteBuffer.allocate(1024));
                    System.out.println("selector.keys() = " + selector.keys().size());
                }
                // 发生了OP_READ
                if (key.isReadable()){
                    // 通过key反向获取到对应Channel
                    SocketChannel channel = (SocketChannel) key.channel();
                    // 获取到该channel关联的buffer
                    ByteBuffer buffer = (ByteBuffer) key.attachment();
                    channel.read(buffer);
                    System.out.println("form 客户端" + new String(buffer.array()));
                }
                // 手动从集合中移除当前的selectionKey
                iterator.remove();
            }
        }
    }
}
