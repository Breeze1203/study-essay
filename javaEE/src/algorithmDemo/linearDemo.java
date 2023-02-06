package algorithmDemo;

public class linearDemo {
    public static void main(String[] args) {
        Integer[] data={12,33,55,88,79,56,78,99,34};
        int t=LinerSearch.search(data,78);
        System.out.println(t);
        String[] string={"我","喜","欢","你"};
        int h=LinerSearch.search(string,"你");
        System.out.println(h);
    }
    class LinerSearch{
        public static<E> int search(E[] data,E target){
            for(int i=0;i<data.length;i++) {
                if (data[i].equals(target)){
                    return i;
                }
            }
            return -1;
        }
    }
}
