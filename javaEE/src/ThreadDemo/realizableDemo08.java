package ThreadDemo;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class realizableDemo08 {
    public static void main(String[] args) throws ExecutionException, InterruptedException {
        Callable1 t1=new Callable1();
        Callable2 t2=new Callable2();
        FutureTask t3=new FutureTask(t1);
        FutureTask t4=new FutureTask(t2);
        new Thread(t3).start();
        new Thread(t4).start();
        System.out.println(t3.get());
        System.out.println(t4.get());
        System.out.println(new Thread(t3).getId());
        System.out.println(new Thread(t4).getId());
        System.out.println(new Thread(t3).getName());
        System.out.println(new Thread(t3).getPriority());
        System.out.println(new Thread().getState());
    }
}
class Callable1 implements Callable<String> {
    public String call() throws Exception {
        String str="我喜欢你";
        return str;
    }
}
class Callable2 implements Callable<Integer> {
    public Integer call(){
        int obj=10;
        while(obj>0){
            obj--;
        }
        return obj;
    }
}