package com.ding.springcloud.service.lambda;

public class BaseTest {
 
    public static void main(String[] args){

        // 使用Lambda启动线程
        // 1.传统方式使用
        Thread t1 = new Thread(new MyRunnable());
        t1.start();

        // 2.匿名内部类使用
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 running...");
            }
        });
        t2.start();

        // 3.Lambda使用
        Thread t3 = new Thread(() -> System.out.println("t3 running..."));
        t3.start();
        // 更简写法1
        new Thread(() -> System.out.println("t4 running...")).start();
        // 更简写法2
        Process.process(()-> System.out.println("t5 running..."));
    }

}

class MyRunnable implements Runnable {

    @Override
    public void run() {
        System.out.println("t1 running...");
    }
}


interface Process {
    // Java8中允许接口中定义非抽象方法 前提该方法必须为 default 或 static
    static void process(Runnable runnable) {
        new Thread(runnable).start();
    }
}
