<template>
  <div>
    <div v-if="!submitted">
      <div class="mb-3">
        <label for="firstName" class="form-label"> Qustion </label>
        <input
          type="text"
          class="form-control"
          id="Qustion"
          required
          name="qustion"
          v-model="qna.qustion"
        />
      </div>
      <div class="mb-3">
        <label for="lastName" class="form-label"> Answer </label>
        <input
          type="text"
          class="form-control"
          id="answer"
          required
          name="answer"
          v-model="qna.answer"
        />
      </div> 
      <div class="mb-3">
        <label for="email" class="form-label"> Questioner </label>
        <input
          type="text"
          class="form-control"
          id="questioner"
          required
          name="questioner"
          v-model="qna.questioner"
        />
      </div>
      <div class="mb-3">
        <label for="email" class="form-label"> Answerer </label>
        <input
          type="text"
          class="form-control"
          id="answerer"
          required
          name="answerer"
          v-model="qna.answerer"
        />
      </div>
      <div class="mb-3">
        <button @click="saveQna" class="btn btn-primary">Submit</button>
      </div>
    </div>
    <div v-else>
      <div class="alert alert-success" role="alert">
        Save Qna successfully!
      </div>
      <button @click="newQna" class="btn btn-primary">
        Add New Qna
      </button>
    </div>
  </div>
</template>

<script>
// axios 공통함수 import
import QnaDataService from "../../services/QnaDataService";

export default {
  data() {
    return {
      qna: {
        qno: null,
        question: "",
        answer: "",
        questioner: "",
        answerer:'',
      },
      // submit 버튼을 클릭하면 true 가 되고, You submitted successfully! 화면에 출력됨
      submitted: false,
    };
  },
  methods: {
    saveQna() {
      let data = {
        qno: this.qna.qno,
        question: this.qna.question,
        answer: this.qna.answer,
        questioner: this.qna.questioner,
        answerer: this.qna.answerer,
      };

      // insert 요청 함수 호출(axios 공통함수 호출)
      QnaDataService.create(data)
        // 성공하면 then() 결과가 전송됨
        .then((response) => {
          this.qna.qno = response.data.qno;
          // 콘솔 로그 출력(response.data)
          console.log(response.data);
          // 변수 submitted
          this.submitted = true;
        })
        // 실패하면 .catch() 결과가 전송됨
        .catch((e) => {
          console.log(e);
        });
    },
    newQna() {
      // 새양식 다시 보여주기 함수, 변수 초기화
      this.submitted = false;
      this.qna = {};
    },
  },
};
</script>

<style>
.submit-form {
  max-width: 300px;
  margin: auto;
}
</style>
