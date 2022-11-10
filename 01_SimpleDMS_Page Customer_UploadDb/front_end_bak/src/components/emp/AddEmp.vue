<template>
  <!-- AddDept.vue -->
  <div class="submit-form">
    <!-- 새 양식 폼 시작 -->
    <div v-if="!submitted">
      <div class="form-group">
        <!--  -->
        <label for="ename">Ename</label>
        <input
          type="text"
          class="form-control"
          id="ename"
          required
          v-model="emp.ename"
          name="ename"
        />
      </div>

      <div class="form-group">
        <label for="job">Job</label>
        <input
          class="form-control"
          id="job"
          required
          v-model="emp.job"
          name="job"
        />
      </div>


      <div class="form-group">
        <label for="manager">Manager</label>
        <input
          class="form-control"
          id="manager"
          required
          v-model="emp.manager"
          name="manager"
        />
      </div>


      <div class="form-group">
        <label for="hiredate">Hiredate</label>
        <input
          class="form-control"
          id="hiredate"
          required
          v-model="emp.hiredate"
          name="hiredate"
        />
      </div>

      <div class="form-group">
        <label for="salary">salary</label>
        <input
          class="form-control"
          id="salary"
          required
          v-model="emp.salary"
          name="salary"
        />
      </div>

      <div class="form-group">
        <label for="commission">commission</label>
        <input
          class="form-control"
          id="commission"
          required
          v-model="emp.commission"
          name="commission"
        />
      </div>

      <div class="form-group">
        <label for="dno">dno</label>
        <input
          class="form-control"
          id="dno"
          required
          v-model="emp.dno"
          name="dno"
        />
      </div>

      <button @click="saveEmp" class="btn btn-success">Submit</button>
    </div>
    <!-- 새 양식 폼  끝 -->

    <div v-else>
      <h4>You submitted successfully!</h4>
      <button class="btn btn-success" @click="newEmp">Add</button>
    </div>
  </div>
</template>

<script>
// axios 공통함수 import
import EmpDataService from "../../services/EmpDataService";

export default {
  data() {
    return {
      emp: {
        eno: null,
        ename: "",
        job: "",
        manager: null,
        hiredate: null,
        salary: null,
        commission: null,
        dno: null,
      },
      // submit 버튼을 클릭하면 true가 되고, You submitted successfully! 화면에 출력됨


      submitted: false,
    };
  },
  methods: {
    saveEmp(){
      // 임시 객체 변수 👉 springboot 전송
      // 사원번호(eno)는 자동생성되므로 빼고 전송함
      let data = {
        ename: this.emp.ename,
        job: this.emp.job,
        manager: this.emp.manager,
        hiredate: this.emp.hiredate,
        salary: this.emp.salary,
        commission: this.emp.commission,
        dno: this.emp.dno,
      };

      // insert 요청 함수 호출
      EmpDataService.create(data)
      // 성공하면 .then
      .then(response => {this.emp.eno = response.data.eno;
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
    newEmp(){
      // 새양식 다시 보여주기 함수, 변수 초기화
      this.submitted = false;
      this.emp = {}
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