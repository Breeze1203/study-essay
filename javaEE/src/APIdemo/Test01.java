package APIdemo;

public class Test01 {
    public static void main(String[] args) {
        AgeDemo b=new AgeDemo();
        System.out.println("正常");
        try {
            b.setAge(200);
        }catch(Exception e){
            System.out.println("年龄不合法");
        } finally {
            System.out.println("年龄不合法");
        }
        System.out.println(b.getAge());
        System.out.println("正常执行");
    }
}
