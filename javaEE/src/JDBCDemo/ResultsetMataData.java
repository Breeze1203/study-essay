package JDBCDemo;

import java.sql.*;

public class ResultsetMataData {
    public static void main(String[] args) {
        get();

    }
    public static void get(){
        Connection con=null;
        try{
            con=UtilsJDBCDemo.getConnection();
            String sql="select * from emp_1 where id in (1,2,3)";
            /*
            PreparedStatement preparedStatement= con.prepareStatement(sql);
            preparedStatement.setInt(1,1);
            preparedStatement.setInt(2,2);
            preparedStatement.setInt(3,3);
            ResultSet resultSet=preparedStatement.executeQuery();
            ResultSetMetaData set=resultSet.getMetaData();
            for(int i=1;i<=set.getColumnCount();i++)

                System.out.println(set.getColumnName(i));

             */

            Statement statement=con.createStatement();
            ResultSet resultSet=statement.executeQuery(sql);
            ResultSetMetaData resultsetMataData=resultSet.getMetaData();
            int n=resultsetMataData.getColumnCount();
            System.out.println(n);
            System.out.println(resultsetMataData.getColumnName(1));
            System.out.println(resultsetMataData.getColumnName(2));
            System.out.println(resultsetMataData.getColumnName(3));
            for(int i=1;i<=n;i++)
                System.out.println(resultsetMataData.getColumnName(i));


        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
