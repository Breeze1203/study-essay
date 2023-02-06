package ThreadDemo;

public class synchronizedDemo {
    public static void main(String[] args) {
        final Table table=new Table();
        Thread t1=new Thread(){
            public void run(){
                while(true){
                    int bean=table.getBeans();
                    System.out.println("豆子"+bean+","+"名字"+getName());
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        Thread t2=new Thread(){
            public void run(){
                while(true){
                    int bean =table.getBeans();
                    System.out.println("豆子"+bean+","+"名字"+getName());
                    try {
                        sleep(5000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        t1.start();
//        try {
//            t1.join();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        t2.start();
    }
}
class Table{
    private int beans=20;

    public synchronized int getBeans() {
        if(beans==0) {
            throw new RuntimeException("豆子没有了") ;
        };
        return beans--;
    }
}