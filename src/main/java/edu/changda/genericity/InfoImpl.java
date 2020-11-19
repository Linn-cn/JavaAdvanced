package edu.changda.genericity;

/**
 * @program: JucAndJvm
 * @classname: InfoImpl
 * @description:
 * @author: 南街
 * @create: 2020-01-13 10:14
 **/
public class InfoImpl implements Info<Integer> {
    @Override
    public Integer test(Integer key) {
        return 1;
    }
}
