package edu.changda.jndi.rmi;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import java.util.Hashtable;

public class RmiClient {
  public static void main(String[] args) throws NamingException {
    System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase", "true");
    Hashtable<String, String> environment = new Hashtable<>();
    environment.put("测试环境变量", "1111");
    Context ctx = new InitialContext(environment);
    ctx.lookup("rmi://localhost:1099/evil");
  }
}
