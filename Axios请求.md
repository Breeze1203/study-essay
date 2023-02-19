#### Axios请求

###### 跨域解决方案:

第一种：前端解决方案

```js
// 解决跨域问题

  devServer:{

    proxy:{

      '/api':{

        target:'http://iwenwiki.com/',

        changeOrigin:true

      },

    },

  }

```

第二种：后端解决

$\textcolor{red}{Spring Framework[为 CORS 提供一流的支持,CORS 必须在 Spring Security 之前处理，因为飞行前请求不包含任何 cookie}$（即`JSESSIONID`. 如果请求不包含任何 cookie 并且 Spring Security 是第一个，则请求确定用户未通过身份验证（因为请求中没有 cookie）并拒绝它

确保首先处理 CORS 的最简单方法是使用`CorsFilter`. `CorsFilter`用户可以通过提供`CorsConfigurationSource`使用以下内容的与 Spring Security集成

```java
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			// by default uses a Bean by the name of corsConfigurationSource
			.cors(withDefaults())
			...
		return http.build();
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowedOrigins(Arrays.asList("https://example.com"));
		configuration.setAllowedMethods(Arrays.asList("GET","POST"));
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}
```

如果您使用 Spring MVC 的 CORS 支持，则可以省略指定`CorsConfigurationSource`并且 Spring Security 使用提供给 Spring MVC 的 CORS 配置：

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

```java
@Bean
public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	http
		// if Spring MVC is on classpath and no CorsConfigurationSource is provided,
		// Spring Security will use CORS configuration provided to Spring MVC
		.cors(withDefaults())
		...
	return http.build();
}
}
```
后端框架也很多，实现原理差不多，都是修改下相应头。以常用的Java SpringCloud 和nodejs koa 框架为例。

Http 协议CORS头
跨域其实也是http层面上可以解决的问题，后端解决也是比较简单的，也是项目常见的解决手法。

CORS （Cross-Origin Resource Sharing，跨域资源共享）是一个系统，它由一系列传输的HTTP头组成，这些HTTP头决定浏览器是否阻止前端 JavaScript 代码获取跨域请求的响应。

同源安全策略 默认阻止“跨域”获取资源。但是 CORS 给了web服务器这样的权限，即服务器可以选择，允许跨域请求访问到它们的资源。

Access-Control-Allow-Origin
指示请求的资源能共享给哪些域。
Access-Control-Allow-Credentials
指示当请求的凭证标记为 true 时，是否响应该请求。
Access-Control-Allow-Headers
用在对预请求的响应中，指示实际的请求中可以使用哪些 HTTP 头。
Access-Control-Allow-Methods
指定对预请求的响应中，哪些 HTTP 方法允许访问请求的资源。
Access-Control-Expose-Headers
指示哪些 HTTP 头的名称能在响应中列出。
Access-Control-Max-Age
指示预请求的结果能被缓存多久。
Access-Control-Request-Headers
用于发起一个预请求，告知服务器正式请求会使用那些 HTTP 头。
Access-Control-Request-Method
用于发起一个预请求，告知服务器正式请求会使用哪一种 HTTP 请求方法。
Origin
指示获取资源的请求是从什么域发起的。
SpringCloud设置跨域
在跨域过滤器里配置一下跨域头部，* 是通配符即允许所有。SpringCloud设置跨域

在跨域过滤器里配置一下跨域头部，* 是通配符即允许所有

```java
@Configuration
public class GatewayCorsConfiguation {
 
    @Bean
    public CorsFilter corsFilter(){
        // 初始化cors配置对象
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowCredentials(true); // 允许使用cookie，但是使用cookie是addAllowedOrigin必须是具体的地址，不能是*
//        configuration.addAllowedOrigin("*");
        configuration.addAllowedOrigin("http://manage.leyou.com");
        configuration.addAllowedMethod("*");  //允许的请求方式,get,put,post,delete
        configuration.addAllowedHeader("*");//允许的头信息
 
        //初始化cors的源对象配置
        UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
        corsConfigurationSource.registerCorsConfiguration("/**",configuration);
 
        //3.返回新的CorsFilter.
        return new CorsFilter(corsConfigurationSource);
    }
}

```

###### 拦截器:

```javascript
// Add a request interceptor
axios.interceptors.request.use(function (config) {
    // Do something before request is sent
    return config;
  }, function (error) {
    // Do something with request error
    return Promise.reject(error);
  });

// Add a response interceptor
axios.interceptors.response.use(function (response) {
    // Any status code that lie within the range of 2xx cause this function to trigger
    // Do something with response data
    return response;
  }, function (error) {
    // Any status codes that falls outside the range of 2xx cause this function to trigger
    // Do something with response error
    return Promise.reject(error);
  });
```

###### 发送请求:

```javascript
import axios from "axios"
import querystring from "querystring";

const errorHandle = (status,info) => {
    switch(status){
        case 400:
            console.log("语义有误");
            break;
        case 401:
            console.log("服务器认证失败");
            break;
        case 403:
            console.log("服务器拒绝访问");
            break;
        case 404:
            console.log("地址错误");
            break;
        case 500:
            console.log("服务器遇到意外");
            break;
        case 502:
            console.log("服务器无响应");
            break;
        default:
            console.log(info);
            break;
    }
}

// 创建一个axios的实例
const instance = axios.create({
    // 网络请求的公共配置信息
    timeout: 50
})

// 拦截器(发送请求之前)
instance.interceptors.request.use(
    // 里面包含两个参数config error
    config => {
        if (config.method === 'post') {
            config.data = querystring.stringify(config.data)
        }
        return config
    },
    error => {
        return Promise.reject(error)
    }
)

// 获取服务端发送的请求之前
instance.interceptors.response.use(
    response => {
        // 整个网络请求成功，但返回的数据不一定正确
        return response.status === 200 ? Promise.resolve(response) : Promise.reject(response)
    },
    error => {
        // 整个网络请求都不成功
        // const { response } = error
        return response.error
    }
)

// 导出网络实例
export default instance
```

```js
// 将axios挂载到全局
app.config.globalProperties.$axios=axios
app.mount('#app')
```

单独提取路径:

```java
// 创建对象
const base={
    baseUrl:'http://iwenwiki.com/',
    chengping:'api/blueberrypai/getChengpinDetails.php'

}
// 导出对象
export default base
```

```javascript
<!-- 此组件演示用Axios发送网络请求  先安装npm install --save axios-->
<template>
</template>
<script>
// 局部引入
// import axios from "axios"
// import querystring from "querystring";

// 导出组件
export default {
    name: 'Axios',
    data() {
        return {
            chengping: {}
        }
    }
    // mounted() {
    //     //get请求方式
    //     this.$axios({
    //         method: 'get',
    //         url: 'http://localhost:1111/user',
    //         data: {
    //             name: '张三',
    //             id: 2
    //         }
    //     }).then(res => {
    //         console.log(res.data);
    //     })

        // post请求

        // mounted() {
        // axios({
        //     method: "post",
        //     url: 'http://localhost:1111/user',
        //     contentType:'"application/json"',
        //     data:querystring.stringify( {
        //         name: '李白',
        //         id: 12323
        //     })
        // }).then(res => {
        //     console.log(res.data);
        // })
        // 快捷方式 get
        // this.$axios.get('http://iwenwiki.com/api/blueberrypai/getChengpinDetails.php').then(res=>{
        //     console.log(res.data);
        // })

        // 全局挂载
        // this.$axios.post('http://localhost:1111/user', querystring.stringify({
        //     name: '李白',
        //     id: 1223
        // }),
        // ).then(resd=>{
        //     console.log(resd.data);
        // })


   
}

</script>
<style>

</style>
```

#### 做项目中遇到的问题:

###### 引入element ui图标:

全局引入：

```javascript
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
 
const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
  app.component(key, component)
}
```

局部引入：

```javascript
<script>
import { ArrowDown } from '@element-plus/icons-vue'
export default {
  name: "App",
  components: {
    ArrowDown,
    
  }
}
</script>
```

###### 各个数据库的关联信息:

```properties
menu 的字段id对应字段parentId
表hr_role的hrid对应表hr的id
表hr_role的rid对应表menu_role的rid
menu_role的rid对应menu的id
```

