package APIdemo;

import java.io.*;

public class File01Demo {
    public static void main(String[] args) throws IOException {
        FileInputStream fis=new FileInputStream("视频1.qlv");
        BufferedInputStream bis=new BufferedInputStream(fis);
        FileOutputStream fos=new FileOutputStream("视频2.qlv");
        BufferedOutputStream bos=new BufferedOutputStream(fos);
        long start=System.currentTimeMillis();
        int d=-1;
        while((d=bis.read())!=-1){
            bos.write(d);
        }
        bis.close();
        bos.close();
        long end=System.currentTimeMillis();
        System.out.println(end-start);
    }
}
