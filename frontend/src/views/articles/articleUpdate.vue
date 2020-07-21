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
    data(){
      return{
        article:{
          title:null,
          description:null,
          // movie_title을 DB에 맞게 바꾸어 주면 된다.
          pid:this.$route.params.ID,
          writer:null,
        },
        user:null,
        currentuser:null,
      }
    },
    methods: {
        // getuser(){
        //   const axiosConfig = {
        //     headers:{
        //       Authorization : `Token ${this.$cookies.get('auth-token')}`
        //     },
        //   }
        //   axios.get(`${BACK_URL}/rest-auth/user/`,axiosConfig)
        //     .then((reaponse)=>{
        //       this.currentuser = reaponse.data.username
        //     if (this.user !== this.currentuser){
        //         this.$alert("잘 못 된 접근입니다.");
        //         this.$router.push({name:'articleView'})
        //     }  
        //     })
        //     .catch((err)=>{
        //       console.error(err)
        //     })
        // },
        getItem()
        {
          axios.get(BACK_URL+'/post/detail/'+this.$route.params.ID)
          .then((response)=>{
            this.article.title = response.data.title
            this.article.description = response.data.description
            // movie_title을 DB에 맞게 바꾸어 주면 된다.
            this.article.writer = response.data.writer
            // this.user = response.data.user.username
            // this.getuser()
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