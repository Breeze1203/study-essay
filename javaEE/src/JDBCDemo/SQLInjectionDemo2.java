package JDBCDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Scanner;

public class SQLInjectionDemo2 {
    public static void main(String[] args) {
        Scanner in=new Scanner(System.in);
        System.out.println("请输入用户名");
        int id=Integer.parseInt(in.nextLine());
        System.out.println("请输入密码");
        String name=in.nextLine();
        getInput(id,name);
    }
    public static void getInput(Integer id,String name){
        Connection con=null;
        try{
            con=UtilsJDBCDemo.getConnection();
            String sql="select * from emp_1 where id=? and name=?";
            PreparedStatement prep=con.prepareStatement(sql);
            prep.setInt(1,id);
            prep.setString(2,name);
            System.out.println(sql);
            ResultSet set=prep.executeQuery();
            if(set.next()){
                System.out.println("欢迎你"+name);
            }else{
                System.out.println("用户名或密码错误");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
