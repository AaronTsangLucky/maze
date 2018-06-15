package com.concept.maze.test.thread.pc1;

/**
 * 此为由问题的生产者消费者 示例演示.没有使用wait/notify/notifyAll同步机制
 * 消费者No1(1):缺货!-->wait before-->0
 * 消费者No1(2):缺货!-->wait before-->0
 * 消费者No1(3):缺货!-->wait before-->0
 * 消费者No1(4):缺货!-->wait before-->0
 * 消费者No1(5):缺货!-->wait before-->0
 * 消费者No1(6):缺货!-->wait before-->0
 * 消费者No1(7):缺货!-->wait before-->0
 * 消费者No1(8):缺货!-->wait before-->0
 * 消费者No1(9):缺货!-->wait before-->0
 * 消费者No1(10):缺货!-->wait before-->0
 * 消费者No1(11):缺货!-->wait before-->0
 * 消费者No1(12):缺货!-->wait before-->0
 * 消费者No1(13):缺货!-->wait before-->0
 * 消费者No1(14):缺货!-->wait before-->0
 * 消费者No1(15):缺货!-->wait before-->0
 * 消费者No1(16):缺货!-->wait before-->0
 * 消费者No1(17):缺货!-->wait before-->0
 * 消费者No1(18):缺货!-->wait before-->0
 * 消费者No1(19):缺货!-->wait before-->0
 * 消费者No1(20):缺货!-->wait before-->0
 * 生产者No1(1):0->1
 * 生产者No1(2):1->2
 * 生产者No1(3):2->3
 * 生产者No1(4):3->4
 * 生产者No1(5):4->5
 * 生产者No1(6):5->6
 * 生产者No1(7):6->7
 * 生产者No1(8):7->8
 * 生产者No1(9):8->9
 * 生产者No1(10):9->10
 * 生产者No1(11):货满!-->10
 * 生产者No1(12):货满!-->10
 * 生产者No1(13):货满!-->10
 * 生产者No1(14):货满!-->10
 * 生产者No1(15):货满!-->10
 * 生产者No1(16):货满!-->10
 * 生产者No1(17):货满!-->10
 * 生产者No1(18):货满!-->10
 * 生产者No1(19):货满!-->10
 * 生产者No1(20):货满!-->10
 */
public class ProducerConsumerWithWaitNofity1 {

    public static void main(String[] args) {
        Resource resource = new Resource();
        ProducerThread p1 = new ProducerThread("生产者No1",resource);

        ConsumerThread c1 = new ConsumerThread("消费者No1", resource);

        p1.start();
        c1.start();
    }

}

class Resource {//重要
    //当前资源数量
    private int num = 0;
    //资源池中允许存放的资源数目
    private static final int MAX_SIZE = 10;

    public synchronized void add(int runTime) {
        if (num>=MAX_SIZE){
            System.out.println(Thread.currentThread().getName() +"("+runTime+"):货满!-->"+num);
        }else {
            System.out.println(Thread.currentThread().getName() +"("+runTime+"):"+num+"->"+(++num));
        }
    }

    public synchronized void remove(int runTime) {
        if(num<=0){
            System.out.println(Thread.currentThread().getName() +"("+runTime+"):缺货!-->wait before-->"+num);
        } else{
            System.out.println(Thread.currentThread().getName() +"("+runTime+"):"+num+"->"+(--num));
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
        for (int i = 1; i <=20 ; i++) {
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
        for (int i = 1; i <= 20; i++) {
            resource.add(i);
        }
    }

}
