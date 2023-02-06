package APIdemo;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner scanner=new Scanner(System.in);
        System.out.println("请输入文件名：");
        String filename=scanner.nextLine();
        FileOutputStream a=new FileOutputStream(filename);
        OutputStreamWriter b=new OutputStreamWriter(a);
        PrintWriter c=new PrintWriter(b,true);
        System.out.println("请你输入内容:");
        String line=null;
        while(true){
            line=scanner.nextLine();
            if("exit".equals(line)){
                System.out.println("再见");
                break;
            }
            c.println(line);
        }
        c.close();
    }
}
