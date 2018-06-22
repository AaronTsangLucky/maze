package com.concept.maze.test.temp;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;

public class TestTemp{
    public static void main(String[] args) {
        HelloConcurrent hc = new HelloConcurrent();
        for (int i = 0; i < 4; i++) {
            new Thread(hc).start();
        }
    }
}

class HelloConcurrent implements Runnable{

    private static CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();

    static {
        list.add("AA");
        list.add("BB");
        list.add("CC");
    }

    @Override
    public void run() {
        Iterator it = list.iterator();
        while (it.hasNext()){
            System.out.println(it.next());
            list.add("AA");
        }
    }
}
