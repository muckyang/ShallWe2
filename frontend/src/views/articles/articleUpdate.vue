<template>
  <div>
    <h1>articleUpdate</h1>
    <p>Title</p>
    <input type="text" id="title" v-model="article.title">
    <p>Writer</p>
    <!-- movie_title을 DB에 맞게 바꾸어 주면 된다. -->
    <input type="text" id="Movie" v-model="article.writer">
    <p>Content</p>
    <textarea id="content" rows="10" v-model="article.description"></textarea>
    <button type="submit" @click="updateItem">수정</button>
    <button type="submit" @click="deleteItem">삭제</button>
    <button @click="getItem">확인</button>
  </div>
</template>

<script>
const BACK_URL = "http://127.0.0.1:8080"
import axios from "axios"

export default {
    name:'articleUpdate',
    data () {
      return {
        article: {
          pid: this.$route.params.ID,
          title: null,
          description: null,
          writer: null,
        },
      }
    },
    methods: {
        getItem () {
          axios.get(BACK_URL+'/post/detail/'+this.$route.params.ID)
          .then((response)=>{
            this.article.title = response.data.title
            this.article.description = response.data.description
            this.article.writer = response.data.writer
          })
          .catch((err)=>{
            console.error(err)
          })
        },
        updateItem()
        {
          event.preventDefault()
          axios.post(BACK_URL+'/post/update/', this.article)
          .then((response) => {
            this.$router.push({name:'articleDetail',params:this.$route.params.ID})
            })
          .catch((err)=>{
            console.error(err)
          })
        },
        deleteItem()
        {
          event.preventDefault()
          axios.get(BACK_URL+'/post/delete/'+this.$route.params.ID)
          this.$router.push({name:'articleList'})
        },    
    },
    created: function(){
        this.getItem()
    },
}
</script>

<style>

</style>