<template>
  <div>
    <div v-if="!submitted">
      <div class="mb-3">
        <label for="firstName" class="form-label">First Name</label>
        <input
          type="text"
          class="form-control"
          id="firstName"
          required
          name="firstName"
          v-model="customer.firstName"
        />
      </div>
      <div class="mb-3">
        <label for="lastName" class="form-label">Last Name</label>
        <input
          type="text"
          class="form-control"
          id="lastName"
          required
          name="lastName"
          v-model="customer.lastName"
        />
      </div>
      <div class="mb-3">
        <label for="email" class="form-label">Email</label>
        <input
          type="email"
          class="form-control"
          id="email"
          required
          name="email"
          v-model="customer.email"
        />
      </div>
      <div class="mb-3">
        <label for="phone" class="form-label">Phone</label>
        <input
          type="text"
          class="form-control"
          id="phone"
          required
          name="phone"
          v-model="customer.phone"
        />
      </div>
      <div class="mb-3">
        <button @click="saveCustomer" class="btn btn-primary">Submit</button>
      </div>
    </div>
    <div v-else>
      <div class="alert alert-success" role="alert">
        Save customer successfully!
      </div>
      <button @click="newCustomer" class="btn btn-primary">
        Add New Customer
      </button>
    </div>
  </div>
</template>

<script>
// axios 공통함수 import
import CustomerDataService from "../../services/CustomerDataService";

export default {
  data() {
    return {
      customer: {
        cid: null,
        firstName: "",
        lastName: "",
        email: "",
        phone:'',
      },
      // submit 버튼을 클릭하면 true 가 되고, You submitted successfully! 화면에 출력됨
      submitted: false,
    };
  },
  methods: {
    saveCustomer() {
      // 임시 객체 변수 -> springboot 전송
      // 부서번호는(dno) 자동생성되므로 빼고 전송함
      let data = {
        cid: this.customer.cid,
        firstName: this.customer.firstName,
        lastName: this.customer.lastName,
        email: this.customer.email,
        phone: this.customer.phone,
      };

      // insert 요청 함수 호출(axios 공통함수 호출)
      CustomerDataService.create(data)
        // 성공하면 then() 결과가 전송됨
        .then((response) => {
          this.customer.cid = response.data.cid;
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
    newCustomer() {
      // 새양식 다시 보여주기 함수, 변수 초기화
      this.submitted = false;
      this.customer = {};
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
