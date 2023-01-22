```java
package com.spring.xmlContext;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Applicationcontext {
    /*利用配置文件初始化当前容器
     * 利用xml文件，初始化全部bean对象
     * */
    private Map<String,Object> beanMap=new HashMap<String,Object>();

    public Applicationcontext(String xml){
        // 利用dom4J读取xml文件 ,解析xml文件，得到bean的类名和id
        //  根据类名动态加载类并创建对象，将对象和对应的id添加到map中
        // 从resources(classpath)里面读取流
        InputStream in=getClass().getClassLoader().getResourceAsStream(xml);
        //相当于高级流
        SAXReader read=new SAXReader();
        try {
            Document doc=read.read(in);
            in.close();
            // 解析xml
            Element root=doc.getRootElement();
            // 读取根元素中的全部bean子元素
            List<Element> list=root.elements("bean");
            for(Element e:list){
                String id=e.attributeValue("id");
                String classname=e.attributeValue("class");
                // 动态加载类，动态创建对象
                Class cla=Class.forName(classname);
                Object bean=cla.newInstance();
                beanMap.put(id,bean);
            }
        } catch (DocumentException | IOException | ClassNotFoundException | InstantiationException |
                 IllegalAccessException e) {
            throw new RuntimeException(e);
        }

    }

    public Object getBean(String id){
        // 根据id在map中查找对象，并返回对象

        return beanMap.get(id);
    }

    // 泛型方法，有点是减少依次类型转换
    public<T> T getBean(String id,Class<T> cla){
        return (T) beanMap.get(id);
    }
}
```

```java
package com.spring.xmlContext;



import java.util.Date;

public class Text {
    public static void main(String[] args) {
        Applicationcontext d=new Applicationcontext("context.xml");
        Date date=d.getBean("date",Date.class);
        System.out.println(date);
    }

}
```