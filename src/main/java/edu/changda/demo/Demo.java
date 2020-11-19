package edu.changda.demo;

import java.util.Arrays;

/**
 * @author Linn-cn
 * @create 2020/10/26
 */
public class Demo {
    public static void main(String[] args) {
        // System.out.println(Character.toChars(91 - 1));
        // for (int i = 0; i < 100; i++ ){
        //     System.out.println(intToAZ(701));
        // }
        // System.out.println(strToBytes("000c29be-6c340000-5a0ec7f6-21ae8e6b"));
        System.out.println(-1%3);
        System.out.println((double)-1/3);
    }

    /**
     * 代码中的类名、方法名、参数名已经指定，请勿修改，直接返回方法规定的值即可
     * 将整数转换成A-Z编号的字符串
     *
     * @param n int整型 大于0的整数
     * @return string字符串
     */
    public static String intToAZ(int n) {
        if (n <= 0) {
            return "";
        }
        StringBuilder builder = new StringBuilder();
        for (; n > 26; n -= 26) {
            builder.append("A");
        }
        builder.append(Character.toChars(64 + n));
        return builder.toString();
    }

    public static String strToBytes(String hexString) {
        hexString = hexString.replace("-", "");
        // 如果长度不是偶数，就在后面添加空格
        if ((hexString.length() % 2) != 0) {
            hexString += " ";
        }
        // 定义一个数组,长度是待转换数组长度的一半
        int[] returnBytes = new int[hexString.length() / 2];
        for (int i = 0; i < returnBytes.length; i++) {
            returnBytes[i] = (0xff & Integer.parseInt(hexString.substring(i * 2, i * 2 + 2), 16));
        }
        return Arrays.toString(returnBytes);
        // write code here
    }

    public static String toHexString(String s) {
        String str = "";
        for (int i = 0; i < s.length(); i++) {
            int ch = s.charAt(i);
            String s4 = Integer.toHexString(ch);
            str = str + s4;
        }
        return str;
    }

}
