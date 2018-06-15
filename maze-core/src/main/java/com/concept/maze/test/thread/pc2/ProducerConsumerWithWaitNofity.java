package com.concept.maze.test.thread.pc2;

/**此生产者消费者模式存在问题,看程序打印的过程,得知消费者线程已经跑完20次。
 * 1.消费者No1在消费者No1(20)消费了生产者生产的数据,此时所有的消费者线程已完结.但还有个生产者线程没有跑完:生产者No2(19),生产者No1(20),生产者No2(20).
 * 2.此时 生产者No2(19):0->1.但是已经没有消费者了。
 * 3.按照程序逻辑 生产者No1(20),生产者No2(20)都将进入wait set.线程无法终结，生产者No1(20),生产者No2(20)一直处于waiting,没有其他线程对其notify.
 * 消费者No1(1):缺货!-->wait before-->0
 * 消费者No2(1):缺货!-->wait before-->0
 * 生产者No1(1):0->1
 * 生产者No2(1):货满!-->wait before-->1
 * 消费者No2(1):缺货!-->wait after-->1
 * 消费者No1(1):缺货!-->wait after-->1
 * 生产者No1(2):货满!-->wait before-->1
 * 消费者No2(2):1->0
 * 消费者No1(2):缺货!-->wait before-->0
 * 生产者No1(2):货满!-->wait after-->0
 * 生产者No2(1):货满!-->wait after-->0
 * 生产者No2(2):0->1
 * 消费者No1(2):缺货!-->wait after-->1
 * 生产者No1(3):货满!-->wait before-->1
 * 消费者No2(3):1->0
 * 生产者No1(3):货满!-->wait after-->0
 * 生产者No1(4):0->1
 * 生产者No2(3):货满!-->wait before-->1
 * 消费者No2(4):1->0
 * 消费者No1(3):缺货!-->wait before-->0
 * 生产者No2(3):货满!-->wait after-->0
 * 生产者No2(4):0->1
 * 消费者No1(3):缺货!-->wait after-->1
 * 生产者No1(5):货满!-->wait before-->1
 * 消费者No2(5):1->0
 * 生产者No1(5):货满!-->wait after-->0
 * 消费者No2(6):缺货!-->wait before-->0
 * 生产者No1(6):0->1
 * 生产者No2(5):货满!-->wait before-->1
 * 消费者No2(6):缺货!-->wait after-->1
 * 消费者No1(4):1->0
 * 生产者No2(5):货满!-->wait after-->0
 * 消费者No1(5):缺货!-->wait before-->0
 * 生产者No2(6):0->1
 * 消费者No1(5):缺货!-->wait after-->1
 * 生产者No1(7):货满!-->wait before-->1
 * 消费者No2(7):1->0
 * 生产者No1(7):货满!-->wait after-->0
 * 消费者No1(6):缺货!-->wait before-->0
 * 消费者No2(8):缺货!-->wait before-->0
 * 生产者No1(8):0->1
 * 生产者No2(7):货满!-->wait before-->1
 * 消费者No2(8):缺货!-->wait after-->1
 * 消费者No1(6):缺货!-->wait after-->1
 * 消费者No2(9):1->0
 * 生产者No2(7):货满!-->wait after-->0
 * 消费者No1(7):缺货!-->wait before-->0
 * 生产者No1(9):0->1
 * 消费者No1(7):缺货!-->wait after-->1
 * 消费者No2(10):1->0
 * 生产者No1(10):0->1
 * 消费者No1(8):1->0
 * 生产者No2(8):0->1
 * 消费者No2(11):1->0
 * 消费者No1(9):缺货!-->wait before-->0
 * 生产者No2(9):0->1
 * 生产者No1(11):货满!-->wait before-->1
 * 消费者No1(9):缺货!-->wait after-->1
 * 消费者No2(12):1->0
 * 生产者No1(11):货满!-->wait after-->0
 * 消费者No1(10):缺货!-->wait before-->0
 * 生产者No2(10):0->1
 * 消费者No1(10):缺货!-->wait after-->1
 * 生产者No1(12):货满!-->wait before-->1
 * 消费者No1(11):1->0
 * 消费者No2(13):缺货!-->wait before-->0
 * 生产者No2(11):0->1
 * 消费者No2(13):缺货!-->wait after-->1
 * 生产者No1(12):货满!-->wait after-->1
 * 消费者No1(12):1->0
 * 消费者No2(14):缺货!-->wait before-->0
 * 生产者No1(13):0->1
 * 生产者No2(12):货满!-->wait before-->1
 * 消费者No2(14):缺货!-->wait after-->1
 * 生产者No1(14):货满!-->wait before-->1
 * 消费者No2(15):1->0
 * 消费者No1(13):缺货!-->wait before-->0
 * 生产者No1(14):货满!-->wait after-->0
 * 生产者No2(12):货满!-->wait after-->0
 * 消费者No2(16):缺货!-->wait before-->0
 * 生产者No2(13):0->1
 * 生产者No1(15):货满!-->wait before-->1
 * 消费者No2(16):缺货!-->wait after-->1
 * 消费者No1(13):缺货!-->wait after-->1
 * 消费者No1(14):1->0
 * 生产者No1(15):货满!-->wait after-->0
 * 生产者No2(14):0->1
 * 消费者No2(17):1->0
 * 消费者No2(18):缺货!-->wait before-->0
 * 消费者No1(15):缺货!-->wait before-->0
 * 生产者No2(15):0->1
 * 生产者No1(16):货满!-->wait before-->1
 * 消费者No1(15):缺货!-->wait after-->1
 * 消费者No2(18):缺货!-->wait after-->1
 * 生产者No2(16):货满!-->wait before-->1
 * 消费者No1(16):1->0
 * 消费者No2(19):缺货!-->wait before-->0
 * 生产者No2(16):货满!-->wait after-->0
 * 生产者No1(16):货满!-->wait after-->0
 * 生产者No2(17):0->1
 * 消费者No2(19):缺货!-->wait after-->1
 * 生产者No1(17):货满!-->wait before-->1
 * 消费者No1(17):1->0
 * 生产者No1(17):货满!-->wait after-->0
 * 消费者No2(20):缺货!-->wait before-->0
 * 消费者No1(18):缺货!-->wait before-->0
 * 生产者No1(18):0->1
 * 生产者No2(18):货满!-->wait before-->1
 * 消费者No1(18):缺货!-->wait after-->1
 * 消费者No2(20):缺货!-->wait after-->1
 * 消费者No1(19):1->0
 * 生产者No1(19):0->1
 * 生产者No2(18):货满!-->wait after-->1
 * 消费者No1(20):1->0
 * 生产者No2(19):0->1
 * 生产者No1(20):货满!-->wait before-->1
 * 生产者No2(20):货满!-->wait before-->1
 */
