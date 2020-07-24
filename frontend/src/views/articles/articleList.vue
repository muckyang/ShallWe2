<template>
  <div class="mt-5">
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
import axios from "axios"

export default {
  name: 'articleList',
  data: function () {
    return {
      articles: [

      ],
      temptrue: true
    }
  },
  methods: {
    initArticles () {
       const config = {
          headers: {
            Authorization: `${this.$cookies.get('auth-token')}`
          }
       }
       if (headers.Authorization===null) {
        axios.get(`${BACK_URL}/post/read/${this.temptrue}/${config.headers.Authorization}`)
          .then((reaponse) => {
            this.articles = reaponse.data.postList
          })
          .catch((err) => {
            console.error(err)
          })         
       }else{
         axios.get(`${BACK_URL}/post/read/${this.temptrue}`)
        .then((reaponse) => {
          this.articles = reaponse.data.postList
        })
        .catch((err) => {
          console.error(err)
        })
       }
    },
  },
    created () {
      this.initArticles()
    },
  }
</script>

<style>

</style>