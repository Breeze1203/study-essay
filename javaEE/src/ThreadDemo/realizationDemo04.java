package ThreadDemo;

public class realizationDemo04 {
    public static void main(String[] args) {
        new Thread(new Runnable(){

            @Override
            public void run() {
               for(int i=1;i<100;i++){
                   System.out.println("你是谁？");
               }
               for(int i=1;i<100;i++){
                   System.out.println("修水表的");
               }
            }
        }).start();
    }
}
