<template>
  <div>
    <hr>
    <input type="text" v-model="comment_content.content"> 
    <button type="button" class="btn btn-dark" @click="createComment">댓글쓰기</button>
    <commentListItem v-for="comment in comments" :key="comment.no" :comment="comment" @re-render="getComments" :user="comment.writer"/>
  </div>
</template>

<script>
const BACK_URL = "http://127.0.0.1:8080"
import axios from "axios"
import commentListItem from '@/components/comments/commentListItem'

export default {
  name:'commentList',
  components:{
    commentListItem,
  },
  data(){
    return{
      comments:[],
      commentData:{
        content:'',
        writer: '',
        articleno: '',
      },
    }
  },
  methods: {
      createComment(){
        this.comment_content.articleno=this.$route.params.ID
        const Authorization = `${this.$cookies.get('auth-token')}`

        axios.post( `${BACK_URL}/comment/create` , [this.commentData,Authorization])
          .then(()=>{
            this.commentData.content=''
            console.log('댓글 보내기 완료')
          })
          .catch((err)=>{
            console.error(err)
          })
      },
      getComments()
      { 
        axios.get(BACK_URL+'/comment/read/'+this.$route.params.ID)
          .then((response)=>{
            this.comments = response.data.commentList
          })
          .catch((err)=>{
            console.error(err)
          })       
      },
  },
  created(){
    this.getComments()
  },
}
</script>

<style>

</style>