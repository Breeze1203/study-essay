package JDBCDemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Scanner;

public class SQLInjectionDemo {
    public static void main(String[] args) {
        System.out.println("请输入id");
        Scanner in=new Scanner(System.in);
        int id= Integer.parseInt(in.nextLine());
        System.out.println("请输入姓名");
        String name=in.nextLine();
        LoginIn(id,name);

    }
    public static void LoginIn(Integer id,String name){
        Connection con=null;
        try{
            con=UtilsJDBCDemo.getConnection();
            Statement stat=con.createStatement();
            String sql="select * from emp_1 where id='"+id+"'"+"and name='"+name+"'";
            System.out.println(sql);
            ResultSet rs=stat.executeQuery(sql);
            if(rs.next()){
                System.out.println("欢迎你"+name);
            }else{
                System.out.println("用户名或密码错误");
            }
        }catch(Exception e){
            e.printStackTrace();
        }finally{
            UtilsJDBCDemo.close(con);
        }
    }
}
