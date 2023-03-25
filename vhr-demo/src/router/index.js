import { createRouter, createWebHistory } from 'vue-router'


const routes = [
  {
    path: '/',
    name: 'login',
    hidden:false,
    component: () => import('../views/Login.vue')
  },
  {
    path: '/Home',
    name: 'Home',
    hidden:true,
    component: () => import('../components/Home.vue')
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

