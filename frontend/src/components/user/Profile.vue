<template>
  <div>
      <h1>Profile</h1>
      <p>{{name}}님 환영합니다</p>
      <button @click="deleteUser">회원 탈퇴 하기</button>
      <router-link v-bind:to="{name:constants.URL_TYPE.USER.EDIT}">회원 정보 수정하기</router-link>
  </div>
</template>

<script>
const BACK_URL = 'http://127.0.0.1:8080'
import constants from '../../lib/constants'
import axios from 'axios'

export default {
    name:'Profile',
    data: function () {
        return {
            constants,
            name:'',
            address:'',
            email:'',
            nickname:'',
            id:'',
            password:'',
            birthday:'',
        }
    },
    methods: {
        getUser:function(){
            var id = localStorage.getItem('id')
            
            axios.get(`${BACK_URL}/info/${id}/`)
            .then((response)=>{
                this.name=response.data.name
                this.address=response.data.address
                this.email=response.data.email
                this.nickname=response.data.nickname
                this.id=response.data.id
                this.password=response.data.password
                this.birthday=response.data.birthday
            })
            .catch((err)=>{
                console.error(err)
            })
        },
        deleteUser: function(){
            var id = localStorage.getItem('id')
             axios.get(`${BACK_URL}/account/delete/${id}`)
            .then((response)=>{
                console.log(response)
                alert("회원 탈퇴가 완료되었습니다.")
            })
            .catch((err)=>{
                console.error(err)
            })
        }
    },
    created: function(){
        this.getUser()
    }
}
</script>

<style>

</style>