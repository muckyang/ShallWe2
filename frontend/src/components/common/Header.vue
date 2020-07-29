<template>
  <div>
    <!-- Navbar1 -->
    <nav class="navbar navbar-light d-flex justify-content-end navbar1 p-0 my-navbar">
      <ul class="navbar-nav d-flex flex-row">
        <!-- 로그인 X -->
        <li class="nav-item mr-2"><router-link v-if="!isLoggedin" v-bind:to="{name:constants.URL_TYPE.USER.LOGIN}" class="nav-link navbar1-item" data-toggle="modal" data-target="#exampleModal">로그인</router-link></li>
        <li class="nav-item mr-2"><router-link v-if="!isLoggedin" v-bind:to="{name:constants.URL_TYPE.USER.JOIN}" class="nav-link navbar1-item">회원가입</router-link></li>
        <!-- 로그인 O -->
        <li class="nav-item mr-2"><a href="#" v-if="isLoggedin" @click="REMOVE_TOKEN" class="nav-link navbar1-item">로그아웃</a></li>
        <li class="nav-item mr-2"><router-link v-if="isLoggedin" v-bind:to="{name:constants.URL_TYPE.USER.PROFILE}" class="nav-link navbar1-item">마이페이지</router-link></li>
      </ul>
    </nav>
    <!-- Navbar2 -->
    <nav class="navbar navbar-light d-flex justify-content-center my-navbar p-1">
      <a class="navbar-brand mt-3" href="/" style="color: #ee6e9f; font-family: 'Lobster', cursive; font-size: 35px">Shall we?</a>
    </nav>
    <!-- Navbar3 -->
    <nav class="navbar navbar-expand-lg navbar-light">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item"><router-link to="/article" class="nav-link">ArticleList</router-link></li>
          <li class="nav-item"><router-link to="#" class="nav-link">CommunityList</router-link></li>
        </ul>
        <!-- Search -->
        <div class="dropdown">
          <button class="btn btn btn-light dropdown-toggle mr-1" type="button" id="dropdownMenuButton" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
            선택
          </button>
          <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
            <a class="dropdown-item" href="#">제목</a>
            <a class="dropdown-item" href="#">작성자</a>
            <a class="dropdown-item" href="#">태그</a>
          </div>
        </div>
        <form class="form-inline my-2 my-lg-0">
          <input class="form-control mr-sm-2" 
          type="search" placeholder="검색" 
          aria-label="Search">

          <button class="btn btn-outline-secondary my-2 my-sm-0" 
          type="submit"><i class="fas fa-search"></i></button>
        </form>
      </div>
    </nav>
  </div>
</template>  
<script>
                    
import constants from "../../lib/constants";
import {mapState, mapMutations} from 'vuex'

export default {
  name: "Header",
  components: {},
  props: ["isHeader"],
  data: function() {
    return {
      constants,
      keyword: "",
    }
  },
  methods: {
    ...mapMutations(['REMOVE_TOKEN','loginCheck']),
  },
  computed:{
    ...mapState(['isLoggedin'])
  },
  created:function(){
    this.loginCheck()
  },
};
</script>
<style>
</style>