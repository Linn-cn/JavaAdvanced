package edu.changda.regex.simple;

import org.junit.Test;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.util.regex.Pattern;

/**
 * 正则元字符
 * 1.特殊单字符
 * 2.空白符
 * 3.范围
 * 4.量词
 * 5.断言
 * @author Linn-cn
 * @create 2020/09/30
 */
public class BasicsDemo {

    @Test
    public void test1(){
        Pattern pattern = Pattern.compile("\\d{11}");
        System.out.println(pattern.pattern());
        System.out.println(pattern.matcher("17673169755").matches());
    }

    @Test
    public void test2(){
        Pattern pattern = Pattern.compile("(https?|ftp)://");
        System.out.println(pattern.pattern());
        System.out.println(pattern.matcher("http://www.baidu.com").matches());
    }

    @Test
    public void test3() throws UnsupportedEncodingException {
        BigInteger bigInteger = new BigInteger("110101", 2);
        System.out.println(bigInteger.toString());
        BigInteger bg = new BigInteger("53");
        System.out.println(bg.toString());
    }

    @Test
    public void test4(){
        System.out.println("139735{6}8055".replaceAll("[{}]",""));
    }
}
