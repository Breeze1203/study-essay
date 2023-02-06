package APIdemo;

import java.io.Serializable;
import java.util.List;

public class person implements Serializable {

    private String name;
    private int salary;
    private String gendar;
    private transient List<String> otherInfo;

    public person(){
    }

    public String toString(){
        return name+","+gendar+","+ salary +","+otherInfo;
    }


    public void setName(String name) {
        this.name=name;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public void setGendar(String gendar) {
        this.gendar = gendar;
    }

    public void setOtherInfo(List<String> otherInfo) {
        this.otherInfo = otherInfo;
    }
}
