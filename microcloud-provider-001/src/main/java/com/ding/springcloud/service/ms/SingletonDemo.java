package com.ding.springcloud.service.ms;

public class SingletonDemo {
    private static SingletonDemo instance =null;
    private SingletonDemo(){}


    public static SingletonDemo getInstance(){
        if(instance==null){
            synchronized (SingletonDemo.class){
                if(instance==null){
                    instance=new SingletonDemo();
                }
            }
        }
        return instance;
    }
    
}