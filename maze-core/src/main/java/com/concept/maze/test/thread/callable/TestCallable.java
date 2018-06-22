package com.concept.maze.test.thread.callable;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class TestCallable {

    public static void main(String[] args) {
        long begin = System.currentTimeMillis();
        HellCallable hc = new HellCallable();
        FutureTask<Integer> ft1 = new FutureTask<>(hc);
        Thread t1 = new Thread(ft1);
        t1.start();

        FutureTask<Integer> ft2 = new FutureTask<>(hc);
        Thread t2 = new Thread(ft2);
        t2.start();

        System.out.println("==========================");
        try {
            System.out.println("计算结果"+ft1.get());
            long end = System.currentTimeMillis();

            System.out.println("=========================="+(end-begin));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}


class HellCallable implements Callable<Integer>{

    @Override
    public Integer call() throws Exception {
        int sum = 0;
        for (int i = 0; i < 10000; i++) {
            Thread.sleep(1);
            sum+=i;
        }
        return sum;
    }
}
