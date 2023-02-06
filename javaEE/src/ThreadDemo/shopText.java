package ThreadDemo;

public class shopText {
    public static void main(String[] args) {
        Thread shop1=new Thread(){
            public void run(){
                Shop01.buy();
            }
        };
        Thread shop2=new Thread(){
          public void run(){
              Shop01.buy();
          }
        };
        shop1.start();
        shop2.start();
    }
}
class Shop01{

    public  static void buy(){
        System.out.println(Thread.currentThread().getName()+"正在挑选衣服");
        synchronized(Shop01.class) {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "正在试穿衣服");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println(Thread.currentThread().getName()+"挑选完毕，结账走人");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
