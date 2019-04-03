package com.ding.springcloud.service.lambda;

/**
 * 使用Lambda的前提
 *
 * 对应接口有且只有一个抽象方法！！！
 * 方法有参有返回值
 */
public class Demo02 {
    public static void main(String[] args) {

        // 1.有参无返回
        IEat eat1 = (String thing) -> System.out.println("eat " + thing);
        eat1.eat("apple");

        // 参数数据类型可以省略
        IEat eat2 = (thing) -> System.out.println("eat " + thing);
        eat2.eat("banana");

        // 2.多个参数
        ISpeak speak1 = (who, content) -> System.out.println(who + " talk " + content);
        speak1.talk("xinzong", "hello word");

        // 3.返回值
        IRun run1 = () -> {return 10;};
        run1.run();

        // 4.返回值简写
        IRun run2 = () -> 10;
        run2.run();
    }

}

interface IEat {

    void eat(String thing);
}

interface ISpeak {
    void talk(String who, String content);
}

interface IRun {

    int run();
}
