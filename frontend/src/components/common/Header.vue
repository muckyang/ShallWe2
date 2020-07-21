<template>
  <div>
    <!-- Navbar1 -->
    <nav class="navbar navbar-light bg-light d-flex justify-content-center">
      <a class="navbar-brand mt-3" href="/">로고 넣을 공간</a>
      <!-- <img src="../../assets/img/logo.png" width="10 height="50" alt="" loading="lazy"> -->
    </nav>
    <!-- Navbar2 -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item dropdown">
            <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
              전체 카테고리
            </a>
            <!-- Dropdown -->
            <div class="dropdown-menu" aria-labelledby="navbarDropdown">
              <a class="dropdown-item" href="#">항목1</a>
              <a class="dropdown-item" href="#">항목2</a>
              <div class="dropdown-divider"></div>
              <a class="dropdown-item" href="#">항목3</a>
              <a class="dropdown-item" href="#">항목4</a>
            </div>
          </li>
          <!-- Links -->
          <li class="nav-item"><router-link to="/article" class="nav-link">글보기</router-link></li>
          <li class="nav-item"><router-link to="/create" class="nav-link">글쓰기</router-link></li>
          <li class="nav-item"><router-link v-if="!isLoggedIn" v-bind:to="{name:constants.URL_TYPE.USER.LOGIN}" class="nav-link" data-toggle="modal" data-target="#exampleModal">로그인</router-link></li>
          <li class="nav-item"><a class="nav-link" v-if="isLoggedIn" @click="logout">로그아웃</a></li>
          <li class="nav-item"><router-link v-if="!isLoggedIn" v-bind:to="{name:constants.URL_TYPE.USER.JOIN}" class="nav-link">회원가입</router-link></li>
        </ul>
        <!-- Search -->
        <form class="form-inline my-2 my-lg-0">
          <input class="form-control mr-sm-2" type="search" placeholder="검색" aria-label="Search">
          <button class="btn btn-outline-secondary my-2 my-sm-0" type="submit">검색</button>
        </form>
      </div>
    </nav>
  </div>
</template>   

<script>
import constants from "../../lib/constants";

export default {
  name: "Header",
  components: {},
  props: ["isHeader","isLoggedIn"],
  computed: {},
  watch: {},
  methods: {
    logout: function() {
      localStorage.removeItem("id");
      var aa = localStorage.getItem("id");
      console.log(aa);
    }
  },
  data: function() {
    return {
      constants,
      keyword: ""
    }
  },
  created:function() {
    console.log(this.isLoggedIn)
  }
};
</script>

<style>

</style>
