package com.concept.maze.test.thread.pc2;

public class ProducerConsumerWithWaitNofity {

    public static void main(String[] args) {
        Resource resource = new Resource();
        ProducerThread p1 = new ProducerThread("生产者No.1",resource);
//        ProducerThread p2 = new ProducerThread("生产者No.2",resource);
//        ProducerThread p3 = new ProducerThread("生产者No.3",resource);

        ConsumerThread c1 = new ConsumerThread("消费者No.1", resource);
        ConsumerThread c2 = new ConsumerThread("消费者No.2", resource);
        ConsumerThread c3 = new ConsumerThread("消费者No.3", resource);

        p1.start();
//        p2.start();
//        p3.start();

        c1.start();
        c2.start();
        c3.start();
    }

}

class Resource {//重要
    //当前资源数量
    private int num = 0;
    //资源池中允许存放的资源数目
    private static final int MAX_SIZE = 10;

    public synchronized void add() {
        if (num>=MAX_SIZE){
            try {
                System.out.println(Thread.currentThread().getName() +":货满!-->wait before-->"+num);
                this.wait();
                System.out.println(Thread.currentThread().getName() +":货满!-->wait after-->"+num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else{
            System.out.println(Thread.currentThread().getName() + ":"+(++num));
            this.notifyAll();
        }

    }

    public synchronized void remove() {
        if(num<=0){
            try {
                System.out.println(Thread.currentThread().getName() +":缺货!-->wait before-->"+num);
                this.wait();
                System.out.println(Thread.currentThread().getName() +":缺货!-->wait after-->"+num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else{
            System.out.println(Thread.currentThread().getName()+":"+(num--));
            this.notifyAll();
        }
    }
}


class ConsumerThread extends Thread {
    private Resource resource;

    public ConsumerThread(String name, Resource resource) {
        super(name);
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20 ; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.remove();
        }
    }
}

class ProducerThread extends Thread {
    private Resource resource;

    public ProducerThread(String name, Resource resource) {
        super(name);
        this.resource = resource;
    }

    @Override
    public void run() {
        for (int i = 0; i < 20; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.add();
        }
    }

}
