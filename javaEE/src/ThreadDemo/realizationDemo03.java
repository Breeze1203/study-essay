package ThreadDemo;

public class realizationDemo03 {
    public static void main(String[] args) {
        myRunnable1 t1=new myRunnable1();
        myRunnable2 t2=new myRunnable2();
        Thread t3=new Thread(t1);
        Thread t4=new Thread(t2);
        t3.start();
        t4.start();
    }
}
class myRunnable1 implements Runnable{

    @Override
    public void run() {
        System.out.println("你是谁啊？");
    }
}
class myRunnable2 implements Runnable{

    @Override
    public void run() {
        System.out.println("修水表的");
    }
}