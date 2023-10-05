import querystring from "querystring";
import axios from "axios";
import {ElMessage} from 'element-plus';
import router from "../router";

const errorHandle = (status, info) => {
    switch (status) {
        case 400:
            ElMessage.error("语义有误");
            break;
        case 401:
            ElMessage.error("认证失败");
            break;
        case 403:
            router.replace("/")
            break;
        case 404:
            break;
        case 500:
            ElMessage.error("服务器遇到意外");
            break;
        case 502:
            ElMessage.error("服务器无响应");
            break;
        default:
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
        let array = ['/api/employee/basic/addEmp',
            '/api/employee/basic/updateEmp',
            '/api/employee/basic/Advanched',
            '/api/sal/sob/',
            '/api/sal/sob/updateSalary'];
        let equal = true;
        // 对post方法参数进行处理
        if (config.method === 'post') {
            for (let i = 0; i < array.length; i++) {
                // 如果访问的路径不属于数组上面路径，就处理请求参数
                if (config.url === array[i]) {
                    equal = false;
                    break;
                }
            }
            if (equal) {
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
            ElMessage.error("服务器遇到意外，请稍后尝试");
            router.replace("/");
            return;
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


