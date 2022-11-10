<template>
  <!-- <nav>
    <router-link to="/">Home</router-link> |
    <router-link to="/about">About</router-link>
  </nav> -->
  <!-- router-view : 메뉴를 클릭하면 화면에 보이는 부분 -->
  <!-- container : 화면에 중앙정렬하면서 박스형태로 보임 -->
  <!-- mt : m(margin) t(top) 1~5 사이즈가 미리 정의됨(부트스트랩에서) -->
  <div class="container mt-3">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
  <div class="container-fluid">
    <a class="navbar-brand" href="#">SimpleDMS</a>
    <!-- <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button> -->
    <div class="collapse navbar-collapse" id="navbarNavDropdown">
      <!-- 대메뉴 #1 -->
      <ul class="navbar-nav ms-5">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Department Info
          </a>
          <!-- 소메뉴 정의 -->
          <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <li><router-link to="/dept" class="dropdown-item">Dept</router-link></li>
            <li><router-link to="/add/dept" class="dropdown-item">Add</router-link></li>
          </ul>
        </li>
      </ul>
      <!-- 대메뉴 #2 -->
      <ul class="navbar-nav ms-5">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Employee Info
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <li><router-link to="/emp" class="dropdown-item">Emp</router-link></li>
            <li><router-link to="/add/emp" class="dropdown-item">Add</router-link></li>
          </ul>
        </li>
      </ul>
      <!-- 대메뉴 #3 -->
      <ul class="navbar-nav ms-5">
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-bs-toggle="dropdown" aria-expanded="false">
              Faq Info
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
            <li><router-link to="/faq" class="dropdown-item">Faq</router-link></li>
            <li><router-link to="/add/faq" class="dropdown-item">Add</router-link></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>
    <router-view/>
  </div>
</template>

<style lang="scss">
</style>