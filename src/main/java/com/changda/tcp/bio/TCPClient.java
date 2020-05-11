package com.changda.tcp.bio;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * TCP 通信的客户端：向服务器发送连接请求，给服务器发送数据，读取服务器回写的数据
 * 表示客户端的类
 * java.net.socket：此类实现客户端套接字。套接字是两台机器间通信的端点
 * 套接字：包含了IP地址和端口号的网络单位
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("localhost", 8888);
             OutputStream outputStream = socket.getOutputStream();
             InputStream ins = socket.getInputStream()) {
            outputStream.write("你好服务器".getBytes());

            byte[] bytes = new byte[1024];
            int len = ins.read(bytes);
            System.out.println(new String(bytes, 0, len));
//            OutputStream ops = socket.getOutputStream();
//            ops.write("收到谢谢".getBytes());
        }

    }
}
