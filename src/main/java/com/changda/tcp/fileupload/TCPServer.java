package com.changda.tcp.fileupload;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 文件上传服务器端，读取客户端上传的文件，保存到服务器的硬盘，给客户端回写"上传成功"
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        try (ServerSocket server = new ServerSocket(8888);
             Socket socket = server.accept();
             InputStream is = socket.getInputStream()
        ) {
            File file = new File("D:\\upload2");
            if (!file.exists()){
                file.mkdirs();
            }
            FileOutputStream fos = new FileOutputStream(file + "\\1.jpg");
            int len = 0;
            byte[] bytes = new byte[1024];
            while ((len = is.read(bytes)) != -1) {
                fos.write(bytes,0,len);
            }
            socket.getOutputStream().write("上传成功".getBytes());
        }
    }
}
