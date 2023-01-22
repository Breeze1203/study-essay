```java
package JDBCDemo;
/**
工具类，读取配置文件中的driverclass,url,user,passworld

*/

import com.mysql.cj.protocol.Resultset;
import org.w3c.dom.ls.LSOutput;

import java.io.IOException;
import java.io.InputStream;
import java.sql.*;
import java.util.Collection;
import java.util.Properties;

public class UtilsJDBC {
UtilsJDBC(){}
    private static String driverclass;
    private static String url;
    private static String user;
    private static String passworld;


    static {
        try {
            InputStream in = UtilsJDBC.class.getClassLoader().getResourceAsStream("UTils.properties");
            Properties prop = new Properties();
            prop.load(in);
            driverclass = prop.getProperty("driverclass");
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            passworld = prop.getProperty("passworld");
            Class.forName(driverclass);
            System.out.println("开始运行");
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
    public void go(){
        System.out.println("已经过");
    }

    public static Connection getconnection() {
        Connection con = null;
        try {
            con = DriverManager.getConnection(url, user, passworld);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void close(Connection con, Statement stat, ResultSet set) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (set != null) {
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection con, Statement stat) {
        if (con != null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stat != null) {
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

```

```java
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
```

