package JDBCDemo;



import java.sql.*;

public class Day01 {
    public static void main(String[] args){
        /*
        Connection con=null;
        try{
        con=UtilsJDBC.getConnection();
        Statement stat=con.createStatement();
        String sql="select * from emp_1";
        ResultSet rs=stat.executeQuery(sql);
        while(rs.next()) {
            System.out.println(rs.getInt("id") + "\t" + rs.getString("name"));
        }
        }catch(Exception e){
            System.out.println("报错了");
        }
         */
        Connection con=UtilsJDBC.getconnection();
        try {
            Statement stat=con.createStatement();
            String sql="select * from emp_1";
            ResultSet resultSet = stat.executeQuery(sql);
            while(resultSet.next())
                System.out.println(resultSet.getInt("ID")+"\t"+resultSet.getString("name")+"\t"+resultSet.getInt("age"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        System.out.println("写出完毕");


        UtilsJDBC jdbc=new UtilsJDBC();
        jdbc.go();
    }
}

