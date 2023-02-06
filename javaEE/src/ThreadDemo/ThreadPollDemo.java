package ThreadDemo;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPollDemo {
    /***
     *
     * @param args
     */
    public static void main(String[] args) {
        /*
        List<String> list=new ArrayList<String>();
        list.add("我");
        list.add("喜欢");
        list.add("你");
        System.out.println(list);
        list= Collections.synchronizedList(list);
        System.out.println(list);
        Set<String> set=new HashSet();
        set.add("我");
        set.add("喜欢");
        set.add("你");
        System.out.println(set);
        set=Collections.synchronizedSet(set);
        System.out.println(set);
        Map<String,Integer> map=new HashMap<String,Integer>();
        map.put("语文",90);
        map.put("数学",99);
        map.put("英语",60);
        System.out.println(map);
        map=Collections.synchronizedMap(map);
        System.out.println(map);

         */

        long start1 = System.currentTimeMillis();
        /*
        System.out.println("我不喜欢你");
         */
        ExecutorService threadPool = Executors.newCachedThreadPool();
            Thread thread = new Thread() {
                public void run() {
                    System.out.println(Thread.currentThread().getName() + " ," + "我喜欢你");
                    try {
                        sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            };
            threadPool.execute(thread);
            long end1 = System.currentTimeMillis();
            System.out.println(end1 - start1);
    }
}
