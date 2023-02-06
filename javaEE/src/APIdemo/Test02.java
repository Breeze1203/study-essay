package APIdemo;

public class Test02 {
    public static void main(String[] args) {
        System.out.println("程序开始了");
        try{String b="abc";
        System.out.println(b.charAt(3));} catch (Exception e) {
            e.printStackTrace();
            System.out.println("位置越界");
        }
        System.out.println("程序结束了");
    }
}
