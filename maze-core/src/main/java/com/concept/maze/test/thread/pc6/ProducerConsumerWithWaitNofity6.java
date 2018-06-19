package com.concept.maze.test.thread.pc6;

import java.util.ArrayList;
import java.util.List;

/**
 * 生产者与消费者一生产多消费队列操作
 */
public class ProducerConsumerWithWaitNofity6 {

    public static void main(String[] args) {
        CookieList cookieList = new CookieList();
        P p = new P(cookieList);
        C c = new C(cookieList);
        p.start();
        c.start();
        for(int i=0;i<3;++i){
            c  = new C(cookieList);
            c.start();
        }
    }
}
class CookieList {
    public static final int MAX_SIZE = 1;
    private List<String> list = new ArrayList<String>();
    public synchronized void product(){
        try{
            if(list.size()==MAX_SIZE)
                this.wait();
            list.add("cookie"+((int)(Math.random()*10)+1));
            System.out.println("生产者生产了"+list.get(0));
            this.notify();
        }catch(InterruptedException e){
        }
    }
    public synchronized void customer(){
        try{
            //注意while
            while(list.size()==0)
                this.wait();
            System.out.println("消费者消费了:"+list.get(0));
            list.remove(0);
            this.notifyAll();
        }catch(InterruptedException e){
        }
    }
}

//生产者线程
class P extends Thread{
    public CookieList cookieList;
    public P(CookieList cookieList){
        this.cookieList = cookieList;
    }
    @Override
    public void run() {
        while(true){
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
    public C(CookieList cookieList){
        this.cookieList = cookieList;
    }
    @Override
    public void run() {
        while(true){
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