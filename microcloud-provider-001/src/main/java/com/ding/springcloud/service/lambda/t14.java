package com.ding.springcloud.service.lambda;

public class t14 {
    public static void main(String[] args) {
        try {
            MyThread14 thread = new MyThread14();
            thread.start();
            Thread.sleep(100); //此方法代表 让当前线程休眠 1 秒，即表示使 main线程休眠 1秒
            thread.interrupt();
            thread.interrupt();
            System.out.println("11111111111111111111111111111111111111111111111111111111111111111111111是否终止1？ =" + thread.isInterrupted());
            System.out.println("222222222222222222222222222222222222222222222222222是否终止2？ =" + thread.isInterrupted());
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println("----------end-----------");
    }
}

class MyThread14 extends Thread {
    public void run() {
        for (int i = 0; i < 50000; i++) {
            System.out.println("i = " + i);
        }
    }
}