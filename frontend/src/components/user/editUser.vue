<template>
  <div class="user" id="join"> 
        <div class="wrapC table">
            <div class="middle">
                <h1>회원 정보 수정</h1>
                <div class="form-wrap">

                    <div class="input-wrap">
                        <input v-model="nickname"
                            id="nickname"
                            placeholder="닉네임을 입력해주세요" type="text"/>
                    </div>

                    <div class="input-wrap">
                        <input v-model="email" 
                            id="email"
                            placeholder="이메일을 입력해주세요"
                            type="text"/>
                    </div >

                    <div class="input-wrap name-warp">
                        <input v-model="name" 
                            id="name"
                            placeholder="이름을 입력해주세요"
                            type="text"/>
                    </div>

                    <div class="input-wrap address-warp">
                        <input v-model="address" 
                            id="address"
                            placeholder="주소를 입력해주세요"
                            type="text"/>
                    </div>

                    <div class="input-wrap birthday-warp">
                        <input v-model="birthday" 
                            id="birthday"
                            placeholder="생일을 입력해주세요"
                            type="text"/>
                    </div>

                    <div class="input-wrap password-wrap">
                        <input v-model="password"
                            id="password" 
                            type="password"
                            placeholder="비밀번호를 입력해주세요"/>
                        <span :class="{active : passwordType==='text'}">
                                <i class="fas fa-eye"></i>
                            </span>
                    </div>

                    <div class="input-wrap password-wrap">
                        <input v-model="password2" 
                            id="password-confirm"
                            type="password"
                            placeholder="비밀번호를 한번 더 입력해주세요"/>
                        <span :class="{active : passwordConfirmType==='text'}">
                                <i class="fas fa-eye"></i> 
                            </span>
                    </div>
                </div>
                <button v-on:click="editUser" class="btn">
                    <span>
                        작성완료
                    </span>
                </button>
            </div>
        </div> 
    </div>
</template>

<script>
const BACK_URL = 'http://127.0.0.1:8080'
import axios from 'axios'

export default {
    name:"editUser",
    data: function () {
        return {
            name:'',
            address:'',
            email:'',
            nickname:'',
            id:'',
            password:'',
            birthday:'',
            password2:'',
            passwordType:"password",
            passwordConfirmType:"password",
        }
    },
    methods:{
        editUser:function(){
            const password2 = this.password2
            const editData={
              name: this.name,
              address:this.address,
              email:this.email,
              nickname:this.nickname,
              id:this.id,
              password:this.password,
              birthday:this.birthday,
            } 
            if(editData.password===password2){ 
                const config = {
                    headers: {
                        Authorization: `${this.$cookies.get('auth-token')}`
                    }
                }
                axios.post(`${BACK_URL}/account/update/${config.headers.Authorization}`)
                .then((response)=>{
                    console.log(response)
                    alert("수정이 완료되었습니다.")
                })
                .catch((err)=>{
                    console.error(err)
                })
            }else{
                alert("비밀번호를 확인해 주세요")
            }
        },
        getUser:function(){
            const config = {
                headers: {
                    Authorization: `${this.$cookies.get('auth-token')}`
                }
            }
          axios.get(`${BACK_URL}/account/update/${config.headers.Authorization}`)
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
        }
    },
    created: function(){
        this.getUser()
    }
}
</script>

<style>

</style>