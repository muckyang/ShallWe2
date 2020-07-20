<template>
  <div>
      <h1>Update</h1>
      <div class="aform">
        <form>
          <div class="form-group">
            <label for="title">Title</label>
            <input type="text" class="form-control" id="title" v-model="article.title">
          </div>
          <div class="form-group">
            <label for="content">Content</label>
            <textarea class="form-control" id="content" rows="10" v-model="article.content"></textarea>
          </div>
          <button type="submit" class="btn btn-info mr-1" @click="updateItem">수정</button>
          <button type="submit" class="btn btn-danger" @click="deleteItem">삭제</button>
        </form>
      </div>
  </div>
</template>
<script>
                    
                    
import axios from "axios"
const BACK_URL = "http://127.0.0.1:8000"
export default {
    name:'articleUpdate',
    data(){
      return{
        article:{
          title:null,
          content:null,
        },
        user:null,
        currentuser:null,
      }
    },
    methods: {
        getuser(){
          const axiosConfig = {
            headers:{
              Authorization : `Token ${this.$cookies.get('auth-token')}`
            },
          }
          axios.get(`${BACK_URL}/rest-auth/user/`,axiosConfig)
            .then((reaponse)=>{
              this.currentuser = reaponse.data.username
            if (this.user !== this.currentuser){
                this.$alert("잘 못 된 접근입니다.");
                this.$router.push({name:'articleView'})
            }  
            })
            .catch((err)=>{
              console.error(err)
            })
        },
        getItem()
        { 
          axios.get(BACK_URL+'/articles/'+this.$route.params.ID)
          .then((reaponse)=>{
            this.article.title = reaponse.data.title
            this.article.content = reaponse.data.content
            this.user = reaponse.data.user.username
            this.getuser()
          })
          .catch((err)=>{
            console.error(err)
          })
        },
        updateItem()
        {
          event.preventDefault()
          axios.post(BACK_URL+'/articles/'+this.$route.params.ID+'/update/',this.article)
          .then((response) => {
            this.article = response.data
            this.$router.push({name:'articleDetail',params:this.$route.params.ID})
            })
          .catch((err)=>{
            console.error(err)
          })
        },
        deleteItem()
        {
          event.preventDefault()
          axios.delete(BACK_URL+'/articles/'+this.$route.params.ID+'/delete/')
          this.$router.push({name:'articleView'})
        },    
    },
    created: function(){
        this.getItem()
    },
}
</script>

<style>

</style>