package APIdemo;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class File04Demo {
    public static void main(String[] args) throws FileNotFoundException {
        PrintWriter pw=new PrintWriter("pw.txt");
        pw.println("锄禾日当午");
        pw.println("汗滴禾下土");
        pw.println("谁之盘中餐");
        pw.println("粒粒皆辛苦");
        pw.close();
    }
}