public class ProducerConsumerWithWaitNofity {

    public static void main(String[] args) {
        Resource resource = new Resource();
        ProducerThread p1 = new ProducerThread("生产者No1",resource);
        ProducerThread p2 = new ProducerThread("生产者No2",resource);
//        ProducerThread p3 = new ProducerThread("生产者No.3",resource);

        ConsumerThread c1 = new ConsumerThread("消费者No1", resource);
        ConsumerThread c2 = new ConsumerThread("消费者No2", resource);
//        ConsumerThread c3 = new ConsumerThread("消费者No.3", resource);

        p1.start();
        p2.start();
//        p3.start();

        c1.start();
        c2.start();
//        c3.start();
    }

}

class Resource {//重要
    //当前资源数量
    private int num = 0;
    //资源池中允许存放的资源数目
    private static final int MAX_SIZE = 1;

    public synchronized void add(int runTime) {
        if (num>=MAX_SIZE){
            try {
                System.out.println(Thread.currentThread().getName() +"("+runTime+"):货满!-->wait before-->"+num);
                this.wait();
                System.out.println(Thread.currentThread().getName() +"("+runTime+"):货满!-->wait after-->"+num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else{
            System.out.println(Thread.currentThread().getName() +"("+runTime+"):"+num+"->"+(++num));
            this.notifyAll();
        }

    }

    public synchronized void remove(int runTime) {
        if(num<=0){
            try {
                System.out.println(Thread.currentThread().getName() +"("+runTime+"):缺货!-->wait before-->"+num);
                this.wait();
                System.out.println(Thread.currentThread().getName() +"("+runTime+"):缺货!-->wait after-->"+num);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } else{
            System.out.println(Thread.currentThread().getName() +"("+runTime+"):"+num+"->"+(--num));
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
        for (int i = 1; i <=20 ; i++) {
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
        for (int i = 1; i <= 20; i++) {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.add(i);
        }
    }

}
