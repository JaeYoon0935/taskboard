import { createRouter, createWebHistory } from 'vue-router'
import Board from '../components/Board/Board.vue'
import PostsReg from '../components/Board/PostsReg.vue'
import PostsDetail from '../components/Board/PostsDetail.vue'
//import PostsDetail from '../components/Board/PostsDetail.vue'
//import Login from '../components/Auth/Login.vue'
//import SignUp from '../components/Auth/SignUp.vue'

const routes = [
  //{ path: '/login', component: Login },
  //{ path: '/signUp', component: SignUp },
  {
    path: '/',
    component: Board,
    meta: { requiresAuth: true }
  },
  {
    path: '/Board',
    component: Board,
    meta: { requiresAuth: true }
  },
  {
    path: '/PostsReg',
    component: PostsReg,
    meta: { requiresAuth: true }
  },
  {
    path: '/PostsDetail/:postsId',
    component: PostsDetail,
    meta: { requiresAuth: true }
  }
  /*
  {
    path: '/PostsDetail/:postsId',
    component: PostsDetail,
    meta: { requiresAuth: true }
  }
    */
]

const router = createRouter({
  history: createWebHistory(),
  routes
})
/*
// 보호 라우트 (PrivateRoute 역할)
router.beforeEach((to, from, next) => {
  if (to.meta.requiresAuth) {
    // 인증 체크 로직 (임시: localStorage에 토큰 등)
    const isAuth = localStorage.getItem('token')
    if (!isAuth) return next('/login')
  }
  next()
})
*/
export default router
