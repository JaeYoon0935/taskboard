<template>
  <div class="posts-container">
    <h2>게시글 상세보기</h2>
    <br />
    <div class="posts-wrapper">
      <table class="posts-table">
        <tbody>
          <tr>
            <td>제목</td>
            <td>
              <input v-model="title" />
            </td>
          </tr>
          <tr>
            <td>작성자</td>
            <td class="posts-readOnlyCont">
              <span>{{ regUser }}</span>
            </td>
          </tr>
          <tr>
            <td>등록일</td>
            <td class="posts-readOnlyCont">
              <span>{{ regDts }}</span>
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
      <div class="posts-actions">
        <a href="#" @click.prevent="modProc" class="register-btn">수정</a>
        &nbsp;&nbsp;
        <a href="#" @click.prevent="delProc" class="register-btn">삭제</a>
      </div>
      <!--
      <CommentArea :postsId="postsId" />
      <div class="posts-actions">
        <router-link to="/Board" class="list-btn">목록</router-link>
      </div>
      -->
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, watch } from 'vue';
import { useRoute, useRouter } from 'vue-router';
import axios from 'axios';
//import CommentArea from './CommentArea.vue';

const route = useRoute();
const router = useRouter();
const postsId = route.params.postsId;

const title = ref('');
const content = ref('');
const regUser = ref('');
const regDts = ref('');

const getPostDetail = async () => {
  try {
    const response = await axios.post('http://127.0.0.1:8080/api/Board/postDetail', { postsId });
    title.value = response.data.title;
    content.value = response.data.content;
    regUser.value = response.data.regUser;
    regDts.value = response.data.regDts;
  } catch (error) {
    alert('게시글 불러오기에 실패하였습니다.');
  }
};

// postsId가 변경될 때마다 실행 (동적 라우팅 대응)
onMounted(getPostDetail);
watch(() => route.params.postsId, (newId, oldId) => {
  if (newId !== oldId) getPostDetail();
});

const modProc = async () => {
  if (!title.value || !content.value) {
    alert('제목과 내용을 입력해주세요.');
    return;
  }

  try {
    const response = await axios.post('http://127.0.0.1:8080/api/Board/modPost', {
      postsId, title: title.value, content: content.value
    });
    if (response.status === 200) {
      alert('수정 되었습니다.');
      router.push('/Board');
    } else {
      alert('게시글 수정에 실패하였습니다.');
    }
  } catch (error) {
    alert('게시글 수정에 실패하였습니다.');
  }
};

const delProc = async () => {
  if (window.confirm('게시글을 삭제하시겠습니까?')) {
    try {
      const response = await axios.post('http://127.0.0.1:8080/api/Board/delPost', { postsId });
      if (response.status === 200) {
        alert('삭제 되었습니다.');
        router.push('/Board');
      } else {
        alert('게시글 삭제에 실패하였습니다.');
      }
    } catch (error) {
      alert('게시글 삭제에 실패하였습니다.');
    }
  }
};
</script>


