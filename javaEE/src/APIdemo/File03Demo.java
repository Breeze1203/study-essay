package APIdemo;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class File03Demo {
    public static void main(String[] args) throws IOException {
        FileInputStream gbs=new FileInputStream("fbi.txt");
        InputStreamReader gos=new InputStreamReader(gbs);
        int n=-1;
        while(((n=gos.read())!=-1)){
            System.out.print((char)n);
        }
    }
}
