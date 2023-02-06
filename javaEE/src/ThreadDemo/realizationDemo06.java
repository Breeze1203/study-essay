package ThreadDemo;

public class realizationDemo06 {
    public static void main(String[] args) {
        Thread t1=Thread.currentThread();
        System.out.println("验证在哪执行:"+t1);
        doSome();
        Thread t2=new Thread(){
            public void run(){
                Thread t3= Thread.currentThread();
                System.out.println("运行线程的地方是："+t3);
            }
        } ;
        t2.start();
    }

    public static void doSome() {
        Thread t2=Thread.currentThread();
        System.out.println("在这儿执行："+t2);
    }
}