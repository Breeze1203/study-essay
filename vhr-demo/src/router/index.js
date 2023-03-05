import { createRouter, createWebHistory } from 'vue-router'


const routes = [
  {
    path: '/',
    name: 'login',
    component: () => import('../views/Login.vue')
  },
  {
    path: '/Home',
    name: 'Home',
    component: () => import('../components/Home.vue'),
    children: [
      {
        path: '/test1',
        name: '导航一',
        component: () => import('../components/Test1.vue')
      }
      , {
        path: '/test2',
        name: '导航二',
        component: () => import('../components/Test2.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router

