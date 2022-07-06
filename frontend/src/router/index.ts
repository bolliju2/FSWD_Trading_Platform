import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import Tabs from '../views/Tabs.vue'
import Login from '../views/Login.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: Login,
  },
  {
    path: '/tabs/',
    component: Tabs,
    children: [
      {
        path: '',
        redirect: '/tabs/currencies'
      },
      {
        path: 'currencies',
        component: () => import('@/views/Currencies.vue')
      },
      {
        path: 'portfolio',
        component: () => import('@/views/Portfolio.vue')
      },
      {
        path: 'transactions',
        component: () => import('@/views/BuyAndSell.vue')
      }
    ]
  }

  // {
  //   path: '/portfolio',
  //   component: () => import('@/views/Portfolio.vue')
  // },
  // {
  //   path: '/tabs/',
  //   component: Tabs,
  //   children: [
  //     {
  //       path: '',
  //       redirect: '/tabs/transaction'
  //     },
  //     {
  //       path: 'portfolio',
  //       component: () => import('@/views/Portfolio.vue')
  //     },
  //     {
  //       path: 'currencies',
  //       component: () => import('@/views/Currencies.vue')
  //     },
  //     {
  //       path: 'timerecord',
  //       component: () => import('@/views/Timerecord.vue')
  //     }
  //   ]
  // }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
