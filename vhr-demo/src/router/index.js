import { createRouter, createWebHistory } from 'vue-router'
import EmpAdv from '../components/emp/EmpBasic.vue'

const routes = [
  {
    path: '/',
    hidden:false,
    component: () => import('../views/Login.vue')
  },
  // {
  //   path: '/Home',
  //   name: 'Home',
  //   hidden:true,
  //   component: () => import('../components/Home.vue')
  // },
  // {
  //   path: '/text',
  //   component:EmpAdv
  // }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

// 循环输出路由对象里面的地址
export default router

