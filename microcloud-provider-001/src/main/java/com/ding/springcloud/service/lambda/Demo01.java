package com.ding.springcloud.service.lambda;

/**
 * 使用Lambda的前提
 *
 * 对应接口有且只有一个抽象方法！！！
 * 方法无参无返回
 */
public class Demo01 {
    public static void main(String[] args) {

        // 1.传统方式 需要new接口的实现类来完成对接口的调用
        IShareBike shareBike1 = new ShareBikeImpl();
        shareBike1.lesGo();
        // 2.匿名内部类使用
        IShareBike shareBike2 = new IShareBike() {
            @Override
            public void lesGo() {
                System.out.println("xiao lan go");
            }
        };
        // 3.无参无返回Lambda表达式
        IShareBike shareBike3= () -> { System.out.println("xiaocheng go");};
        // ()代表的就是方法，只是匿名的。因为接口只有一个方法所以可以反推出方法名
        shareBike3.lesGo();
        IShareBike shareBike4=()->System.out.println("xiaolan go");
        shareBike4.lesGo();


    }
}
class ShareBikeImpl implements  IShareBike{
    public void lesGo(){
        System.out.println("小黄 go");
    }
}
interface IShareBike{
    public void lesGo();
}
