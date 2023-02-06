package ThreadDemo;


public class joinDemo {
    public static boolean isFinish=false;
    public static void main(String[] args) {
        Thread downland = new Thread() {
            public void run() {
                System.out.println("开始下载");
                for (int i = 1; i <= 100; i++) {
                    System.out.println("下载中：" + i + "%");
                    try {
                        sleep(50);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                System.out.println("图片下载完毕");
                isFinish = true;
            }
        };
        Thread show=new Thread(){
          public void run(){
              System.out.println("开始显示图片");
              try {
                  downland.join();
              } catch (InterruptedException e) {
                  e.printStackTrace();
              }
              if(isFinish ==false){
                  throw new RuntimeException("图片还没有下载完毕");
              }else{
                  System.out.println("图片显示完毕");
              }
          }
        };
        downland.start();
        show.start();
    }
}
