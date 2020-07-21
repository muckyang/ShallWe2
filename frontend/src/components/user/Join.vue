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
                        
                        <input v-model="signupData.nickname"
                            id="nickname"
                            placeholder="닉네임을 입력해주세요" type="text"/>
                    </div>

                    <div class="input-wrap">
                        <div class="p-2">
                            <i class="fas fa-envelope"></i>
                        </div>
                        
                        <input v-model="signupData.email" 
                            id="email"
                            placeholder="이메일을 입력해주세요"
                            type="text"/>
                    </div >

                    <div class="input-wrap id-warp">
                        <div class="p-2">
                            <!-- <i class="fas fa-id-badge"></i> -->
                            <i class="fas fa-user"></i>
                        </div>
                        <input v-model="signupData.id" 
                            id="id"
                            placeholder="id를 입력해주세요"
                            type="text"/>
                    </div>

                    <div class="input-wrap name-warp">
                        <div class="p-2">
                            <!-- <i class="fas fa-id-badge"></i> -->
                            <i class="fas fa-user"></i>
                        </div>
                        <input v-model="signupData.name" 
                            id="name"
                            placeholder="이름을 입력해주세요"
                            type="text"/>
                    </div>

                    <div class="input-wrap address-warp">
                        <div class="p-2">
                            <i class="fas fa-home"></i>
                        </div>
                        <input v-model="signupData.address" 
                            id="address"
                            style="font-family:FontAwesome;"
                            placeholder="주소를 입력해주세요"
                            type="text"/>
                    </div>

                    <div class="input-wrap birthday-warp">
                        <div class="p-2">
                            <i class="fas fa-birthday-cake"></i>
                        </div>
                        <input v-model="signupData.birthday" 
                            id="birthday"
                            placeholder="생일을 입력해주세요"
                            type="text"/>
                    </div>

                    <div class="input-wrap password-wrap">
                        <div class="p-2">
                            <i class="fas fa-key"></i>
                        </div>
                        <input v-model="signupData.password"
                            id="password" 
                            type="password"
                            placeholder="비밀번호를 입력해주세요"/>
                        <span :class="{active : signupData.passwordType==='text'}">
                                <i class="fas fa-eye"></i>
                            </span>
                    </div>

                    <div class="input-wrap password-wrap">
                        <div class="p-2">
                            <i class="fas fa-key"></i>
                        </div>
                        <input v-model="password2" 
                            id="password-confirm"
                            type="password"
                            placeholder="비밀번호를 한번 더 입력해주세요"/>
                        <span :class="{active : signupData.passwordConfirmType==='text'}">
                                <i class="fas fa-eye"></i> 
                            </span>
                    </div>
                </div>

                <label>
                    <input v-model="signupData.isTerm" type="checkbox" id="term"/>
                    <span>약관에 동의합니다</span>
                </label>

                <span class="go-term">약관 보기</span><br><br>

                <button v-on:click="signUp" class="btn">
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
const BACK_URL = "http://127.0.0.1:8080" //로컬 주소 넣으면 되나

    export default {
        name: "Signup",
        components: {
        },
        data: () => {
            return {
                signupData:{
                    address:'',
                    birthday:'',
                    email: '',
                    id: '',
                    name:'', 
                    nickname: '',
                    password:''       
                },
                isTerm: false,
                password2:''
            }
        },
        methods: {
            signUp: function() {
                if (this.signupData.password===this.password2){
                    axios.post(`${BACK_URL}/account/signup`, this.signupData)
                    .then((res) => {
                        this.$cookies.set('auth-token', res.data.key)
                        console.log(res.data.key)
                        alert("회원가입이 완료되었습니다.");
                    })
                    .catch((err) => {
                      console.log(err)
                        alert("입력 값을 다시 확인해주세요");
                    });
                }else{
                    alert("비밀번호를 다시 설정해주세요")
                }

            },
            // SubmitUser:function(signupData){
            //     console.log('회원가입 요청')
                // this.$emit("submit-signupData", this.signupData)
            },
    }

</script>

<style>
.input-wrap{
    display: flex;
    justify-content: flex-start;
}

</style>