<template>
  <div>
    <div class="doFlex">
      <img class="MyImage" src="https://source.unsplash.com/random" alt="...">
      <div class="articleInfo">
        <div class="title"><p>{{ article.title }}</p></div>
        <div><p>가격 들어갈 자리</p></div>
        <!-- movie_title을 DB에 맞게 바꾸어 주면 된다. -->
        <div class="writer"><p>{{ article.writer }}</p></div>
        <div class="description"><p>{{ article.description }}</p></div>
        <div class="update"><router-link :to="{name:'articleUpdate', params: {ID: `${article.pid}`}}">수정</router-link></div>
      </div>
      <!-- <p>Updated at {{ udate }}/{{ utime }}</p> -->    
    </div> 
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
.MyImage{
  width: 35%;
  height: 300px;
}
.doFlex{
  display: flex;
  padding: 10px 30px 10px 30px;
}
.articleInfo{
  margin-left: 15px;
  text-align: left;
}
.update{
  justify-content: bottom;
}
.title{
  font-size: xx-large;
  font-weight: 600;

}

</style>