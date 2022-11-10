<template>
  <!-- AddDept.vue -->
  <div class="submit-form">
    <!-- 새 양식 폼 시작 -->
    <div v-if="!submitted">
      <div class="form-group">
        <label for="dname">Dname</label>
        <input
          type="text"
          class="form-control"
          id="dname"
          required
          v-model="dept.dname"
          name="dname"
        />
      </div>

      <div class="form-group">
        <label for="loc">Loc</label>
        <input
          class="form-control"
          id="loc"
          required
          v-model="dept.loc"
          name="loc"
        />
      </div>

      <button @click="saveDept" class="btn btn-success">Submit</button>
    </div>
    <!-- 새 양식 폼  끝 -->

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newDept">Add</button>
    </div>
  </div>
</template>

<script>
// axios 공통함수 import
import DeptDataService from "../../services/DeptDataService";

export default {
  data() {
    return {
      dept: {
        dno: null,
        dname: "",
        loc: "",
      },
      // submit 버튼을 클릭하면 true가 되고, You submitted successfully! 화면에 출력됨


      submitted: false,
    };
  },
  methods: {
    saveDept(){
      // 임시 객체 변수 👉 springboot 전송
      // 부서번호는 자동생성되므로 빼고 전송함
      let data = {
        dname: this.dept.dname,
        loc: this.dept.loc,
      };

      // insert 요청 함수 호출
      DeptDataService.create(data)
      // 성공하면 .then
      .then(response => {this.dept.dno = response.data.dno;
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
    newDept(){
      // 새양식 다시 보여주기 함수, 변수 초기화
      this.submitted = false;
      this.dept = {}
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