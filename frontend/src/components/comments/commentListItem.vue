<template>
  <div>
    <div class="d-flex bd-highlight mb-3">
        <div class="bd-highlight csize coutline">{{ducomment.user.username}} ᆞ {{cdate}}/{{ctime}} ᆞ {{udate}}/{{utime}}</div>
        <div class="ml-auto bd-highlight">
        <span v-if="usersameflag">
            <button @click="deleteComment">삭제</button>|
            <button @click="showInput" v-if="!flag">수정</button>
        </span>
        </div>
    </div>
    <div class="coutline">{{ducomment.content}}</div>
    
    <!-- 댓글 수정 -->
    <input v-if="flag" type="text" v-model="updatedComment.content">
    <button v-if="flag" @click="updateComment">댓글 수정</button>
  </div>
</template>

<script>
const BACK_URL = "http://127.0.0.1:8000"                
import axios from "axios"

export default {
    name:'commentListItem',
    props:{
        comment:Object,
        user:String,      
    },
    data(){
        return{
            watchFlag:false,
            flag:false,
            usersameflag:false,
            ducomment:this.comment,
            updatedComment:{
                content:'',
            },
            currentuser:null,
            cdate:'',
            udate:'',
            ctime:'',
            utime:'',
        }
    },
    methods:{
        getItem()
        { 
          const axiosConfig = {
            headers:{
              Authorization : `Token ${this.$cookies.get('auth-token')}`
            },
          }
          axios.get(`${BACK_URL}/rest-auth/user/`,axiosConfig)
            .then((reaponse)=>{
                this.currentuser = reaponse.data.username
                this.cdate=this.ducomment.created_at.substr(0,10)
                this.udate=this.ducomment.updated_at.substr(0,10)
                this.ctime=this.ducomment.created_at.substr(11,5)
                this.utime=this.ducomment.updated_at.substr(11,5)
                if (this.user===this.currentuser){
                    this.usersameflag = true
                }
            })
            .catch((err)=>{
              console.error(err)
            })
        },
        deleteComment(){
          axios.post(BACK_URL+'/articles/'+`${this.comment.id}`+'/comment-delete/')
            .then(()=>{
                this.watchFlag=true
            })
            .catch((err)=>{
                console.error(err)
            })
      },
      showInput(){
          this.flag=true
      },
      updateComment(){
        console.log(this.updatedComment.content)
        axios.post(BACK_URL+'/articles/'+`${this.comment.id}`+'/comment-update/',this.updatedComment)
            .then((response)=>{
                this.ducomment=response.data
                this.flag=false
            })
            .catch((err)=>{
                console.error(err)
            })
      },
    },
    watch:{
        watchFlag(){
            this.$emit('re-render')
        }
    },
    created(){
        this.getItem()
    },
}
</script>

<style>

</style>