package edu.changda.regex.simple;

import org.junit.Test;

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
    public void test3(){
        Pattern pattern = Pattern.compile("1[3-9]\\d{9}");
        System.out.println(pattern.pattern());
        System.out.println(pattern.matcher("13973568055").matches());
    }
}
