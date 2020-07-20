<template>
  <div class="outline">
    <h1>{{article.title}}</h1>
    <hr>
    <hr class="munderline">
    <p class="coutline">{{article.content}}</p>
    <div class="d-flex flex-row-reverse bd-highlight mb-3">
      <div class="p-2 bd-highlight">
        <div><i>by {{article.user.username}}</i></div>
        <div><i>Created at {{cdate}}/{{ctime}}</i></div>
        <div><i>Ureated at {{udate}}/{{utime}}</i></div>
        <div>
          <hr>
          <router-link v-if="flag" :to="{name:'articleUpdate',params:{ID:`${article.id}`}}" ><button class="btn btn-info">게시글 수정</button></router-link> 
        </div>
      </div>
    </div>
    <commentList/>
  </div>
</template>
<script>
import axios from "axios"

// import commentList from '@/components/comments/commentList'
const BACK_URL = "http://127.0.0.1:8000"

export default {
  name:'articleDetail',
  components:{
    // commentList,
  },
  data(){
    return{
      article:{user:{username:"dsfdfdfdf"}},
      flag : false,
      currentuser : "",
      cdate:'',
      udate:'',
      ctime:'',
      utime:'',
    }
  },
  methods: {
      getcurrentuser(){
        const axiosConfig = {
          headers:{
            Authorization : `Token ${this.$cookies.get('auth-token')}`
          },
        }
        axios.get(`${BACK_URL}/rest-auth/user/`,axiosConfig)
          .then((reaponse)=>{
            this.currentuser = reaponse.data.username
        if (this.article.user.username===this.currentuser){
          this.flag = true
        }  
          })
          .catch((err)=>{
            console.error(err)
          })
      },
      getItem(){
        axios.get(BACK_URL+'/articles/'+this.$route.params.ID)
        .then((response)=>{
          this.article = response.data
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
.outline{
  width:60%;
  margin: auto;
}
.munderline{
  width:70%;
  margin-bottom: 30px;
}
.font{
  font-family: '궁서체';
}
</style>