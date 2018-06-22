package com.concept.maze.test.thread.volatiles;

public class TestVolatile2 {

    public  int inc = 0;

    public synchronized void increase() {
        inc=inc+1;
    }

    public static void main(String[] args) {
        final TestVolatile2 test = new TestVolatile2();
        for(int i=0;i<10;i++){
            new Thread(){
                public void run() {
                    for(int j=0;j<1000;j++)
                        test.increase();
                };
            }.start();
        }

        while(Thread.activeCount()>2)  //保证前面的线程都执行完
            Thread.yield();
        System.out.println(test.inc);
    }

}

