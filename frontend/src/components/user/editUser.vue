<template>
  <div class="user" id="join"> 
        <div class="wrapC table">
            <div class="middle">
                <h2>개인정보 수정</h2>
                <hr>
                <div class="form-wrap">                                    

                    <div class="input-wrap password-wrap">
                        <div class="p-2">
                            <!-- <i class="fas fa-key"></i> -->
                            <label for="password">비밀번호</label>
                        </div>
                        <input v-model="password"
                            id="password" 
                            type="password"
                            name="password"
                            placeholder="비밀번호를 입력해주세요"/>
                        <span :class="{active : passwordType==='text'}">
                                <i class="fas fa-eye"></i>
                            </span>
                    </div>

                    <div class="input-wrap password-wrap">
                        <div class="p-2">
                            <!-- <i class="fas fa-key"></i> -->
                            <label for="password-confirm">비밀번호 확인</label>
                        </div>
                        <input v-model="password2" 
                            id="password-confirm"
                            name="password-confirm"
                            type="password"
                            placeholder="비밀번호를 한번 더 입력해주세요"/>
                        <span :class="{active : passwordConfirmType==='text'}">
                                <i class="fas fa-eye"></i> 
                            </span>
                    </div>

                    <div class="input-wrap name-warp">
                        <div class="p-2">
                            <!-- <i class="fas fa-user"></i> -->
                            <label for="name">이름</label>
                        </div>
                        <input v-model="name" 
                            id="name"
                            name="name"
                            placeholder="이름을 입력해주세요"
                            type="text"/>
                    </div>

                    <div class="input-wrap">
                        <div class="p-2">
                            <!-- <i class="fas fa-user"></i> -->
                            <label for="nickname">닉네임</label>
                        </div>
                        <input v-model="nickname"
                            id="nickname"
                            name="nickname"
                            placeholder="닉네임을 입력해주세요" 
                            type="text"/>
                    </div>                

                    <div class="input-wrap address-warp">
                        <div class="p-2">
                            <!-- <i class="fas fa-home"></i> -->
                            <label for="address">주소</label>
                        </div>
                        <input v-model="address" 
                            id="address"
                            name="address"
                            placeholder="주소를 입력해주세요"
                            type="text"/>
                    </div>

                    <div class="input-wrap birthday-warp">
                        <div class="p-2">
                            <!-- <i class="fas fa-birthday-cake"></i> -->
                            <label for="birthday">생일</label>
                        </div>
                        <input v-model="birthday" 
                            id="birthday"
                            name="birthday"
                            placeholder="YYYY-MM-DD"
                            type="text"/>
                    </div>

                </div>
                <button v-on:click="editUser" class="submitButton">
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
import { mapState, mapActions } from 'vuex'

export default {
    name:"editUser",
    data: function () {
        return {
            editData:{
                editDataForSend:{
                    name:'',
                    address:'',
                    email:'',
                    nickname:'',
                    id:'',
                    password:'',
                    birthday:'',
                },
                password2:'',
            },
            passwordType:"text",
            passwordConfirmType:"text",
        }
    },
    methods:{
        ...mapActions(['editUser','getUserData'])
    },
    created: function(){
        this.getUserData()
        console.log(this.userData)
        this.editData.editDataForSend.name=this.userData.name
        this.editData.editDataForSend.address=this.userData.address
        this.editData.editDataForSend.email=this.userData.email
        this.editData.editDataForSend.nickname=this.userData.nickname
        this.editData.editDataForSend.id=this.userData.id
        this.editData.editDataForSend.password=this.userData.password
        this.editData.editDataForSend.birthday=this.userData.birthday
    },
    computed:{
        ...mapState(["userData"]),
    },
}
</script>

<style>
.form-wrap{
    justify-content: center;
    align-items: center;
    margin-left: 34%;
    margin-top: 30px;
    margin-bottom: 20px;
}
.input-wrap{
    display: flex;
    /* justify-content: flex-start; */
    margin-bottom: 10px;
}
hr{
    width:40%;
    margin-bottom: 20px;
    height:3px;
    border-color: black;
}
label{
    width: 150px;
    text-align: left;
    font-weight: 550;
    color: black;
}
input{
    width: 30%;
    height: 40px;
    border: 1px solid grey;
    border-radius: 5px;
    opacity: 0.5;
}
.submitButton{
    color: rgb(202, 51, 195);
    background: transparent;
    border: 3px solid plum;
    border-radius: 5px;
    -webkit-transition: all .5s ease;
    transition: all .5s ease;
    -webkit-transform: translate(0, 0);
    transform: translate(0, 0);
    width:25%;
    height: 50px;
    margin-top: 10px;
}
.submitButton:hover{
    background-color: rgb(202, 51, 195);
    color: white ;
    text-decoration: solid;
    border: 3px solid rgb(202, 51, 195);
    -webkit-transition: all .35s ease;
    transition: all .35s ease;
    /* >a
        color aqur-bg
        -webkit-transition all .35s ease
        transition all .35s ease */
}
    
</style>