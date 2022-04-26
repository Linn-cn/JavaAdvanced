package edu.changda.classloader.spi;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname SpiServiceImpl
 * @description
 * @create 2020-04-13 11:20
 **/
public class SpiServiceImpl implements SpiService{
    public SpiServiceImpl() {
        System.out.println("spi被加载");
    }

    @Override
    public void println() {
        System.out.println("测试Spi--------------");
    }
}
