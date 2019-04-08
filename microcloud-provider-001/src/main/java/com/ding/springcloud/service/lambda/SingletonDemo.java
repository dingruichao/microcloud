package com.ding.springcloud.service.lambda;

public class SingletonDemo {
    private static class SingletonHolder{
        private static SingletonDemo instance=new SingletonDemo();
    }
    private SingletonDemo(){
        System.out.println("Singleton has loaded");
    }
    public static SingletonDemo getInstance(){
        return SingletonHolder.instance;
    }

    public static void main(String[] args){
        SingletonDemo singletonDemo=SingletonDemo.getInstance();
        System.out.println();

    }
}