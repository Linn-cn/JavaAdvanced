package edu.changda.classloader.spi;

import java.util.ServiceLoader;

/**
 * @author 南街
 * @program JavaAdvanced
 * @classname SPIDemo
 * @description Java的SPI案例
 * @create 2020-04-13 11:17
 **/
    public class SPIDemo {
    public static void main(String[] args) {
        ServiceLoader<SpiService> load = ServiceLoader.load(SpiService.class);
        for (SpiService service : load) {
            service.println();
        }
    }
}
