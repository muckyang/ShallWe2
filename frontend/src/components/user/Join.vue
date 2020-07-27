<template>
    <div class="user" id="join"> 
        <div class="wrapC table">
            <div class="middle">
                <h1>회원가입</h1>
                <div class="form-wrap">

                    <div class="input-wrap ">
                        <div class="p-2">
                            <i class="fas fa-user"></i>
                        </div>
                        <input v-model="signUpData.signUpDataForSend.nickname"
                            id="nickname"
                            placeholder="닉네임을 입력해주세요" type="text"/>
                    </div>

                    <div class="input-wrap">
                        <div class="p-2">
                            <i class="fas fa-envelope"></i>
                        </div>
                        <input v-model="signUpData.signUpDataForSend.email" 
                            id="email"
                            placeholder="이메일을 입력해주세요"
                            type="text"/>
                    </div >

                    <div class="input-wrap id-warp">
                        <div class="p-2">
                            <i class="fas fa-user"></i>
                        </div>
                        <input v-model="signUpData.signUpDataForSend.id" 
                            id="id"
                            placeholder="id를 입력해주세요"
                            type="text"/>
                    </div>

                    <div class="input-wrap name-warp">
                        <div class="p-2">
                            <i class="fas fa-user"></i>
                        </div>
                        <input v-model="signUpData.signUpDataForSend.name" 
                            id="name"
                            placeholder="이름을 입력해주세요"
                            type="text"/>
                    </div>

                    <div class="input-wrap address-warp">
                        <div class="p-2">
                            <i class="fas fa-home"></i>
                        </div>
                        <input v-model="signUpData.signUpDataForSend.address" 
                            id="address"
                            style="font-family:FontAwesome;"
                            placeholder="주소를 입력해주세요"
                            type="text"/>
                    </div>

                    <div class="input-wrap birthday-warp">
                        <div class="p-2">
                            <i class="fas fa-birthday-cake"></i>
                        </div>
                        <input v-model="signUpData.signUpDataForSend.birthday" 
                            id="birthday"
                            placeholder="생일을 입력해주세요"
                            type="text"/>
                    </div>

                    <div class="input-wrap password-wrap">
                        <div class="p-2">
                            <i class="fas fa-key"></i>
                        </div>
                        <input v-model="signUpData.signUpDataForSend.password"
                            id="password" 
                            type="password"
                            placeholder="비밀번호를 입력해주세요"/>
                        <span :class="{active : signUpData.passwordType==='text'}">
                            <i class="fas fa-eye"></i>
                        </span>
                    </div>

                    <div class="input-wrap password-wrap">
                        <div class="p-2">
                            <i class="fas fa-key"></i>
                        </div>
                        <input v-model="signUpData.password2" 
                            id="password-confirm"
                            type="password"
                            placeholder="비밀번호를 한번 더 입력해주세요"/>
                        <span :class="{active : signUpData.passwordConfirmType==='text'}">
                            <i class="fas fa-eye"></i> 
                        </span>
                    </div>
                </div>
                

                
                <!-- <label>
                    <input v-model="signUpData.isTerm" type="checkbox" id="term"/>
                    <span>약관에 동의합니다</span>
                </label>
                <span class="go-term">약관 보기</span><br><br>-->


                <button @click="signUp(signUpData)" class="btn">
                    <span>
                        작성완료
                    </span>
                </button>
            </div>
        </div> 
    </div>
</template>


<script>
import axios from "axios"
import {mapActions} from 'vuex'
const BACK_URL = "http://127.0.0.1:8080"

    export default {
        name: "Signup",
        components: {
        },
        data: () => {
            return {
                signUpData:{
                    signUpDataForSend:{
                        address:'',
                        birthday:'',
                        email: '',
                        id: '',
                        name:'', 
                        nickname: '',
                        password:''
                    },
                    password2:''       
                },
                // isTerm: false,
            }
        },
        methods: {
            ...mapActions(["signUp"])
        },
    }

</script>

<style>
.input-wrap{
    display: flex;
    justify-content: flex-start;
}
</style>