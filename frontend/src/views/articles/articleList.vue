<template>
  <div class="mt-5">
    <div class="category">
      <ul class="nav nav-tabs" id="myTab" role="tablist">
        <li class="nav-item" >
          <div class="nav-link" id="One-tab" 
          data-toggle="tab" role="tab" 
          aria-controls="home" aria-selected="true"
          @click="changeCategory(1)">배달</div>
        </li>
        <li class="nav-item">
          <div class="nav-link" id="Two-tab" 
          data-toggle="tab" role="tab" 
          aria-controls="profile" aria-selected="false"
          @click="changeCategory(2)">주문</div>
        </li>
        <li class="nav-item">
          <div class="nav-link" id="Three-tab" 
          data-toggle="tab" role="tab" 
          aria-controls="contact" aria-selected="false"
          @click="changeCategory(3)">공동구매</div>
        </li>
      </ul>
      <!-- <li class="nav-item"><div class="nav-link">배달</div></li>
      <li class="nav-item"><div to="/article" class="nav-link">공동구매</div></li>
      <li class="nav-item"><div to="/article" class="nav-link">공동주문</div></li> -->

    </div>
    <div v-if="categoryNum === 1">
      <p>배달 글</p>
    </div>
    <div v-if="categoryNum === 2">
      <p>주문 글</p>
    </div>
    <div v-if="categoryNum === 3">
      <p>공동구매글</p>
    </div>
    <router-link class="btn btn-primary" :to="{name:'articleCreate'}">글쓰기</router-link>
    <div class="container">
      <div class="row">
        <div class="card col-3" style="width: 18rem;" v-for="article in articles" :key="article.pid">
          <img src="https://source.unsplash.com/random" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title">{{ article.title }}</h5>
            <p class="card-text">{{ article.description }}</p>
            <router-link class="btn btn-primary" :to="{name:'articleDetail',params:{ID:`${article.pid}`}}">보러가기</router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
const BACK_URL = "http://127.0.0.1:8080"
import {mapState,mapActions} from 'vuex'

export default {
  name: 'articleList',
  data(){
    return {
      categoryNum: 0,
    }
  },
  methods: {
    ...mapActions(['getArticles']),
    changeCategory(num){
      this.categoryNum = num
      console.log('바뀐 카테고리 넘버',this.categoryNum);
    }
  },
  computed:{
    ...mapState(['articles'])
  },
  created () {
    this.getArticles({temp:1,categoryId:this.categoryNum})
  },
}
</script>

<style>
.category{
  display: flex;
}
.nav-item{
  text-decoration: none;
}
</style>