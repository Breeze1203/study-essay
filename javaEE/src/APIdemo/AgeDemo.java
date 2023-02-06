package APIdemo;

public class AgeDemo {
    public int getAge() {
        return age;
    }

    public void setAge(int age) throws Exception {
        if (0 < age||age > 90) {
            throw new Exception("年龄不合法");
        }
        this.age = age;
    }

    private int age;
}
