package ThreadDemo;

public class realizationDemo05 {
    public static void main(String[] args) {
        Runnable t1=new Runnable(){
            public void run(){
                for(int i=1;i<100;i++){
                    System.out.println("你是谁？");
                }
                for(int i=0;i<100;i++){
                    System.out.println("修水表的");
                }
            }
        };
        Thread t3=new Thread(t1);
        Thread t4=new Thread(t1);
        t3.start();
        t4.start();
    }
}
