package APIdemo;

import java.io.*;

public class File02Demo {
    public static void main(String[] args) throws IOException {
        FileOutputStream FBI=new FileOutputStream("fbi.txt");
        OutputStreamWriter fos=new OutputStreamWriter(FBI);
        fos.write("我爱中国,此生不悔华夏");
        fos.close();
        System.out.println("书写完毕");
    }
}
