package com.ding.springcloud.service.spi;

import java.util.ServiceLoader;

public class SPIMain {
    public static void main(String[] args) {
        ServiceLoader<IShout> shouts = ServiceLoader.load(IShout.class);
        for (IShout s : shouts) {
            if(s instanceof Cat){
                System.out.println("cat");
            }
            s.shout();
        }
    }
}