package com.concept.maze.test.thread.pc4;

import java.util.ArrayList;
import java.util.List;

public class ProducerConsumerWithWaitNofity4 {

    public static void main(String[] args) {
        CookieList cookieList =new CookieList();
        P p1 = new P(cookieList);
        P p2 = new P(cookieList);

        C c = new C(cookieList);
        p1.start();
        p2.start();
        c.start();
        for (int i = 0; i < 3; ++i) {
            c = new C(cookieList);
            c.start();
//            p = new P(cookieList);
//            p.start();
        }
    }
}

class CookieList {
    public static final int MAX_SIZE = 5;
    private List<String> list = new ArrayList<String>();
    public synchronized void product() {
        try {
            while (list.size() == MAX_SIZE)
                this.wait();
            list.add("cookie" + ((int) (Math.random() * 10) + 1));
            System.out.println("生产者生产了" + list.get(0));
            this.notifyAll();
        } catch (InterruptedException e) {
        }
    }
    public synchronized void customer() {
        try {
            //注意while
            while (list.size() == 0)
                this.wait();
            System.out.println("消费者消费了:" + list.get(0));
            list.remove(0);
            this.notifyAll();
        } catch (InterruptedException e) {
        }
    }
}

//生产者线程
class P extends Thread {
    public CookieList cookieList;
    public P(CookieList cookieList) {
        this.cookieList = cookieList;
    }
    @Override
    public void run() {
        for (int i = 0; i <3 ; i++) {
            this.cookieList.product();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
    }
}

//消费者线程
class C extends Thread{
    public CookieList cookieList;
    public C(CookieList cookieList) {
        this.cookieList = cookieList;
    }
    @Override
    public void run() {
        for (int i =1; i < 3; i++) {
            this.cookieList.customer();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                // TODO 自动生成的 catch 块
                e.printStackTrace();
            }
        }
    }
}