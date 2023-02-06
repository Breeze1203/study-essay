package JDBCDemo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PreparedDemo {
    public static void main(String[] args) {
        Connection con = null;
        con = UtilsJDBCDemo.getConnection();
        String sql="insert into emp_1(id,name,age)values(?,?,?)";
        String sql1="update emp_1 set name=? where id=?";
        String sql2="delete from emp_1 where id in(?,?)";
        try {
             // PreparedStatement prep = con.prepareStatement(sql);
            PreparedStatement prep1=con.prepareStatement(sql2);
            /*
            prep.setNull(1,4);
            prep.setString(2,"贵八");
            prep.setInt(3,18);
            int t=prep.executeUpdate();
            System.out.println(t);
            System.out.println("加入完毕");
            prep.setNull(1,1);
            prep.setString(2,"喜欢");
            prep.setInt(3,24);
            int n=prep.executeUpdate();
            System.out.println(n);

             */
            /*
            System.out.println("加入完毕");
            prep1.setString(1,"赵文");
            prep1.setInt(2,5);
            prep1.executeUpdate();
            System.out.println("更改完毕");

             */
            /*
            prep1.setString(1,"王八");
            prep1.setInt(2,6);
            prep1.executeUpdate();

             */
            prep1.setInt(1,6);
            prep1.setInt(2,7);
            prep1.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
