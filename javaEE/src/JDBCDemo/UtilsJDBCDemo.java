package JDBCDemo;

import org.apache.commons.dbcp2.BasicDataSource;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class UtilsJDBCDemo {
    public static String driverclass;
    public static String url;
    public static String user;
    public static String passworld;
    public static int Initiasize;
    public static int MaxActive;
    public static BasicDataSource ds;

    static{
        ds=new BasicDataSource();
        InputStream in=UtilsJDBCDemo.class.getClassLoader().getResourceAsStream("Utils.properties");
        Properties prop=new Properties();
        try {
            prop.load(in);
            driverclass=prop.getProperty("driverclass");
            url=prop.getProperty("url");
            user=prop.getProperty("user");
            passworld=prop.getProperty("passworld");
            Initiasize=Integer.parseInt(prop.getProperty("Size"));
            MaxActive=Integer.parseInt(prop.getProperty("MaxActive"));
            in.close();
            ds.setDriverClassName(driverclass);
            ds.setUrl(url);
            ds.setUsername(user);
            ds.setPassword(passworld);
            ds.setInitialSize(Initiasize);
            ds.setMaxTotal(MaxActive);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection(){
        Connection con=null;
        try {
            con=ds.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public static void close(Connection con){
        if(con!=null) {
            try {
                con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
