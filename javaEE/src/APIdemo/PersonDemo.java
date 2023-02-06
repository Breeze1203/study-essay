package APIdemo;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class PersonDemo {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
        System.out.println("开始写出");
        FileInputStream o=new FileInputStream("person.txt");
        ObjectInputStream q=new ObjectInputStream(o);
        person e=(person)q.readObject();
        System.out.println(e);
        q.close();
    }
}
