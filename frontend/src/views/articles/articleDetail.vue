<template>
  <div>
    <h1>articleDetail</h1>
    <p>Title: {{ article.title }}</p>
    <!-- movie_title을 DB에 맞게 바꾸어 주면 된다. -->
    <p>Writer: {{ article.writer }}</p>
    <p>description: {{ article.description }}</p>
    <!-- <p>Updated at {{ udate }}/{{ utime }}</p> -->
    <router-link :to="{name:'articleUpdate', params: {ID: `${article.pid}`}}">수정</router-link> 
    <commentList/>
  </div>
</template>

<script>
  const BACK_URL = "http://127.0.0.1:8080"
  import axios from "axios"
  import commentList from '@/components/comments/commentList'
  
  export default {
    name:'articleDetail',
    components:{
      commentList,
    },
    data () {
      return {
        article: {user: {username: ''},pid:null,},
      }
    },
    methods: {
        // getcurrentuser(){
        //   const axiosConfig = {
        //     headers:{
        //       Authorization : `Token ${this.$cookies.get('auth-token')}`
        //     },
        //   }
        //   axios.get(`${BACK_URL}/post/user/`,axiosConfig)
        //     .then((reaponse)=>{
        //       this.currentuser = reaponse.data.username
        //   if (this.article.user.username===this.currentuser){
        //     this.flag = true
        //   }  
        //     })
        //     .catch((err)=>{
        //       console.error(err)
        //     })
        // },
        getItem(){
          // axios.get(BACK_URL+'/post/detail/'+this.$route.params.ID)
          axios.get(BACK_URL+'/post/detail/'+this.$route.params.ID)
          .then((response)=>{
            this.article = response.data
            this.article.pid=response.data.pid
            console.log(this.article.created_at,this.article.updated_at)
            this.cdate=this.article.created_at.substr(0,10)
            this.udate=this.article.updated_at.substr(0,10)
            this.ctime=this.article.created_at.substr(11,8)
            this.utime=this.article.updated_at.substr(11,8)
            console.log(this.cdate,this.udate)
          if (this.article.user.username===this.currentuser){
            this.flag = true
          }
          })
          .catch((err)=>{
            console.error(err)
          })
        },
    },
    created: function(){
        this.getcurrentuser()
    },
    mounted: function(){
      this.getItem()
    },
  }
</script>

<style>

</style>