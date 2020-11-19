package edu.changda.classloader.loader;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname URLClassLoaderDemo
 * @description
 * @create 2020-04-21 15:59
 **/
public class URLClassLoaderDemo {
    public static void main(String[] args) throws MalformedURLException, ClassNotFoundException, IllegalAccessException, InstantiationException {
        File file = new File("");
        URI uri = file.toURI();
        URL url = uri.toURL();

        // 默认父类加载器是系统类加载器
        URLClassLoader classLoader = new URLClassLoader(new URL[]{url});
        Class<?> aClass = classLoader.loadClass("xxx");
        aClass.newInstance();
    }
}
