package APIdemo;

import java.io.*;

public class File05Dem0 {
    public static void main(String[] args) throws IOException {
        FileOutputStream fly=new FileOutputStream("fly.txt");
        OutputStreamWriter flys=new OutputStreamWriter(fly);
        PrintWriter n=new PrintWriter(flys);
        n.println("锄禾日当午");
        n.println("汗滴禾下土");
        n.close();

    }
}
