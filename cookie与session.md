cookie和session存值，并且另一servlet访问

```java
package sessionDemo;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;

@WebServlet("/session")
public class session extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name=req.getParameter("name");  // 获取浏览器输入：如name=张三 获取的就是张三
        HttpSession session=req.getSession();  
        System.out.println(session.getId());
        session.setAttribute("name", name);
        System.out.println(session);
        System.out.println(name);
    }
}
```

```java
package sessionDemo;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;


@WebServlet("/sessionDemo")
public class sessionDemo extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session=req.getSession();  // 获取请求端的session
        String name= (String) session.getAttribute("name");
        PrintWriter out=resp.getWriter();
        out.print(name);

    }
}
```

```java
package demoText;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;

@WebServlet("/cookie")
public class cookieDemo extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String city=req.getParameter("city");
        Cookie a2=new Cookie("city", URLDecoder.decode(city,"utf-8")); // 改变编码格式
        a2.setMaxAge(0);
        resp.addCookie(a2); //  加入cookie
        System.out.println(a2.getValue()+"   "+a2.getName());
    }
}
```

```java
package demoText;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;

@WebServlet("/index")
public class indexCookie extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Cookie[] cookies=req.getCookies(); // 获取请求端的cookie
        if(cookies!=null){
            PrintWriter out= resp.getWriter();
            for(Cookie c:cookies){
                out.print(URLDecoder.decode(c.getName(),"utf-8")+"  "+ URLDecoder.decode(c.getValue(),"utf-8"));
            }
        }
    }
}
```