```javascript
// 获取ajax对象
function getXhr(){
            var x=null;
            if(window.XMLHttpRequest){
                x=new XMLHttpRequest();
            }else{
                x=new ActiveXObject("Microsoft.XMLHttp");
            }
            return x;
        }

// 依据id查找节点
function $(id){
return document.getElementById(id);
}

// 根据id查找节点，然后返回上面的value
function $F(id){
return $(id).value;
}
```

```java
<%--
  Created by IntelliJ IDEA.
  User: 温柔赋予她
  Date: 2022/11/2
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="ajax.js"></script>
    <!-- post请求-->
    <script type="text/javascript">
        function check_code(){
            var xhr=getXhr();
            xhr.open('post','check_code.do',true);
            xhr.setRequestHeader('content-type','application/x-www-form-urlencoded')
            xhr.onreadystatechange=function (){
                if(xhr.readyState==4&&xhr.status==200){
                    var txt=xhr.responseText;
                    alert(txt)
                }
            };
            xhr.send('admin='+$F('code'));
        }
    </script>

    <!--
    get请求
    <script type="text/javascript">
        function check_code() {
            // 1.先获的ajax对象
            var xhr = getXhr();
            // 2.发送请求
            xhr.open('get', 'check_code.do?admin=' + $F('code'), true)
            xhr.onreadystatechange = function () {
                // 处理服务器返回的数据
                if (xhr.readyState == 4 && xhr.status == 200) {
                    // 获得服务器返回的数据
                    var txt = xhr.responseText;
                }
            };
            xhr.send(null);
        }
    </script>
    -->
</head>
<body style="font-size: 30px">
<form action="" method="post">
    账号：<input type="text" id="code" name="admin" onblur="check_code()"></br>
    密码：<input type="password" name="password"></br>
    <input type="submit" value="确定">
</form>
</body>
</html>
```

#### 服务端代码

```java
package ajax;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;

public class ajaxText extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("utf-8");
        resp.setContentType("text/html");
        resp.setCharacterEncoding("utf-8");
        System.out.println("ajax....");
        String url=req.getRequestURI();
        System.out.println("url :"+url);
        PrintWriter out=resp.getWriter();
        String action=url.substring(url.lastIndexOf("/"),url.lastIndexOf("."));
        System.out.println("action ："+action);
        // 根据分析结果不同，进行不同处理
        if("/check_code".equals(action)){
            String admincode=req.getParameter("admin");
            if("king".equals(admincode)){
                out.print("账号已存在");
            }else {
                out.print("可以使用");
            }
        }else if("/luck".equals(action)){
            Random random=new Random();
            int num=random.nextInt(100);
            System.out.println(num);
            out.print(num);
        }
    }
}
```

```java
<%--
  Created by IntelliJ IDEA.
  User: 温柔赋予她
  Date: 2022/11/3
  Time: 11:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" src="ajax.js"></script>
    <script type="text/javascript">
        function show_luck() {
            var xhr = getXhr();
            xhr.open('get', 'luck.do', true);
            xhr.onreadystatechange =function (){
                if(xhr.readyState==4&&xhr.status==200){
                    var txt=xhr.responseText;
                    $('d1').innerHTML=txt;
                }
            };
            xhr.send(null)
        }
    </script>
</head>
<body>
<h2>
    <a href="javascript:show_luck()">你的幸运数字是:</a>
    <div id="d1"></div>
</h2>
</body>
</html>
```

#### 本案列展示的是ajax对象通过两种请求获取服务器端传过来的数据

load（）方法，向服务器发送异步请求，然后将服务器返回的数据直接添加到符合要求的节点上

```jsp
<%--
  Created by IntelliJ IDEA.
  User: 温柔赋予她
  Date: 2022/11/6
  Time: 18:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script src="js/jquery.js"></script>
    <script>
        $(function (){
            $('#d1').onclick(show_Number())
        });
        function show_Number(){
            $('#d1').load('luck.do','size=3');
        }
    </script>
</head>
<body>
<a href="javascript:show_luck()">你的幸运数字是:</a>
<div id="d1"></div>
</body>
</html>
```

