package ThreadDemo;

public class jackAndroseDemo {
    public static void main(String[] args) {
        Thread rose = new Thread() {
            public void run() {
                for (int i = 0; i < 10; i++) {
                    System.out.println("let me jumpe");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("啊啊啊啊啊啊");
                System.out.println("噗通,,,,,");
            }
        };
        Thread jack = new Thread() {
            public void run() {
                while (true) {
                    System.out.println("you jumpe ,i will jumpe");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        };
        jack.setDaemon(true);
        rose.start();
        jack.start();
        System.out.println("main方法结束了");
//       while(true){
           System.out.println("ta");
       }
//    main方法里也是一个前台线程，不管怎样先执行main方法里的
//    System.out.println("main方法结束了");
    }

