```jsp
<%@ page import="entiy.Emp" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: 温柔赋予她
  Date: 2022/5/30
  Time: 15:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>查询员工</title>
</head>
<body>
<table>
    <tr>
        <td>number</td>
        <td>age</td>
        <td>salary</td>
        <td>name</td>
    </tr>
    <%
        List<Emp> list = (List<Emp>) request.getAttribute("name");
        if (list != null) {
            for (Emp e : list) {
    %>
    <tr>
        <td><%=e.getNumber()%></td>
        <td><%=e.getAge()%></td>
        <td><%=e.getSalary()%></td>
        <td><%=e.getName()%></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>

```

```java
package Text;

import entiy.EmpDao;
import entiy.EmpDaoImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/Find_Emp")
public class Find_Emp extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        EmpDao dao=new EmpDaoImpl();
        List list=dao.findAll();
        req.setAttribute("name",list);
        req.getRequestDispatcher("Find_Emp.jsp").forward(req,resp);
    }
}

```

<u>利用转发</u>   <font color=red>req.getRequestDispatcher("Find_Emp.jsp").forward(req,resp);</font>package demoText;

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
        Cookie[] cookies=req.getCookies();
        if(cookies!=null){
            PrintWriter out= resp.getWriter();
            for(Cookie c:cookies){
                out.print(URLDecoder.decode(c.getName(),"utf-8")+"  "+ URLDecoder.decode(c.getValue(),"utf-8"));
            }
        }
    }
}package demoText;

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
        Cookie[] cookies=req.getCookies();
        if(cookies!=null){
            PrintWriter out= resp.getWriter();
            for(Cookie c:cookies){
                out.print(URLDecoder.decode(c.getName(),"utf-8")+"  "+ URLDecoder.decode(c.getValue(),"utf-8"));
            }
        }
    }
}

Find_Emp.jsp是jsp文件，forward就是转发

jsp文件里获取就是   List<Emp> list = (List<Emp>) request.getAttribute("name");

```jsp
<%--
  Created by IntelliJ IDEA.
  User: 温柔赋予她
  Date: 2022/5/29
  Time: 8:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="entiy.*" %>
<%@ page import="java.util.List"%>bh
<html>
<head>
    <title>第一个jsp页面</title>
</head>
<body>
<table>
    <tr>
        <td>number</td>
        <td>age</td>
        <td>salary</td>
        <td>name</td>
    </tr>
    <%
        EmpDao dao = new EmpDaoImpl();
        List<Emp> list = dao.findAll();
        if (list != null) {
            for (Emp e : list) {
    %>
    <tr>
        <td><%=e.getNumber()%></td>
        <td><%=e.getAge()%></td>
        <td><%=e.getSalary()%></td>
        <td><%=e.getName()%></td>
    </tr>
    <%
            }
        }
    %>
</table>
</body>
</html>

```

这种为不利用转发,处理小型且逻辑简单的业务