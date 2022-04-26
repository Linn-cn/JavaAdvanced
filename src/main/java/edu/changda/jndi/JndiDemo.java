package edu.changda.jndi;

import javax.naming.Context;
import javax.naming.NamingException;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import java.util.Properties;

public class JndiDemo {
  public static void main(String[] args) {
    // 创建环境变量
    Properties env = new Properties();
    // JNDI初始化工厂类
    env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.dns.DnsContextFactory");
    // // JNDI提供服务的URL
    // env.put(Context.PROVIDER_URL, "url");
    try {
      // 创建JNDI服务对象
      DirContext context = new InitialDirContext(env);
      context.getEnvironment().forEach((k,v) -> {
        System.out.printf("k:%s，v:%s \n",k,v);
      });
    } catch (NamingException e) {
      e.printStackTrace();
    }
  }
}
