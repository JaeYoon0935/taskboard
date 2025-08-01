<template>
  <div class="App">
    <Header />
    <router-view v-slot="{ Component }">
      <!-- 라우터 보호: 인터셉터 준비될 때만 렌더링 -->
      <component :is="Component" v-if="isInterceptorReady" />
      <div v-else>로딩 중...</div>
    </router-view>
    <Footer />
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import Header from './components/Layout/Header.vue'
import Footer from './components/Layout/Footer.vue'
import { setupAxiosInterceptors } from './utils/axiosInterceptors'

// 인터셉터 준비 여부
const isInterceptorReady = ref(false)

onMounted(async () => {
  await setupAxiosInterceptors()
  isInterceptorReady.value = true
})
</script>

<style src="./css/main.css"></style>
