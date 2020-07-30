<template>
  <div>
    <!-- Navbar1 -->
    <nav class="navbar navbar-light d-flex justify-content-end navbar1 p-0 my-navbar">
      <ul class="navbar-nav d-flex flex-row">
        <!-- 로그인 X -->
        <li class="nav-item mr-1"><router-link v-if="!isLoggedin" 
        v-bind:to="{name:constants.URL_TYPE.USER.LOGIN}" class="nav-link navbar1-item" 
        data-toggle="modal" data-target="#exampleModal">로그인</router-link></li>

        <div class="wall">|</div>

        <li class="nav-item mr-2"><router-link v-if="!isLoggedin"
        v-bind:to="{name:constants.URL_TYPE.USER.JOIN}" class="nav-link navbar1-item">
        회원가입</router-link></li>
        <!-- 로그인 O -->
        <li class="nav-item mr-1"><a href="#" v-if="isLoggedin" @click="REMOVE_TOKEN" class="nav-link navbar1-item">로그아웃 |</a></li>
        <li class="nav-item mr-2"><router-link v-if="isLoggedin" v-bind:to="{name:constants.URL_TYPE.USER.PROFILE}" class="nav-link navbar1-item">마이페이지</router-link></li>
      </ul>
    </nav>
    <!-- Navbar2 -->
    <nav class="navbar navbar-light d-flex justify-content-center my-navbar p-1">
      <a class="navbar-brand mt-3" href="/" style="color: #ee6e9f; font-family: 'Lobster', cursive; font-size: 35px">Shall we?</a>
    </nav>
    <!-- Navbar3 -->
    <nav class="navbar navbar-expand-lg navbar-light p-0">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarSupportedContent">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item pb-0"><router-link to="/article" class="nav-link mainMenu pb-0" id="article">게시글</router-link></li>
          <li class="nav-item pb-0"><router-link to="#" class="nav-link mainMenu pb-0" id="community">자유 게시판</router-link></li>
        </ul>
        <!-- Search -->
        <div class="form-inline my-2 my-lg-0 mb-1">
          <div class="dropdown mr-1">
            <button class="downBtn btn btn-secondary" 
            type="button" id="dropdownMenuButton" 
            data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">{{item}}<i class="down fas fa-sort-down"></i> 
            </button>
            <div class="dropdown-menu" aria-labelledby="dropdownMenuButton">
              <a class="dropdown-item" href="#" @click="selectTitle">제목</a>
              <a class="dropdown-item" href="#" @click="selectWriter">작성자</a>
              <a class="dropdown-item" href="#" @click="selectTag">태그</a>
            
          </div>
        </div>
          <input class="searchInput" type="search" 
          style="font-family: FontAwesome;" 
          placeholder="Search" v-model="searchData.word" 
          @keypress.enter="search" 
          >

          <button class="searchBtn 
          my-2 my-sm-0" 
          type="submit" @click="search">
          <i class="fas fa-search"></i></button>
        </div>
      </div>
    </nav>
  </div>
</template>

<script>
                    
import constants from "../../lib/constants";
import {mapState, mapMutations} from 'vuex'
import router from '@/router'
import axios from "axios"
const BACK_URL = "http://127.0.0.1:8080"

export default {
  name: "Header",
  components: {},
  props: ["isHeader"],
  data: function() {
    return {
      constants,
      // icon:'\uf002',
      searchData:{
        subject:'',
        word:'',
      },
      item:'',
    }
  },
  methods: {
    ...mapMutations(['REMOVE_TOKEN','loginCheck']),
    selectTitle(){
      this.searchData.subject = 'title'
      this.item = '제목'
    },
    selectWriter(){
      this.searchData.subject = 'writer'
      this.item = '작성자'
    },
    selectTag(){
      this.searchData.subject = 'tag'
      this.item = '태그'
    },
    search(){
      if(this.searchData.word&&this.searchData.subject){
        console.log(this.searchData.word)
        console.log(this.searchData.subject)
        
        console.log('데이터', this.searchData)
        const config = {
          headers: {
            Authorization : `${this.$cookies.get('auth-token')}`
          }
        }
        axios.post(`${BACK_URL}/post/read/1/${config.headers.Authorization}`, this.searchData)
          .then((res) =>{
            console.log(res.data)
            this.$router.push('/search')
          })
        router.push('/search')
        this.keyword=''
      }else if(this.searchData.word){
        alert('카테고리를 선택하세요!')
      }else if(this.searchData.subject){
        alert('검색어를 입력하세요!')
      }
    }
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
@font-face { font-family: 'Recipekorea'; 
src: url('https://cdn.jsdelivr.net/gh/projectnoonnu/noonfonts_2001@1.1/Recipekorea.woff') format('woff'); 
font-weight: normal; 
font-style: normal;
 }
 .mainMenu{
   font-family: Recipekorea;
   font-weight: normal;
   font-style: normal;
 }
 #article{
   margin-left:-5px;
 }
 #community{
   margin-left: 10px;
 }
.navbar1 {
  height: 30px; 
  
}
.navbar1-item {
  font-size: 12px;
}
.wall{
  padding: .5rem 0.2rem;
  font-size: 12px;
  margin-left: -4px;
  margin-right: 1px;
}
.down{
  padding-bottom:3px;
  margin-bottom: 5px;
}
.downBtn{
  height: 35px;
  vertical-align: middle;

}
.searchInput{
  height: 35px;
  width: 170px;
  border-style: solid;
  border-width: 1.5px;
  border-radius: 0;
  border-color: #453d40;
}
.searchBtn{
  height: 35px;
  border-style: solid;
  border-radius: 0;
  border-color: #453d40;
  background-color: #453d40;
  color: #faf9fa;
}

/* .dropdown-toggle {
  background-color: white;
  border: white;
} */
</style>