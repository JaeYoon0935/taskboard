<template>
  <div class="board-container">
    <h2>게시글 등록</h2>
    <br />
    <div class="board-wrapper">
      <table class="board-table">
        <tbody>
          <tr>
            <td>제목</td>
            <td>
              <input v-model="title" />
            </td>
          </tr>
          <tr>
            <td>내용</td>
            <td>
              <textarea v-model="content"></textarea>
            </td>
          </tr>
        </tbody>
      </table>
      <div class="board-actions">
        <a href="#" @click.prevent="regProc" class="register-btn">등록</a>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, inject } from 'vue';
import axios from 'axios';
// 예시로 AuthContext에서 제공하는 email/userName을 inject로 받음.
// 실제로는 Pinia, Vuex, 혹은 props로 받아도 무방합니다.
const authState = inject('authState', { email: '', userName: '' }); 

const title = ref('');
const content = ref('');

const regProc = async () => {
  if (!title.value || !content.value) {
    alert('제목과 내용을 입력해주세요.');
    return;
  }

  try {
    const response = await axios.post('http://127.0.0.1:8080/api/Board/regPost', {
      title: title.value,
      content: content.value,
      email: authState.email,
      regUser: authState.userName,
    });
    if (response.status === 200) {
      alert('등록 되었습니다.');
      window.location.href = '/Board';
    }
  } catch (error) {
    alert('등록 실패하였습니다.');
  }
};
</script>
