package com.concept.maze.test.thread.pc3;

/**
 * 此生产者消费者模式有虚假唤醒问题，
 * 虚假唤醒解决方案 wait()应该总是在循环中使用：
 * synchronized(obj){
 *     while(condiong){
 *         obj.wait();
 *     }
 *     ...//Perform action appropriate to condition
 * }
 *
 * 如果只有一个生产者，消费者貌似没有问题。
 * 但是如果有多个生产者或消费者就会有问题。
 * 请看程序过程打印.
 * D:\DevInstall\Java\jdk1.8.0_131\bin\java.exe "-javaagent:D:\DevInstall\IntelliJ IDEA 2018.1.4\lib\idea_rt.jar=59632:D:\DevInstall\IntelliJ IDEA 2018.1.4\bin" -Dfile.encoding=UTF-8 -classpath D:\DevInstall\Java\jdk1.8.0_131\jre\lib\charsets.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\deploy.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\ext\access-bridge-64.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\ext\bcprov-jdk15on-1.55.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\ext\cldrdata.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\ext\dnsns.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\ext\jaccess.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\ext\jfxrt.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\ext\localedata.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\ext\nashorn.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\ext\sunec.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\ext\sunjce_provider.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\ext\sunmscapi.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\ext\sunpkcs11.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\ext\zipfs.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\javaws.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\jce.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\jfr.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\jfxswt.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\jsse.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\management-agent.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\plugin.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\resources.jar;D:\DevInstall\Java\jdk1.8.0_131\jre\lib\rt.jar;D:\IdeaNewProjects\maze\maze-core\target\classes com.concept.maze.test.thread.pc3.ProducerConsumerWithWaitNofity3
 * 生产者No1(1):0->1
 * 消费者No2(1):1->0
 * 消费者No3(1):缺货!-->wait before-->0
 * 消费者No1(1):缺货!-->wait before-->0
 * 生产者No1(2):0->1
 * 消费者No1(1):缺货!-->wait after-->1
 * 消费者No1(1):1->0
 * 消费者No3(1):缺货!-->wait after-->0
 * 消费者No3(1):0->-1
 * 消费者No2(2):缺货!-->wait before-->-1
 * 生产者No1(3):-1->0
 * 消费者No2(2):缺货!-->wait after-->0
 * 消费者No2(2):0->-1
 * 消费者No3(2):缺货!-->wait before-->-1
 * 消费者No1(2):缺货!-->wait before-->-1
 * 生产者No1(4):-1->0
 * 消费者No2(3):缺货!-->wait before-->0
 * 消费者No1(2):缺货!-->wait after-->0
 * 消费者No1(2):0->-1
 * 消费者No3(2):缺货!-->wait after-->-1
 * 消费者No3(2):-1->-2
 * 消费者No2(3):缺货!-->wait after-->-2
 * 消费者No2(3):-2->-3
 * 消费者No3(3):缺货!-->wait before-->-3
 * 生产者No1(5):-3->-2
 * 消费者No3(3):缺货!-->wait after-->-2
 * 消费者No3(3):-2->-3
 * 消费者No2(4):缺货!-->wait before-->-3
 * 消费者No1(3):缺货!-->wait before-->-3
 * 消费者No3(4):缺货!-->wait before-->-3
 * 生产者No1(6):-3->-2
 * 消费者No3(4):缺货!-->wait after-->-2
 * 消费者No3(4):-2->-3
 * 消费者No1(3):缺货!-->wait after-->-3
 * 消费者No1(3):-3->-4
 * 消费者No2(4):缺货!-->wait after-->-4
 * 消费者No2(4):-4->-5
 * 生产者No1(7):-5->-4
 * 消费者No3(5):缺货!-->wait before-->-4
 * 消费者No1(4):缺货!-->wait before-->-4
 * 消费者No2(5):缺货!-->wait before-->-4
 * 生产者No1(8):-4->-3
 * 消费者No2(5):缺货!-->wait after-->-3
 * 消费者No2(5):-3->-4
 * 消费者No1(4):缺货!-->wait after-->-4
 * 消费者No1(4):-4->-5
 * 消费者No3(5):缺货!-->wait after-->-5
 * 消费者No3(5):-5->-6
 * 生产者No1(9):-6->-5
 * 消费者No1(5):缺货!-->wait before-->-5
 * 消费者No2(6):缺货!-->wait before-->-5
 * 消费者No3(6):缺货!-->wait before-->-5
 * 生产者No1(10):-5->-4
 * 消费者No3(6):缺货!-->wait after-->-4
 * 消费者No3(6):-4->-5
 * 消费者No2(6):缺货!-->wait after-->-5
 * 消费者No2(6):-5->-6
 * 消费者No1(5):缺货!-->wait after-->-6
 * 消费者No1(5):-6->-7
 * 消费者No3(7):缺货!-->wait before-->-7
 * 消费者No1(6):缺货!-->wait before-->-7
 * 消费者No2(7):缺货!-->wait before-->-7
 * 生产者No1(11):-7->-6
 * 消费者No2(7):缺货!-->wait after-->-6
 * 消费者No2(7):-6->-7
 * 消费者No1(6):缺货!-->wait after-->-7
 * 消费者No1(6):-7->-8
 * 消费者No3(7):缺货!-->wait after-->-8
 * 消费者No3(7):-8->-9
 * 消费者No3(8):缺货!-->wait before-->-9
 * 消费者No2(8):缺货!-->wait before-->-9
 * 消费者No1(7):缺货!-->wait before-->-9
 * 生产者No1(12):-9->-8
 * 消费者No1(7):缺货!-->wait after-->-8
 * 消费者No1(7):-8->-9
 * 消费者No2(8):缺货!-->wait after-->-9
 * 消费者No2(8):-9->-10
 * 消费者No3(8):缺货!-->wait after-->-10
 * 消费者No3(8):-10->-11
 * 消费者No3(9):缺货!-->wait before-->-11
 * 消费者No2(9):缺货!-->wait before-->-11
 * 生产者No1(13):-11->-10
 * 消费者No2(9):缺货!-->wait after-->-10
 * 消费者No2(9):-10->-11
 * 消费者No3(9):缺货!-->wait after-->-11
 * 消费者No3(9):-11->-12
 * 消费者No1(8):缺货!-->wait before-->-12
 * 消费者No2(10):缺货!-->wait before-->-12
 * 生产者No1(14):-12->-11
 * 消费者No3(10):缺货!-->wait before-->-11
 * 消费者No2(10):缺货!-->wait after-->-11
 * 消费者No2(10):-11->-12
 * 消费者No1(8):缺货!-->wait after-->-12
 * 消费者No1(8):-12->-13
 * 消费者No3(10):缺货!-->wait after-->-13
 * 消费者No3(10):-13->-14
 * 消费者No1(9):缺货!-->wait before-->-14
 * 消费者No3(11):缺货!-->wait before-->-14
 * 生产者No1(15):-14->-13
 * 消费者No2(11):缺货!-->wait before-->-13
 * 消费者No3(11):缺货!-->wait after-->-13
 * 消费者No3(11):-13->-14
 * 消费者No1(9):缺货!-->wait after-->-14
 * 消费者No1(9):-14->-15
 * 消费者No2(11):缺货!-->wait after-->-15
 * 消费者No2(11):-15->-16
 * 消费者No1(10):缺货!-->wait before-->-16
 * 消费者No3(12):缺货!-->wait before-->-16
 * 消费者No2(12):缺货!-->wait before-->-16
 * 生产者No1(16):-16->-15
 * 消费者No2(12):缺货!-->wait after-->-15
 * 消费者No2(12):-15->-16
 * 消费者No3(12):缺货!-->wait after-->-16
 * 消费者No3(12):-16->-17
 * 消费者No1(10):缺货!-->wait after-->-17
 * 消费者No1(10):-17->-18
 * 消费者No1(11):缺货!-->wait before-->-18
 * 消费者No3(13):缺货!-->wait before-->-18
 * 消费者No2(13):缺货!-->wait before-->-18
 * 生产者No1(17):-18->-17
 * 消费者No2(13):缺货!-->wait after-->-17
 * 消费者No2(13):-17->-18
 * 消费者No3(13):缺货!-->wait after-->-18
 * 消费者No3(13):-18->-19
 * 消费者No1(11):缺货!-->wait after-->-19
 * 消费者No1(11):-19->-20
 * 生产者No1(18):-20->-19
 * 消费者No1(12):缺货!-->wait before-->-19
 * 消费者No2(14):缺货!-->wait before-->-19
 * 消费者No3(14):缺货!-->wait before-->-19
 * 生产者No1(19):-19->-18
 * 消费者No3(14):缺货!-->wait after-->-18
 * 消费者No3(14):-18->-19
 * 消费者No2(14):缺货!-->wait after-->-19
 * 消费者No2(14):-19->-20
 * 消费者No1(12):缺货!-->wait after-->-20
 * 消费者No1(12):-20->-21
 * 消费者No2(15):缺货!-->wait before-->-21
 * 生产者No1(20):-21->-20
 * 消费者No2(15):缺货!-->wait after-->-20
 * 消费者No2(15):-20->-21
 * 消费者No3(15):缺货!-->wait before-->-21
 * 消费者No1(13):缺货!-->wait before-->-21
 * 消费者No2(16):缺货!-->wait before-->-21
 */
public class ProducerConsumerWithWaitNofity3 {

    public static void main(String[] args) {
        Resource resource = new Resource();
        ProducerThread p1 = new ProducerThread("生产者No1",resource);
//        ProducerThread p2 = new ProducerThread("生产者No2",resource);
//        ProducerThread p3 = new ProducerThread("生产者No3",resource);

        ConsumerThread c1 = new ConsumerThread("消费者No1", resource);
        ConsumerThread c2 = new ConsumerThread("消费者No2", resource);
        ConsumerThread c3 = new ConsumerThread("消费者No3", resource);

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
        }
        System.out.println(Thread.currentThread().getName() +"("+runTime+"):"+num+"->"+(++num));
        this.notifyAll();
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
        for (int i = 1; i <=20 ; i++) {
            try {
                Thread.sleep(200);
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
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            resource.add(i);
        }
    }

}
