Ajax用于浏览器与服务器通信而无需刷新整个页面，服务器将不再返回整个页面，而是返回少量数据，通过JavaScript DOM更新一部分节点

$Ajax()

该方法用于执行Ajax请求，也许我们可以把它称为"jQuery中Ajax系列方法之母"。

可选字段：
1）**url**：链接地址，字符串表示

2）data：需发送到服务器的数据，GET与POST都可以，格式为{A: '...', B: '...'}
3）type："POST" 或 "GET"，请求类型
4）timeout：请求超时时间，单位为毫秒，数值表示
5）cache：是否缓存请求结果，bool表示
6）contentType：内容类型，默认为"application/x-www-form-urlencoded"
7）dataType：服务器响应的数据类型，字符串表示；当填写为json时，回调函数中无需再对数据反序列化为json
8）success：请求成功后，服务器回调的函数
9）error：请求失败后，服务器回调的函数
10）complete：请求完成后调用的函数，无论请求是成功还是失败，都会调用该函数；如果设置了success与error函数，则该函数在它们之后被调用
11）async：是否异步处理，bool表示，默认为true；设置该值为false后，JS不会向下执行，而是原地等待服务器返回数据，并完成相应的回调函数后，再向下执行

```js
// 发送请求
$.ajax({
    url: 'workbench/activity/selectActivityByConditionForPage.do',
    data: {
        name: name,
        owner: owner,
        startDate: startDate,
        endDate: endDate,
        pageNo: pageNo,
        pageSize: pageSize
    },
    type: 'post',
    dataType: 'json',
    success: function (data) {
        // 显示市场活动列表,遍历list<activity> index相当于遍历下标 object循环变量 加\ 转义
        var htmlStr = "";
        $.each(data.activity, function (index, obj) {
            htmlStr += "<tr class=\"active\">";
            htmlStr += "<td><input type=\"checkbox\" value=\"" + obj.id + "\"/></td>";
            htmlStr += "<td><a style=\"text - decoration:none;cursor: pointer;onclick=\"window.location.href='detdetail.jsp';\">" + obj.name + "</a></td>";
            htmlStr += "<td>" + obj.owner + "</td>";
            htmlStr += "<td>" + obj.startDate + "</td>";
            htmlStr += "<td>" + obj.endDate + "</td>";
            htmlStr += "</tr>";
        });
        // 写入 页面片段字符串
        $("#tBody").html(htmlStr);
        // 取消全选按钮
```

①、异步：
在异步模式下，当我们使用AJAX发送完请求后，可能还有代码需要执行。这个时候可能由于种种原因导致服务器还没有响应我们的请求，但是因为我们采用了异步执行方式，所有包含AJAX请求代码的函数中的剩余代码将继续执行。如果我们是将请求结果交由另外一个JS函数去处理的，那么，这个时候就好比两条线程同时执行一样。

当ajax发送请求后，在等待server端返回的这个过程中，前台会继续 执行ajax块后面的脚本，直到server端返回正确的结果才会去执行success，也就是说这时候执行的是两个线程，ajax块发出请求后一个线程 和ajax块后面的脚本（另一个线程）
在上例中，当ajax块发出请求后，他将停留function1()，等待server端的返回，但同时（在这个等待过程中），前台会去执行function2()。

②、同步
在同步模式下，当我们使用AJAX发送完请求后，后续还有代码需要执行，我们同样将服务器响应交由另一个JS函数去处理，但是这时的代码执行情况是：在服务器没有响应或者处理响应结果的JS函数还没有处理完成return时，包含请求代码的函数的剩余代码是不能够执行的。就好比单线程一样，请求发出后就进入阻塞状态，知道接触阻塞余下的代码才会继续执行。如下：

当执行当前AJAX的时候会停止执行后面的JS代码，直到AJAX执行完毕后时，才能继续执行后面的JS代码。
当把async设为false时，这时ajax的请求时同步的，也就是说，这个时候ajax块发出请求后，他会等待在function1（）这个地方，不会去执行function2()，直到function1()部分执行完毕。

二、使用场景
①、异步：发送一个请求,不需要等待返回，随时可以再发送下一个请求
②、同步：一步一步来操作，等待请求返回的数据，再执行下一步

三、总结：
异步： 多线程；无需等待
同步： 单线程，需要等待（容易阻塞进程，页面卡顿用户体验不好等）
