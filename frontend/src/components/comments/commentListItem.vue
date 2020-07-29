<template>
  <div>
    <div class="d-flex bd-highlight mb-3">
        <div class="ml-auto bd-highlight">
        <span v-if="checkAuth">
            <button @click="deleteComment">삭제</button>|
            <button @click="showInput" v-if="!flag">수정</button>
        </span>
        </div>
    </div>
    <div class="coutline">{{ducomment.content}}</div>
    
    <!-- 댓글 수정 -->
    <input v-if="flag" type="text" v-model="ducomment.content">
    <button v-if="flag" @click="updateComment">댓글 수정</button>
  </div>
</template>

<script>
const BACK_URL = "http://127.0.0.1:8080"                
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
        }
    },
    computed:{
        checkAuth(){
            return this.comment.writer === this.$cookies.get('username')
        },
    },
    methods:{
        deleteComment(){
            const Authorization = `${this.$cookies.get('auth-token')}`
            axios.post(BACK_URL+`/comment/delete/${this.ducomment.no}`,Authorization)
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
            const Authorization = `${this.$cookies.get('auth-token')}`
            axios.post(BACK_URL+'/comment/update/',[this.comment,Authorization])
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
        console.log(this.checkAuth)
    }
}
</script>

<style>

</style>