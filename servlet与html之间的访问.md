```html
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Document</title>
</head>
<body>
<form action="http://localhost:8080/firstwebProject/reg">
<p>
    用户名：<input type="text" name="userName">
    密码：<input type="password" name="pwd">
</p>
<p>
    性别：<input type="radio" name="sex" value="M">男
    <input type="radio" name="sex" value="F">女
</p>
<p>
    爱好：<input type="checkbox" name="interest" value="music">听歌
    <input type="checkbox" name="interest" value="run">跑步
    <input type="checkbox" name="interest" value="sport">打球
</p>
<p>
    <input type="submit" value="注册">
</p>
</form>
</body>
</html>
```

**<u>通过表单提交，"http://localhost:8080/firstwebProject/reg"></u>**

**<@WebServlet("/reg")  编写类的注解**



```java
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/reg")
public class Demo02 extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 处理请求的一般步骤
        // 1.接受参数
        // 3.发送响应
        String user = req.getParameter("userName");
        String pwd = req.getParameter("pwd");
        String sex = req.getParameter("sex");
        String[] interest = req.getParameterValues("interest");
        // 处理乱码
        byte[] bs=user.getBytes("ISO8859-1");

        user=new String(bs,"UTF-8");
        // 2.处理业务
        System.out.println(user);
        System.out.println(pwd);
        System.out.println(sex);
        if (interest != null) {
            for (String i : interest) {
                System.out.println(i);
            }
        }
        // 3.发送响应
        resp.setContentType("text/html；utf-8");
        PrintWriter printWriter=resp.getWriter();
        printWriter.println("<p>注册成功</p>");
        printWriter.close();
    }
}
```

1. **web客户将数据打包，向Servlet容器发出HTTP请求;**
2. **Servlet容器解析web的HTTP请求.**
3. **Servlet容器创建一个HttpRequest对象,在这个对象中封装了http请求信息;**
4. **Servlet容器创建一个HttpResponse对象;**
5. ***Servlet容器（如果访问的该servlet不是在服务器启动时创建的，则先创建servlet实例并调用init()方法初始化对象）调用HttpServlet的service()方法,把HttpRequest和HttpResponse对象为service方法的参数传给HttpServlet对象***;
6. **HttpServlet调用HttpRequest的有关方法,获取HTTP请求信息;**
7. **HttpServlet调用HttpResponse的有关方法,生成响应数据;**
8. **Servlet容器把HttpServlet的响应结果传给web客户.** 





![65217115796](C:\Users\86199\AppData\Local\Temp\1652171157967.png)