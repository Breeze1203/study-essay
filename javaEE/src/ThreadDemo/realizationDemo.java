package ThreadDemo;

public class realizationDemo {
    public static void main(String[] args) {
        Thread1 a=new Thread1();
        Thread2 b=new Thread2();
        a.start();
        b.start();
    }
}
class Thread1 extends Thread{
    public void run(){
        for(int i=1;i<10;i++){
            System.out.println("你是谁?");
        }
    }
}
class Thread2 extends Thread{
    public void run(){
        for(int i=1;i<10;i++){
            System.out.println("我是修水表的！");
        }
    }
}
