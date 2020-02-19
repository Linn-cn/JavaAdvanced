package com.changda.tcp.fileupload;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

public class TCPClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 8888);
        int len;
        byte[] bytes;
        // 创建本地字节输入流
        // 创建网络字节输出流
        // 创建网络字节输入流
        try (FileInputStream fis = new FileInputStream("D:\\timg.jpg");
             OutputStream os = socket.getOutputStream();
             InputStream is = socket.getInputStream()) {
            len = 0;
            bytes = new byte[1024];
            // 这里会陷入阻塞
            while ((len = fis.read(bytes)) != -1) {
                os.write(bytes, 0, len);
            }
            // 解决办法，写入完成之后，调用
            socket.shutdownOutput();

            while ((len = is.read(bytes)) != -1) {
                System.out.println(new String(bytes,0,len));
            }
        }
    }
}
