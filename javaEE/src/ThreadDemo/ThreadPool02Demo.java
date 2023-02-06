package ThreadDemo;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool02Demo {
    public static void main(String[] args) {
        long start=System.currentTimeMillis();
        System.out.println("我喜欢你");
        long end=System.currentTimeMillis();
        System.out.println(end-start);
        ExecutorService threadPool= Executors.newCachedThreadPool();
        for(int i=0;i<100;i++){
            Runnable runn=new Runnable() {
                @Override
                public void run() {
                    Thread t=Thread.currentThread();
                    System.out.println("正在运行 : "+t.getName());
                    try {
                        Thread.sleep(3000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("正在运行 ： "+t.getName());
                }
            };
            threadPool.execute(runn);
        }
    }
}
