##### 对象实体类

```java
package Json;

public class Stock {
    private String name;
    private int code;
    private int price;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
```

```java
package Json;

public class student {
    private String name;
    private int age;
    private int id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
```

##### 测试类

```java
package Json;

import com.mysql.cj.xdevapi.JsonArray;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class json_lib {
    public static void main(String[] args) {
        student s=new student();
        s.setId(1);
        s.setAge(19);
        s.setName("张三");
        // 使用json-lib
        JSONObject jsonObject=JSONObject.fromObject(s);
        String json=jsonObject.toString();
        System.out.println(json);

        // 对象集合转换为json
        List<Stock> stocks=new ArrayList<>();
        for(int i=0;i<3;i++){
            Stock stock=new Stock();
            stock.setCode(i);
            stock.setName("中国嘉陵"+i);
            stock.setPrice(43+i);
            stocks.add(stock);
        }
        JSONArray jsonArray= JSONArray.fromObject(stocks);
        String jsone=jsonArray.toString();
        System.out.println(jsone);

        // 数组转换为Json
        Object[] objects=new Object[10];
        for (int i=0;i<objects.length;i++){
            Stock stock=new Stock();
            stock.setCode(i+1);
            stock.setName("嘉陵"+i);
            stock.setPrice(53+i);
            objects[i]=stock;
        }
        JSONArray jsonArray1=JSONArray.fromObject(objects);
        String a=jsonArray1.toString();
        System.out.println(a);
    }
}
```

**JSONObject   jsonObject=JSONObject.fromObject(s);`**
 `String     json=jsonObject.toString();`

转对象

##### JSONArray jsonArray1=JSONArray.fromObject(objects);

​        String a=jsonArray1.toString();

转集合或数组