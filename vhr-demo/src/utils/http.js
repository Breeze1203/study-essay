import querystring from "querystring";
import axios from "axios";
import {ElMessage} from 'element-plus';
import router from "../router";

const errorHandle = (status, info) => {
    switch (status) {
        case 400:
            console.log("语义有误");
            break;
        case 401:
            console.log("认证失败");
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
const instace = axios.create({
    // 网络请求的公共配置信息
    timeout: 5000,
})

// 拦截器(发送请求之前)
instace.interceptors.request.use(
    config => {
        // 对post方法参数进行处理
        if (config.method === 'post') {
            if (config.url !== '/api/employee/basic/addEmp' && config.url !== '/api/employee/basic/updateEmp') {
                config.data = querystring.stringify(config.data)
            }
        }
        return config
    },
    error => {
        return Promise.reject(error)
    }
)

instace.interceptors.response.use(
    success => {
        if (success.data.status === 401) {
            router.replace("/");
            ElMessage.error("请重新登录");
        }
        if (success.data.status === 500) {
            ElMessage.error('用户名或密码错误，请重新输入')
            return;
        }
        if (success.data.message) {
            ElMessage.success({message: success.data.message})
        }
        return success;
    },
    error => {
        errorHandle(error.status, error.info)
        return Promise.reject(error)
    }
)

// 导出网络实例
export default instace;


