package algorithmDemo;

public class Main {
    public static void main(String[] args) {
        int[] arr = {1, 4, 2, 9, 7, 11, 8};
        selectionSort.sort(arr);
        for (int i = 0; i < arr.length; i++) {
            System.out.println(arr[i]);
        }
        linkList<Integer> linkList = new linkList<Integer>();
        for (int i = 0; i < 5; i++) {
            linkList.addFirst(i);
            System.out.println(linkList);
        }
        linkList.set(2,666);
        System.out.println(linkList);
        linkList.delete(3);
        System.out.println(linkList);
        linkList.set(1,666);
        linkList.set(3,666);
        System.out.println(linkList);
        linkList.deleteRepeat(666);
        System.out.println(linkList);

        linkList<String> list=new linkList<>();
        // 向链表中添加元素
        list.addFirst("w");
        list.add(1,"q");
        list.add(2,"e");
        list.add(3,"r");
        list.add(4,"t");
        System.out.println(list);
        // 改变三号元素值
        list.set(3,"p");
        System.out.println(list);
        // 给链表末尾添加元素
        list.addLast("o");
        System.out.println(list);
        // 删除二号元素
        list.delete(2);
        System.out.println(list);

    }

}
