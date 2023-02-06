package JDBCDemo;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TextDemo {
    public static void main(String[] args) {
        try{
        System.out.println("建立连接");
        Connection conn = UtilsJDBCDemo.getConnection();
        Statement stat = conn.createStatement();
            String sql="select * from emp_1";
            ResultSet set=stat.executeQuery(sql);
            while(set.next()){
                System.out.println(set.getInt("id")+"\t"+set.getString("name")+"\t"+set.getInt("age"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
