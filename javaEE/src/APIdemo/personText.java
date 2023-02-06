package APIdemo;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class personText {
    public static void main(String[] args) throws IOException {
        person n=new person();
        n.setName("王愿科");
        n.setSalary(20);
        n.setGendar("男");
        List<String> otherInfe=new ArrayList<String>();
        otherInfe.add("是一名大二在校学生，在校成绩优异，活泼开朗");
        otherInfe.add("为中日文化做出伟大贡献");
        n.setOtherInfo(otherInfe);
        FileOutputStream fos = new FileOutputStream("person.txt");
        ObjectOutputStream oos=new ObjectOutputStream(fos);
        oos.writeObject(n);
        oos.close();
    }
}
