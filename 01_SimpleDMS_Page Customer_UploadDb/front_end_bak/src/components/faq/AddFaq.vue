<template>
  <!-- AddDept.vue -->
  <div class="submit-form">
    <!-- 새 양식 폼 시작 -->
    <div v-if="!submitted">
      <div class="form-group">
        <!-- title -->
        <label for="title">Title</label>
        <input
          type="text"
          class="form-control"
          id="title"
          required
          v-model="faq.title"
          name="title"
        />
      </div>
      <!-- content -->
      <div class="form-group">
        <label for="Content">Content</label>
        <input
          class="form-control"
          id="Content"
          required
          v-model="faq.Content"
          name="Content"
        />
      </div>
      <button @click="saveEmp" class="btn btn-success">Submit</button>
    </div>
    <!-- 새 양식 폼  끝 -->

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newFaq">Add</button>
    </div>
  </div>
</template>

<script>
// axios 공통함수 import
import FaqDataService from "../../services/FaqDataService";

export default {
  data() {
    return {
      emp: {
        no: null,
        title: '',
        content: '',
      },
      // submit 버튼을 클릭하면 true가 되고, You submitted successfully! 화면에 출력됨
      submitted: false,
    };
  },
  methods: {
    saveFaq(){
      // 임시 객체 변수 👉 springboot 전송
      // 사원번호(eno)는 자동생성되므로 빼고 전송함
      let data = {
        title: this.faq.title,
        content: this.faq.content,
      };

      // insert 요청 함수 호출
      FaqDataService.create(data)
      // 성공하면 .then
      .then(response => {this.faq.no = response.data.no;
      // console.log 출력
      console.log(response.data);
      // 변수 submitted
      this.submitted = true;
      })
      // 실패하면 .catch() 결과 전송됨
      .catch(e => {
        console.log(e);
      })
  },
    newFaq(){
      // 새양식 다시 보여주기 함수, 변수 초기화
      this.submitted = false;
      this.faq = {}
    }
  },
};
</script>
<style>
  .submit-form {
    max-width: 300px;
    margin: auto;
  }
</style>