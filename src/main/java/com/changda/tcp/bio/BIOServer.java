package com.changda.tcp.bio;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname BIOServer
 * @description
 * @create 2020-04-09 18:09
 **/
public class BIOServer {

    public static void main(String[] args) throws IOException {
        // 思路
        // 1. 创建一个线程池
        // 2. 如果有客户端连接，就创建一个线程，与之通讯（单独写一个方法）
        ExecutorService pool = Executors.newCachedThreadPool();

        // 创建ServerSocket
        ServerSocket serverSocket = new ServerSocket(6666);

        System.out.println("服务器启动了");
        while (true) {
            // 监听，等待客户端连接
            System.out.println("getId() = " + Thread.currentThread().getId());
            System.out.println("getName() = " + Thread.currentThread().getName());
            System.out.println("等待连接");
            Socket socket = serverSocket.accept();
            System.out.println("连接到一个客户端");

            // 就创建一个线程，与之通讯(单独写一个方法)
            pool.execute(() -> {    // 我们重写
                // 可以和客户端通讯
                handler(socket);
            });
        }
    }

    // 编写一个handler方法，和客户端通讯
    public static void handler(Socket socket){
        byte[] bytes = new byte[1024];
        // 通过socket获取输入流
        try (InputStream stream = socket.getInputStream()) {
            // 循环读取客户端发送的数据
            while (true){
                System.out.println("getId() = " + Thread.currentThread().getId());
                System.out.println("getName() = " + Thread.currentThread().getName());
                System.out.println("read...");
                int read = stream.read(bytes);
                if (read != -1){
                    System.out.println(new String(bytes,0,read));   // 输出客户端发送的数据
                }else {
                    break;
                }
            }
            System.out.println("关闭和client的连接");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
