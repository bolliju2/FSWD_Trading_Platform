import { createRouter, createWebHistory } from '@ionic/vue-router';
import { RouteRecordRaw } from 'vue-router';
import Tabs from '../views/Tabs.vue'
import LoginPage from '../views/LoginPage.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    redirect: '/login'
  },
  {
    path: '/login',
    component: LoginPage,
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
        component: () => import('@/views/CurrenciesPage.vue')
      },
      {
        path: 'portfolio',
        component: () => import('@/views/PortfolioPage.vue')
      },
      {
        path: 'transactions',
        component: () => import('@/views/TradingPage.vue')
      }
    ]
  }
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
