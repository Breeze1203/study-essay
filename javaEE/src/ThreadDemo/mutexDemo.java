package ThreadDemo;

public class mutexDemo {
    public static void main(String[] args) {
        Boo boo=new Boo();
        Thread t1=new Thread(){
            public void run(){
                boo.A();
            }
        };
        Thread t2=new Thread(){
            public void run(){
                boo.A();
            }
        };
        t1.start();
        t2.start();
    }
}
class Boo{
    public synchronized void A(){
        System.out.println(Thread.currentThread().getName()+"正在执行A方法");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"A方法执行完毕");
    }
    public  void B(){
        System.out.println(Thread.currentThread().getName()+"正在执行B方法");
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName()+"B方法执行完毕");
    }
}
