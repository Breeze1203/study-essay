package JDBCDemo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Day02 {
    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/text","root","3548297839");
            Statement stat=con.createStatement();
            String sql="select * from emp_1";
            ResultSet rs=stat.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getInt("id")+"\t"+rs.getString("name")+"\t"+rs.getInt("age"));
            }
        }catch(Exception e){
            System.out.println("报错了");
        }
    }
}
