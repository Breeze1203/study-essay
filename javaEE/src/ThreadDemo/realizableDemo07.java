package ThreadDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.FutureTask;

public class realizableDemo07 {
    public static void main(String[] args) {
        MyCalleable t1=new MyCalleable(10);
        MyCallable1 t3=new MyCallable1(50);
        FutureTask t2=new FutureTask(t1);
        FutureTask t5=new FutureTask(t3);
        Thread t4=new Thread(t2);
        Thread t6=new Thread(t5);
        t4.start();
        t6.start();

    }
}
class MyCalleable implements Callable<Object> {
    private int stick;

    MyCalleable(int stick) {
        this.stick = stick;
    }
    public Integer call() throws Exception {
        int result;
        while(stick>0){
            System.out.println("票有"+stick+"张");
            stick--;
        }
        return stick;
    }
}
class MyCallable1 implements Callable<Object> {
    private Integer obj;

    MyCallable1(Integer obj) {
        this.obj = obj;
    }

    @Override
    public Integer call() throws Exception {
        int obj=10 ;
        while(obj>0){
            obj--;
        }

        return obj;
    }
}