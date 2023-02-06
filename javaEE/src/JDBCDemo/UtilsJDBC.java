package JDBCDemo;



import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class UtilsJDBC {
    /*


    // 申明所需要配置的变量
    public static String driverclass;
    public static String url;
    public static String user;
    public static String passworld;
    public static Connection con;

    // 读取配置文件
    static {
        try {
            InputStream ins = UtilsJDBC.class.getClassLoader().getResourceAsStream("Utils.properties");
            Properties prop = new Properties();
            prop.load(ins);
            driverclass = prop.getProperty("driverclass");
            url = prop.getProperty("url");
            user = prop.getProperty("user");
            passworld = prop.getProperty("passworld");
            Class.forName(driverclass);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        try {
            con = DriverManager.getConnection(url,user,passworld);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return con;
    }
    public static void close(Connection con, Statement stat, ResultSet set){
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stat!=null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(set!=null){
            try {
                set.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void close(Connection con, Statement stat){
        if(con!=null){
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if(stat!=null){
            try {
                stat.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

     */
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
}