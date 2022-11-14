<template>
  <div id="app">
    <nav class="navbar navbar-expand navbar-dark bg-secondary">
      <router-link to="/" class="navbar-brand ms-3">StarBucks Logo</router-link>
      <div class="navbar-nav mr-auto">
        <!-- 대메뉴 #1 : COFFEE -->
        <li class="nav-item dropdown ms-5">
          <a
            class="nav-link dropdown-toggle"
            data-bs-toggle="dropdown"
            href="#"
            role="button"
            aria-expanded="false"
            >COFFEE</a
          >
          <ul class="dropdown-menu bg-secondary">
            <li>
              <div class="mb-1">
                <router-link to="/coffeelist" class="dropdown-item text-white">커피</router-link>
                <router-link to="" class="dropdown-item">스타벅스 원두</router-link>
                <router-link to="" class="dropdown-item">스타벅스 비아</router-link>
                <router-link to="" class="dropdown-item">스타벅스 스타벅스앳홈 by 캡슐</router-link>
              </div>
            </li>
            <li>
              <div class="mb-1">
                <router-link to="" class="dropdown-item text-white">나와 어울리는 커피</router-link>
              </div>
            </li>
            <li>
              <div>
                <router-link to="" class="dropdown-item text-white">스타벅스 리저브™️</router-link>
                <router-link to="" class="dropdown-item">RESERVE MAGAZINE</router-link>
              </div>
            </li>
            <li>
              <div>
                <router-link to="" class="dropdown-item text-white">에스프레소 음료</router-link>
                <router-link to="" class="dropdown-item">도피오</router-link>
                <router-link to="" class="dropdown-item">에스프레소 마끼아또</router-link>
                <router-link to="" class="dropdown-item">아메리카노</router-link>
                <router-link to="" class="dropdown-item">마끼아또</router-link>
                <router-link to="" class="dropdown-item">카푸치노</router-link>
                <router-link to="" class="dropdown-item">라떼</router-link>
                <router-link to="" class="dropdown-item">모카</router-link>
              </div>
            </li>
            <li>
              <div>
               <router-link to="" class="dropdown-item text-white">최상의 커피를 즐기는 법</router-link>
               <router-link to="" class="dropdown-item">커피 프레스</router-link>
               <router-link to="" class="dropdown-item">푸어 오버</router-link>
               <router-link to="" class="dropdown-item">아이스 푸어 오버</router-link>
               <router-link to="" class="dropdown-item">커피 메이커</router-link>
               <router-link to="" class="dropdown-item">리저브를 매장에서 다양하게 즐기는 법</router-link>
              </div>
            </li>
            <li>
              <router-link to="" class="dropdown-item"
                >커피 이야기</router-link
              >
            </li>
          </ul>
        </li>

        <!-- 대메뉴 #2 : MENU -->
        <li class="nav-item dropdown ms-5">
          <a
            class="nav-link dropdown-toggle"
            data-bs-toggle="dropdown"
            href="#"
            role="button"
            aria-expanded="false"
            >MENU</a
          >
          <ul class="dropdown-menu">
            <li>
              <router-link to="" class="dropdown-item">음료</router-link>
            </li>
            <li>
              <router-link to="" class="dropdown-item">푸드</router-link>
            </li>
            <li>
              <router-link to="" class="dropdown-item">상품</router-link>
            </li>
            <li>
              <router-link to="" class="dropdown-item">카드</router-link>
            </li>
            <li>
              <router-link to="" class="dropdown-item">메뉴 이야기</router-link>
            </li>
          </ul>
        </li>

        <!-- 대메뉴 #3 : STORE -->
        <li class="nav-item dropdown ms-5">
          <a
            class="nav-link dropdown-toggle"
            data-bs-toggle="dropdown"
            href="#"
            role="button"
            aria-expanded="false"
            >STORE</a
          >
          <ul class="dropdown-menu">
            <li>
              <router-link to="" class="dropdown-item">매장 찾기</router-link>
            </li>
            <li>
              <router-link to="" class="dropdown-item">드라이브 스루 매장</router-link>
            </li>
            <li>
              <router-link to="" class="dropdown-item">스타벅스 리저브™️ 매장</router-link>
            </li>
            <li>
              <router-link to="" class="dropdown-item">커뮤니티 스토어 매장</router-link>
            </li>
            <li>
              <router-link to="" class="dropdown-item">매장 이야기</router-link>
            </li>
          </ul>
        </li>

        <!-- 대메뉴 #4 : RESPONSIBILITY -->
        <li class="nav-item dropdown ms-5">
          <a
            class="nav-link dropdown-toggle"
            data-bs-toggle="dropdown"
            href="#"
            role="button"
            aria-expanded="false"
            >RESPONSIBILITY</a
          >
          <ul class="dropdown-menu">
            <li>
              <router-link to="" class="dropdown-item">사회공헌 캠페인 소개</router-link>
            </li>
            <li>
              <router-link to="" class="dropdown-item">지역 사회 참여 활동</router-link>
            </li>
            <li>
              <router-link to="" class="dropdown-item">환경보호 활동</router-link>
            </li>
            <li>
              <router-link to="" class="dropdown-item">윤리 구매</router-link>
            </li>
            <li>
              <router-link to="" class="dropdown-item">글로벌 사회 공헌</router-link>
            </li>
          </ul>
        </li>

        <!-- 대메뉴 #5 : STARBUCKS REWARDS -->
        <li class="nav-item dropdown ms-5">
          <a
            class="nav-link dropdown-toggle"
            data-bs-toggle="dropdown"
            href="#"
            role="button"
            aria-expanded="false"
            >STARBUCKS REWARDS</a
          >
          <ul class="dropdown-menu">
            <li>
              <router-link to="" class="dropdown-item">스타벅스 리워드</router-link>
            </li>
            <li>
              <router-link to="" class="dropdown-item">스타벅스 카드</router-link>
            </li>
            <li>
              <router-link to="" class="dropdown-item">스타벅스 e-Gift Card</router-link>
            </li>
          </ul>
        </li>


        <!-- 대메뉴 #6 : WHAT'S NEW -->
        <li class="nav-item dropdown ms-5">
          <a
            class="nav-link dropdown-toggle"
            data-bs-toggle="dropdown"
            href="#"
            role="button"
            aria-expanded="false"
            >WHAT'S NEW</a
          >
          <ul class="dropdown-menu">
            <li>
              <router-link to="" class="dropdown-item">이벤트</router-link>
            </li>
            <li>
              <router-link to="" class="dropdown-item">뉴스</router-link>
            </li>
            <li>
              <router-link to="" class="dropdown-item">매장별 이벤트</router-link>
            </li>
            <li>
              <router-link to="" class="dropdown-item">공지사항</router-link>
            </li>
            <li>
              <router-link to="" class="dropdown-item">월페이퍼</router-link>
            </li>
          </ul>
        </li>



      </div>
    </nav>

    <div class="container mt-3">
      <router-view />
    </div>
  </div>
</template>

<script>
export default {
  name: "app",
};
</script>

<style>
/* 드롭다운 메뉴 마우스 가져다대면 자동으로 내려오게 만들기 */
  .dropdown:hover .dropdown-menu {
    display: block;
    margin-top: 0;
  }
</style>