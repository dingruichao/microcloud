package com.ding.springcloud.service.ms;

interface Source{ void method();}

/**
 * 代理模式（Proxy）
 客户端通过代理类访问，代理类实现具体的实现细节，客户只需要使用代理类即可实现操作。

 这种模式可以对旧功能进行代理，用一个代理类调用原有的方法，且对产生的结果进行控制。
 */
class OldClass implements Source{
    @Override
    public void method() {
    }
}

class Proxy implements Source{
    private Source source = new OldClass();

    void doSomething(){}
    @Override
    public void method() {
        new Class1().func1();
        source.method();
        new Class2().func2();
        doSomething();
    }
}
class Class1 {
    public void func1(){

    }
}
class Class2 {
    public void func2(){

    }
}