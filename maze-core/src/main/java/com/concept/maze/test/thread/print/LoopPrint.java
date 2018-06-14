package com.concept.maze.test.thread.print;

public class LoopPrint extends Thread{

    private LoopPrintFlag loopPrintFlag;

    private String currentWord;

    private String nextWord;

    private int maxCount;

    public LoopPrint(String name, LoopPrintFlag loopPrintFlag, int maxCount, String currentWord, String nextWord){
        super(name);
        this.loopPrintFlag = loopPrintFlag;
        this.maxCount = maxCount;
        this.currentWord = currentWord;
        this.nextWord = nextWord;
    }

    @Override
    public void run() {
        while (maxCount>0){
            synchronized (loopPrintFlag){
                if(currentWord.equalsIgnoreCase(loopPrintFlag.flag)){
                    maxCount--;
                    System.out.println(currentWord);
                    loopPrintFlag.flag = nextWord;
                    loopPrintFlag.notifyAll();
                }else {
                    try {
                        loopPrintFlag.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }

    }

    public static void main(String[] args) {
        System.out.println("Main begin ......");
        LoopPrintFlag loopPrintFlag = new LoopPrintFlag("A");
        LoopPrint la = new LoopPrint("LoopA", loopPrintFlag, 10, "A", "B");
        LoopPrint lb = new LoopPrint("LoopA", loopPrintFlag, 10, "B", "A");
//        LoopPrint lc = new LoopPrint("LoopA", loopPrintFlag, 10, "C", "D");
//        LoopPrint ld = new LoopPrint("LoopA", loopPrintFlag, 10, "D", "A");
        la.start();
        lb.start();
//        lc.start();
//        ld.start();
    }
}

class LoopPrintFlag{
    public String flag;

    public LoopPrintFlag(String flag){
        this.flag = flag;
    }

}
