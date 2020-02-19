package com.changda.tcp.bs;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @program: Java9New
 * @classname: BSServer
 * @description:
 * @author: 朱林
 * @create: 2019-12-21 17:37
 **/
public class BSServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(8888);
             Socket socket = server.accept();
             InputStream is = socket.getInputStream()) {
            byte[] bytes = new byte[1024];
            int len = 0;
            while ((len = is.read(bytes)) != -1) {
                System.out.println(new String(bytes, 0, len));
            }
        }
    }
}
