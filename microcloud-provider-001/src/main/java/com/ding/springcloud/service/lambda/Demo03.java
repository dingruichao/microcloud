package com.ding.springcloud.service.lambda;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Demo03 {
    public static void  main(String[] args) {

        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer", "Roger Federer",
                "Andy Murray", "Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> players = Arrays.asList(atp);

        // 以前的循环方式
        for (String player : players) {
            System.out.print(player + "; ");
        }
        System.out.println();
        // 使用 lambda 表达式以及函数操作(functional operation)
        players.forEach((player) -> System.out.print(player + "// "));

        // 在 Java 8 中使用双冒号操作符(double colon operator)
        players.forEach(System.out::println);

        List<String> testList=new ArrayList();
        for(int i=0;i<100000;i++){
            testList.add("test"+i);
        }
        long begin=System.currentTimeMillis();
        for(int i=0;i<100000;i++){
            String a=testList.get(i);
           System.out.print(testList.get(i));
        }
        long end1=System.currentTimeMillis()-begin;
        System.out.println();
        System.out.println("for usetime="+end1);
        testList.forEach((ab) ->  System.out.print(ab));

        long end2=System.currentTimeMillis()-begin;
        System.out.println();
        System.out.println("forech usetime="+end2);

        //从结果看forecho的性能下降了 耗时多
    }
}
