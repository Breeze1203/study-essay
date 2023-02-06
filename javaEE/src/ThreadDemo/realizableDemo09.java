package ThreadDemo;

public class realizableDemo09 {
    public static void main(String[] args) throws InterruptedException {
        Thread a1 = new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("我喜欢你");
                }
            }
        };
        Thread a2=new Thread(){
            public void run(){
                for(int i=0;i<10;i++){
                    System.out.println("我不喜欢你");
                }
            }
        };
        a1.sleep(1000);
        a1.start();
        a2.start();
    }
}