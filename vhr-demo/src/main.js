import {createApp} from 'vue'
import App from './App.vue'
import store from './store'
import router from "@/router";
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css';
import * as ElementPlusIconsVue from '@element-plus/icons-vue';
import {formatRoutes} from './utils/menus'
// 导入font-awesome图标
import 'font-awesome/css/font-awesome.min.css'
import request from "@/api/request";
import {ElMessage} from "element-plus";


const app = createApp(App);
router.beforeEach((to, from, next) => {
    if (to.path ==='/') {
        next()
    } else {
        if(window.sessionStorage.getItem("user")===null){
            ElMessage.error("尚未登录，请登录");
            router.replace("/");
        }
        if (store.state.routes.length=== 0) {
            request.menuinit().then(resp => {
                let b = formatRoutes(resp.data);
                // 将查询到的菜单数据存入store中
                store.commit('menu', b);
                for (var i = 0; i < b.length; i++) {
                    router.addRoute(b[i]);
                }
                next({...to, replace: true})
            })
        } else {
            next();
        }
    }
});

app.use(store).use(router).use(ElementPlus).mount('#app');


for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
}
