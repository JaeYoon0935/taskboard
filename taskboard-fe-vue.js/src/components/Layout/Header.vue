<template>
  <header class="header">
    <div class="top-bar">
      <div class="user-logout">
        <!-- 인증 토큰이 있으면 로그아웃 버튼 노출 -->
        <!-- <button v-if="authState.token" @click="logOutProc">로그아웃</button> -->
      </div>
    </div>

    <!-- 네비게이션 바 -->
    <div class="nav-container">
      <div class="logo">
        <router-link to="/" style="text-decoration: none; color: inherit;">
          <h1>Vue-board</h1>
        </router-link>
      </div>
      <nav class="menu">
        <ul>
          <li><router-link to="/UserMng">사용자 관리</router-link></li>
          <li><router-link to="/Board">게시판</router-link></li>
          <li><router-link to="/TodoList">To-do-List</router-link></li>
        </ul>
      </nav>
    </div>
  </header>
</template>

<script setup>
import { inject } from 'vue'
import { useRouter } from 'vue-router'

// inject로 전역 인증 상태 받아오기 (AuthProvider에서 provide된 거라고 가정)
const authState = inject('authState')
const authDispatch = inject('authDispatch')
const router = useRouter()

function logOutProc() {
  alert("로그아웃 되었습니다.")
  // 상태변경 (dispatch("LOGOUT")) 역할
  if (authDispatch) {
    authDispatch({ type: "LOGOUT" })
  }
  router.push('/')
}
</script>

<style scoped>

</style>
