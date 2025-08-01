<template>
  <div class="board-container">
    <br /><br />
    <div class="board-wrapper">
      <table class="board-table">
        <tbody>
          <tr>
            <td>글 번호</td>
            <td>제목</td>
            <td>등록자</td>
            <td>등록일</td>
            <td>수정일</td>
          </tr>
          <tr v-for="post in postsList" :key="post.postsId">
            <td>{{ post.postsId }}</td>
            <td>
              <router-link :to="`/PostsDetail/${post.postsId}`">
                {{ post.title }}
              </router-link>
            </td>
            <td>{{ post.regUser }}</td>
            <td>{{ post.regDts }}</td>
            <td>{{ post.modDts ? post.modDts : '-' }}</td>
          </tr>
        </tbody>
      </table>

      <div class="board-actions">
        <router-link to="/PostsReg" class="register-btn">등록</router-link>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'

const postsList = ref([])

onMounted(async () => {
  try {
    const response = await axios.get('http://127.0.0.1:8080/api/Board/getPostsList')
    // setTimeout은 실제 개발에서는 거의 안 쓰지만, 리액트 코드 따라감
    setTimeout(() => {
      postsList.value = response.data
    }, 300)
  } catch (error) {
    alert("데이터 불러오기 실패")
    console.error(error)
  }
})
</script>

<style scoped src="./Board.css"></style>
