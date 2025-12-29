import { createRouter, createWebHistory } from 'vue-router'
import Landing from '../pages/Index.vue'
import MainPage from '../pages/Main.vue'
import authAPI from '../api/auth'

const routes = [
  { path: '/', name: 'Index', component: Landing },
  { path: '/main', name: 'Main', component: MainPage, meta: { requiresAuth: true } }
]

const router = createRouter({
  history: createWebHistory(),
  routes
})

async function checkAuth() {
  if (!localStorage.getItem('auth')) {
    return false
  }
  try {
    await authAPI.login() // check endpoint
    return true
  } catch (e) {
    localStorage.removeItem('auth')
    return false
  }
}

router.beforeEach(async (to, from, next) => {
  const isAuthenticated = await checkAuth()

  if (to.meta.requiresAuth && !isAuthenticated) {
    next({ name: 'Index' })
  } else if (to.name === 'Index' && isAuthenticated) {
    next({ name: 'Main' })
  } else {
    next()
  }
})

export default router
