package com.changda.classloader.loader;

import java.io.*;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname MyFileClassLoader
 * @description 自定义文件类加载器
 * @create 2020-04-21 16:21
 **/
public class MyFileClassLoader extends ClassLoader {
    // 被加载类所在的目录
    private String directory;

    public MyFileClassLoader(String directory) {
        this.directory = directory;
    }

    public MyFileClassLoader(ClassLoader parent, String directory) {
        super(parent);
        this.directory = directory;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 把类名转换为目录
        try {
            String file = directory + File.separator + name.replace('.',File.separatorChar) + ".class";
            FileInputStream inputStream = new FileInputStream(file);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            byte[] buf = new byte[1024];
            int len = -1;
            while ((len = inputStream.read(buf)) != -1){
                outputStream.write(buf,0,len);
            }
            byte[] data = outputStream.toByteArray();
            inputStream.close();
            outputStream.close();
            return defineClass(name,data,0,data.length);
        } catch (IOException e) {
            throw new RuntimeException();
        }
    }
}
