package algorithmDemo;

public class linerDemo01 {
    public static void main(String[] args) {
        Students[] student={new Students("张三"),
                            new Students("李四"),
                            new Students("王五")};
        Students t3=new Students("李四");
        int t4=LinerSearch.search(student,t3);
        System.out.println(t4);
    }
    class LinerSearch{
        public static<E>int search(E[] t1,E t2){
            for(int i=0;i<t1.length;i++)
                if(t1[i].equals(t2))
                    return i;
            return -1;
        }
    }
}
class Students{
    public Students(String name) {
        this.name = name;
    }
    private String name;
    @Override
//   重写equals方法，进行覆盖
    public boolean equals(Object student){
        if(this==student)  // 判断当前Students类对象是不是Object里的student对象
            return true;
        if(student==null)  //判断是否为空
            return false;
        if(this.getClass()!=student.getClass()) //判断当前类的对象信息与Object syudent对象信息
            return false;
        Students another=(Students)student;// 进行强转
        return this.name.equals(another.name);
    }
}
