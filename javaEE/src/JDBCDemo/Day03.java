package JDBCDemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Day03 {
    public static void main(String[] args) {
        Thread t1=new ThreadDemo(5000);
        Thread t2=new ThreadDemo(5000);
        Thread t3=new ThreadDemo(5000);
        t1.start();
        t2.start();
        t3.start();
    }
}
class ThreadDemo extends Thread{
    int wait;

    public ThreadDemo(int wait){
        this.wait=wait;
    }
    @Override
    public void run() {
        try{
            Connection con = UtilsJDBCDemo.getConnection();
            System.out.println("建立连接");
            Thread.sleep(wait);
            Statement stat=con.createStatement();
            String sql="select * from emp_1";
            ResultSet set=stat.executeQuery(sql);
            while(set.next()){
                System.out.println(set.getInt("id")+"\t"+set.getString("name")+"\t"+set.getInt("age"));
            }
            System.out.println("建立结束");
        } catch (SQLException | InterruptedException e) {
            e.printStackTrace();
        }
    }
}

