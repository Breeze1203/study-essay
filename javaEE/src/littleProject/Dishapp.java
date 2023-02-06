package littleProject;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class Dishapp {

    // 创建一个集合，将餐单放入集合中;
    static List<Dish> dishlist = new ArrayList<Dish>();
    static List<Dish> persodishlist = new ArrayList<Dish>();


    public static void main(String[] args) {

        // 初始化菜品
        menu();

        Scanner scanner = new Scanner(System.in);

        while (true) {
            // 展示主菜单
            showmenu();
            System.out.println("请输入编号，根据编号选择服务");
            int num = scanner.nextInt();
            // 根据编号选择服务
            switch (num) {
                case 1:
                    showdishmenu();
                    while (true) {
                        System.out.println("请输入id点菜");
                        int id = scanner.nextInt();
                        if (id == 0) {
                            break;
                        }
                        Dish dish = dishlist.get(id);
                        System.out.println("亲，你点了" + dishlist.get(id).name + "菜");
                        persodishlist.add(dish);
                    }
                case 2:
                    // 展示已点菜品
                    showPersonmeau();
                    break;
                case 3:
                    // 买单结账
                    buy();
                    return;

            }
        }

    }

    private static void buy() {
        System.out.println("亲，请稍等，正在结算");
        double total = 0;
        for (Dish dish : persodishlist) {
            total = total + dish.price;
        }
        System.out.println("亲,你本次消费" + total + "元");
    }

    private static void showPersonmeau() {
        System.out.println("----你已点的菜品----");
        if (persodishlist.size() == 0) {
            System.out.println("暂无选菜");
        } else {
            for (Dish dish : persodishlist) {
                System.out.println(dish.id + "\t\t\t" + dish.name + "\t\t\t" + dish.price);
            }
        }
    }

    private static void showdishmenu() {
        System.out.println("----请你点菜----");
        for (int i = 0; i < dishlist.size(); i++) {
            // 获取集合里的每一个
            System.out.println(dishlist.get(i).id + "\t\t\t" + dishlist.get(i).name + "\t\t\t" + dishlist.get(i).name);
        }
        System.out.println("----输入序号点菜，按0返回上一级----");
    }

    // 初始化菜单
    public static void menu() {
        dishlist.add(new Dish(1, "水煮鱼", 34));
        dishlist.add(new Dish(2, "排骨汤", 55));
        dishlist.add(new Dish(3, "牛肉汤", 56));
        dishlist.add(new Dish(4, "尖椒肉", 55));
        dishlist.add(new Dish(5, "羊肉汤", 66));
        dishlist.add(new Dish(6, "炒鸡蛋", 20));
    }

    // 显示菜单
    public static void showmenu() {
        System.out.println("------主菜单------");
        System.out.println("菜单\t\t\t\t1");
        System.out.println("已点菜品\t\t\t2");
        System.out.println("买单\t\t\t\t3");
    }
}
