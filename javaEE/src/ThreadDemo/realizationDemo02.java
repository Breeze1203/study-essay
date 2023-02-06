package ThreadDemo;

public class realizationDemo02 {
    public static void main(String[] args) {
        new Thread(){
            public void run(){
                for(int i=0;i<100;i++){
                    System.out.println("你是谁啊？");
                }
                for(int i=0;i<100;i++){
                    System.out.println("修水表的");
                }
            }
        }.start();
    }

}
