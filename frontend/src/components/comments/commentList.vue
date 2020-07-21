<template>
  <div>
    <hr>
    <input type="text" v-model="comment_content.content"> 
    <button type="button" class="btn btn-dark" @click="createComment">댓글쓰기</button>
    <commentListItem v-for="comment in comments" :key="comment.id" :comment="comment" @re-render="getComments" :user="comment.user.username"/>
  </div>
</template>

<script>
const BACK_URL = "http://127.0.0.1:8000"
import axios from "axios"
import commentListItem from '@/components/comments/commentListItem'

export default {
  name:'commentList',
  components:{
    commentListItem,
  },
  data(){
    return{
      flag:false,
      comments:'',
      comment_content:{
        content:''
      },
    }
  },
  methods: {
      createComment(){
        const config = {
          headers:{
            Authorization : `Token ${this.$cookies.get('auth-token')}`
          },
        }
        axios.post(BACK_URL+'/articles/'+this.$route.params.ID+'/comment-create/',this.comment_content,config)
          .then(()=>{
            this.flag=true
            this.comment_content.content=''
          })
          .catch((err)=>{
            console.error(err)
          })
      },
      getComments()
      {  
        axios.get(BACK_URL+'/articles/'+this.$route.params.ID+'/comment-detail/')
            .then((response)=>{
              this.comments=response.data
              this.flag=false
            })
            .catch((err)=>{
              console.error(err)
            })       
      },
  },
  watch:{
    flag(){
      this.getComments()
    }
  },
  created(){
    this.getComments()
  },
}
</script>

<style>

</style>