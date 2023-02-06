package ThreadDemo;

import java.text.SimpleDateFormat;
import java.util.Date;

public class realizableDemo10 {
    public static void main(String[] args) {
        Thread t1=new Thread(){
          public void run(){
//              Date date=new Date();
//              SimpleDateFormat sdf=new SimpleDateFormat("HH:mm:ss");
              while(true){
                  Date date=new Date();
                  SimpleDateFormat sdf =new SimpleDateFormat("HH:mm:ss");
                  System.out.println(sdf.format(date));
                  try {
                      sleep(1000);
                  } catch (InterruptedException e) {
                      e.printStackTrace();
                  }
              }
          }
        };
        t1.start();

    }
}
