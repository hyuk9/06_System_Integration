<template>
  <div>
    <!-- search 관련 div 시작 -->
    <div class="col-md-8">
      <div class="input-group mb-3">
        <!--            Todo : 수정 시작 -->
        <input
          type="text"
          class="form-control"
          placeholder="Search by Email"
          v-model="searchEmail"
        />
        <div class="input-group-append">
          <button
            class="btn btn-outline-secondary"
            type="button"
            @click="
              page = 1;
              retrieveCustomer();
            "
          >
            Search
          </button>
        </div>
        <!--            Todo : 수정 끝 -->
      </div>
    </div>
    <!-- search 관련 div 끝 -->

    <!--    Todo : page 바 시작 -->
    <div class="col-md-12">
      <div class="mb-3">
        Items per Page:
        <select v-model="pageSize" @change="handlePageSizeChange($event)">
          <option v-for="size in pageSizes" :key="size" :value="size">
            <!--            size : 3, 6, 9 -->
            {{ size }}
          </option>
        </select>
      </div>

      <b-pagination
        v-model="page"
        :total-rows="count"
        :per-page="pageSize"
        prev-text="Prev"
        next-text="Next"
        @change="handlePageChange"
      ></b-pagination>
    </div>
    <!--    Todo : page 바 끝 -->

    <!-- 테이블 목록 시작 -->
    <table class="table">
      <thead>
        <tr>
          <th scope="col">Name</th>
          <!-- <th scope="col">Last Name</th> -->
          <th scope="col">Email</th>
          <th scope="col">Phone</th>
          <th scope="col">Actions</th>
        </tr>
      </thead>
      <tbody v-for="(data, index) in customer" :key="index">
        <tr>
          <td>{{ data.lastName + " " + data.firstName }}</td>
          <!-- <td>{{ data.lastName }}</td> -->
          <td>{{ data.email }}</td>
          <td>{{ data.phone }}</td>
          <td>
            <router-link :to="'/customer/' + data.cid"
              ><span class="badge bg-success">Edit</span></router-link
            >
          </td>
        </tr>
      </tbody>
    </table>
    <!-- 테이블 목록 끝 -->
  </div>
</template>

<script>
// axios 공통함수 import
import CustomerDataService from "../../services/CustomerDataService";

export default {
  data() {
    return {
      customer: [],
      searchEmail: "", // 이메일 검색

      // 페이징을 위한 변수 정의
      page: 1, // 현재 페이지
      count: 0, // 전체 데이터 건수
      pageSize: 3, // 한페이지당 몇개를 화면에 보여줄지 결정하는 변수

      pageSizes: [3, 6, 9], // select box 에 넣을 기본 데이터
    };
  },
  methods: {
    retrieveCustomer() {
      // getAll() -> (변경) getAll(email, page, size)
      CustomerDataService.getAll(this.searchEmail, this.page - 1, this.pageSize)
        // 성공하면 .then() 결과가 전송됨
        .then((response) => {
          // this.customer = response.data; ->(변경) const {customer, totalItems} = response.data;
          // let(const) { 속성명, 속성명2 } = 데이터 객체 배열 (모던자바문법 구조분해할당)
          const { customer, totalItems } = response.data; // springboot 의 전송된 맵 정보
          this.customer = customer; // 스프링부트에서 전송한 데이터
          this.count = totalItems; // 스프링부트에서 전송한 페이지정보(총 건수)
          // 디버깅 콘솔에 정보 출력
          console.log(response.data);
        })
        // 실패하면 .catch() 에 에러가 전송됨
        .catch((e) => {
          console.log(e);
        });
    },
    handlePageSizeChange(event) {
      this.pageSize = event.target.value; // 한페이지당 개수 저장(3, 6, 9)
      this.page = 1;
      // 재조회 함수 호출
      this.retrieveCustomer();
    },
    handlePageChange(value) {
      this.page = value; // 매개변수 값으로 현재페이지 변경
      // 재조회 함수 호출
      this.retrieveCustomer();
    },
  },
  mounted() {
    this.retrieveCustomer(); // 화면 로딩시 전체 조회함수 실행
  },
};
</script>

<style></style>