package ThreadDemo;

public class shopDemo {
    public static void main(String[] args) {
        Shop shop=new Shop();
        Thread shop1=new Thread(){
        public void run(){
            shop.buy();
        }
        };
        Thread shop2=new Thread(){
        public void run(){
            shop.buy();
        }
        };

        shop1.start();
        shop2.start();

    }
}
class Shop{
    public void buy(){
        Thread t=Thread.currentThread();
        System.out.println(t.getName()+"挑选衣服");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        synchronized(this) {
            System.out.println(t.getName() + "试穿衣服");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(t.getName() + "买完结账");
        }
    }
}
