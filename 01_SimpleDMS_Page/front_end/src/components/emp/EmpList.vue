<template>
  <!-- EmpList.vue -->
  <div class="list row">
    <!-- 검색어 시작 -->
    <div class="col-md-8">
      <div class="input-group mb-3">
        <!--  검색어 입력 -->
        <input
          type="text"
          class="form-control"
          placeholder="Search by Ename"
          v-model="searchEname"
        />
        <!--  검색어 버튼 -->
        <div class="input-group-append">
          <button
            class="btn btn-outline-secondary"
            type="button"
            @click="
              page = 1;
              retrieveEmp();
            "
          >
            Search
          </button>
        </div>
      </div>
    </div>
    <!-- 검색어 끝 -->

    <!-- <!— 페이징 + 전체 목록 시작 —> -->
    <!-- <!— 페이징 양식 시작 —> -->
    <div class="col-md-12">
      <!-- <!— 셀렉트 박스 : 기본 페이지 변경 —> -->
      <!-- <!— change :  handlePageSizeChange($event), 1페이지당 개수 변경 시 실행되는 이벤트—> -->
      <!-- <!—           $event : html 태그의 기본 이벤트, 이 객체로 현재 선택 또는 클릭한 html태그(양식)를 알 수 있음.  -->
                     <!-- event.target (현재 사용자가 선택한 양식(여기서는 셀렉트 박스내 목록을 의미 ) —> -->
      <div class="mb-3">
        Items per Page:
        <select v-model="pageSize" @change="handlePageSizeChange($event)">
          <option v-for="size in pageSizes" :key="size" :value="size">
            {{ size }}
          </option>
        </select>
      </div>

      <!-- <!— b-pagination : bootstrap-vue 의 페이지 번호 컨트롤 —> -->
      <!-- <!— total-rows : 전체 데이터 개수 —> -->
      <!-- <!— per-page : 1페이지 당 개수 —> -->
      <!-- prev-text="화면에_보일_글자" : 전페이지 이동 -->
      <!-- next-text="화면에_보일_글자" : 다음페이지 이동 -->
      <!-- <!— change : handlePageChange(), 페이지 번호 변경 시 실행되는 이벤트 —> -->
      <b-pagination
        v-model="page"
        :total-rows="count"
        :per-page="pageSize"
        prev-text="Prev"
        next-text="Next"
        @change="handlePageChange"
      ></b-pagination>
    </div>
    <!-- <!— 페이징 양식 끝 —> -->


    <!-- 전체 목록 조회 시작 -->
    <div class="col-md-6">
      <h4>Emp List</h4>
      <ul class="list-group" id="emp-list">
        <li
          class="list-group-item"
          :class="{ active: index == currentIndex }"
          v-for="(data, index) in emp"
          :key="index"
          @click="setActiveEmp(data, index)"
        >
          {{ data.ename }}
        </li>
      </ul>

      <button class="m-3 btn btn-sm btn-danger" @click="removeAllEmp">
        Remove All
      </button>
    </div>
    <!-- 전체 목록 조회 끝 -->

    <!-- 간략 상세 목록 시작 -->
    <div class="col-md-6">
      <div v-if="currentEmp">
        <h4>Emp</h4>
        <div>
          <label><strong>Ename:</strong></label> {{ currentEmp.ename }}
        </div>
        <div>
          <label><strong>job:</strong></label> {{ currentEmp.job }}
        </div>
        <div>
          <label><strong>manager:</strong></label> {{ currentEmp.manager }}
        </div>
        <div>
          <label><strong>hiredate:</strong></label> {{ currentEmp.hiredate }}
        </div>
        <div>
          <label><strong>salary:</strong></label> {{ currentEmp.salary }}
        </div>
        <div>
          <label><strong>commission:</strong></label>
          {{ currentEmp.commission }}
        </div>
        <div>
          <label><strong>eno:</strong></label> {{ currentEmp.eno }}
        </div>

        <router-link :to="'/emp/' + currentEmp.eno"
          ><span class="badge bg-success">Edit</span></router-link
        >
      </div>
      <div v-else>
        <br />
        <p>Please click on a Emp...</p>
      </div>
    </div>
    <!-- 간략 상세 목록 끝 -->
  </div>
</template>

<script>
// axios 공통함수 import
import EmpDataService from "../../services/EmpDataService";

export default {
  // 변수 정의하는 곳 : data(), 초기화
  data() {
    return {
      emp: [],
      currentEmp: null,
      currentIndex: -1,
      searchEname: "",

      page: 1,
      count: 0,
      pageSize: 3,
      pageSizes: [3,6,9],
    };
  },
  // 함수 정의하는 곳 : methods:
  methods: {
    // axios , 모든 부서 정보 조회 요청 함수
    retrieveEmp() {
      EmpDataService.getAll(this.searchEname, this.page-1, this.pageSize)
        // 성공하면 .then() 결과가 전송됨
        .then((response) => {
          const { emp, totalItems } = response.data;
          this.emp = emp;
          this.count = totalItems;

          // 디버깅 콘솔에 정보 출력
          console.log(response.data);
        })
        // 실패하면 .catch() 에 에러가 전송됨
        .catch((e) => {
          console.log(e);
        });
    },
    // select box 값 변경시 실행되는 함수(재조회)
    handlePageSizeChange(event) {
      this.pageSize = event.target.value; // 한페이지당 개수 저장(3, 6, 9)
      this.page =1;
      // 재조회 함수 호출
      this.retrieveEmp();
    },
    // 페이지 번호 변경시 실행되는 함수(재조회)
    handlePageChange(value) {
      this.page = value; // 매개변수값으로 현재페이지 변경
      // 재조회 함수 호출
      this.retrieveEmp();
    },
    
    // 목록을 클릭했을때 현재 부서객체, 인덱스번호를 저장하는 함수
    setActiveEmp(data, index) {
      this.currentEmp = data;
      this.currentIndex = index;
    },
    // axios, 모든 부서 정보 삭제 요청 함수
    removeAllEmp() {
      EmpDataService.deleteAll()
        // 성공하면 .then() 결과가 전송됨
        .then((response) => {
          // 디버깅 콘솔에 정보 출력
          console.log(response.data);
          // 전체 목록 재조회
          this.retrieveEmp();
          // currentEmp, currentIndex 초기화
          this.currentEmp = null;
          this.currentIndex = -1;
        })
        // 실패하면 .catch() 에 에러가 전송됨
        .catch((e) => {
          console.log(e);
        });
    },
  },
  // 화면이 뜨자마자 실행되는 이벤트(라이프 사이클 함수) : mounted(), created()
  mounted() {
    this.retrieveEmp(); // 화면 로딩시 전체 조회함수 실행
  },
};
</script>

<style>
.list {
  text-align: left;
  max-width: 750px;
  margin: auto;
}
</style>
