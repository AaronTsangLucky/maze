package com.concept.maze.test.thread.pc;

public class ProducerConsumerWithWaitNofity {

    public static void main(String[] args) {
        Resource resource = new Resource();
        ProducerThread p1 = new ProducerThread("生产者No1",resource);
        ProducerThread p2 = new ProducerThread("生产者No2",resource);
//        ProducerThread p3 = new ProducerThread("生产者No3",resource);

        ConsumerThread c1 = new ConsumerThread("消费者No1", resource);
        ConsumerThread c2 = new ConsumerThread("消费者No2", resource);
        ConsumerThread c3 = new ConsumerThread("消费者No3", resource);

        p1.start();
        p2.start();
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

    public synchronized void add(int runTime) {
        while(num>=MAX_SIZE){
            try {
                System.out.println(Thread.currentThread().getName() +"("+runTime+"):货满!-->wait before-->"+num);
                this.wait();
                System.out.println(Thread.currentThread().getName() +"("+runTime+"):货满!-->wait after-->"+num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() +"("+runTime+"):"+num+"->"+(++num));
        this.notifyAll();
    }

    public synchronized void remove(int runTime) {
        while(num<=0){
            try {
                System.out.println(Thread.currentThread().getName() +"("+runTime+"):缺货!-->wait before-->"+num);
                this.wait();
                System.out.println(Thread.currentThread().getName() +"("+runTime+"):缺货!-->wait after-->"+num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName() +"("+runTime+"):"+num+"->"+(--num));
        this.notifyAll();
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
        for (int i = 1; i <=2; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.remove(i);
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
        for (int i = 1; i <= 2; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.add(i);
        }
    }

}
