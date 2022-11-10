<template>
  <div v-if="currentQna">
    <div class="mb-3">
      <label for="question" class="form-label"> Question </label>
      <input type="text" class="form-control" id="question" required name="question"
             v-model="currentQna.question">
    </div>
    <div class="mb-3">
      <label for="answer" class="form-label"> Answer </label>
      <input type="text" class="form-control" id="answer" required name="answer" v-model="currentQna.answer">
    </div>
    <div class="mb-3">
      <label for="questioner" class="form-label"> Questioner </label>
      <input type="questioner" class="form-control" id="questioner" required name="questioner" v-model="currentQna.questioner">
    </div>
    <div class="mb-3">
      <label for="answerer" class="form-label"> Answerer </label>
      <input type="text" class="form-control" id="answerer" required name="answerer" v-model="currentQna.answerer">
    </div>
    <div class="mb-3">
      <button @click="updateQna" class="btn btn-primary me-3">Update</button>
      <button @click="deleteQna" class="btn btn-danger">Delete</button>
    </div>
    <div class="alert alert-success" role="alert" v-if="message">
      {{ message }}
    </div>
  </div>
</template>

<script>
import QnaDataService from '@/services/QnaDataService';
export default {
  data() {
    return {
      currentQna: null,
      message: ""
    };
  },
  methods: {
    // 부서번호(dno)로 조회 요청하는 함수
    getQna(qno) {
      // axios 공통함수 호출
      QnaDataService.get(qno)
      // 성공하면 .then() 결과가 리턴됨
      .then(response => {
        // springboot 결과를 리턴함(부서 객체)
        this.currentQna = response.data;
        // 콘솔 로그 출력
        console.log(response.data);
      })
      // 실패하면 .catch() 에러메세지가 리턴됨
      .catch(e => {
        console.log(e);
      });
    },
    // 부서정보를 수정 요청하는 함수
    updateQna() {
      // axios 공통함수 호출
      QnaDataService.update(this.currentQna.qno, this.currentQna)
      // 성공하면 then() 결과가 전송됨
      .then(response => {
        console.log(response.data);
        this.message = "The Qna was updated successfully!";
      })
      // 실패하면 .catch() 에러메세지가 전송됨
      .catch(e => {
        console.log(e);
      });
    },
    // 부서정보를 삭제 요청하는 함수
    deleteQna() {
      // axios 공통함수 호출
      QnaDataService.delete(this.currentQna.qno)
      // 성공하면 then() 결과가 전송됨
      .then(response => {
        console.log(response.data);
        // 첫페이지(전체목록_조회_페이지) 강제 이동 : /dept 
        this.$router.push("/qna");
      })
      // 실패하면 .catch() 에러메세지가 전송됨
      .catch(e => {
        console.log(e);
      });
    }
  },
  // 화면이 뜨자 마자 실행되는 이벤트
  mounted() {
    // 클라이언트쪽 디버깅
    // alert(this.$route.params.dno);
    // console.log(this.$route.params.dno);
    
    this.message = "";
  //  this.$route.params.dno : 이전페이지에서 전송한 매개변수는 $route.params 안에 있음
  // $route 객체 : 주로 url 매개변수 정보들이 있음
  // router/index.js 상세페이지 url의 매개변수명 : :dno 
    this.getQna(this.$route.params.qno);
  },
};
</script>

<style>
  .edit-form {
    max-width: 300px;
    margin: auto;
  }
</style>