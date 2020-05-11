package com.changda.tcp.bio;


import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * TCP通信的服务器端：接收客户端的请求，读取客户端发送的数据，给客户端回写数据
 * 表示服务器的类
 * java.net.ServerSocket
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(8888)) {
            Socket socket = server.accept();
            InputStream ins = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len = ins.read(bytes);
            System.out.println(new String(bytes, 0, len));
            OutputStream ops = socket.getOutputStream();
            ops.write("收到谢谢".getBytes());
        }
    }
}
