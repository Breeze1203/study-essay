package APIdemo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class FileDemo {
    public static void main(String[] args) throws IOException {
        FileOutputStream fos2 = new FileOutputStream("fos2.txt");
        String str="文件输出流,文件输入流";
        byte[] a=str.getBytes("UTF-8");
        fos2.write(a);
        fos2.close();
    }
}
