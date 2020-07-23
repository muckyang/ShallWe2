<template>
    <div>
        <div class="profile-block"></div>
        <div class="profile-0">
            <div class="container">
                <div class="row">
                    <div class="col-6 profile-1 mr-1">
                        <div class="media">
                            <img src="https://source.unsplash.com/random" class="mr-3 align-self-center" alt="..." style="height: 100px; width: 100px;">
                            <div class="media-body">
                                <h5 class="mt-2">이름{{ name }}</h5>
                                <p>자기소개</p>
                                <div class="intro">자기소개 내용</div>
                            </div>
                        </div>
                    </div>
                    <div class="col-2 profile-2 mr-1"></div>
                    <div class="col-3 profile-3"></div>
                </div>
            </div>
        </div>
        <div class="profile-block"></div>
        
        <!-- 수정필요 -->
        <div class="container">
            <div class="row">
                <div class="d-flex flex-col">
                    <div class="list-group">
                        <a href="#" class="list-group-item list-group-item-action">
                            항목1
                        </a>
                        <a href="#" class="list-group-item list-group-item-action">항목2</a>
                        <a href="#" class="list-group-item list-group-item-action">항목3</a>
                        <a href="#" class="list-group-item list-group-item-action">항목4</a>
                        <a href="#" class="list-group-item list-group-item-action">항목5</a>
                    </div>
                    <div class="jumbotron jumbotron-fluid">
                        <div class="container">
                            <h1 class="display-4">항목 이름</h1>
                            <p class="lead">항목 내용</p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- 수정필요 -->


        <div class="mt-3">
            <button class="btn btn-danger mr-1" @click="deleteUser">회원 탈퇴 하기</button>
            <router-link class="btn btn-info ml-1" v-bind:to="{name:constants.URL_TYPE.USER.EDIT}">회원 정보 수정하기</router-link>
        </div>
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
            const config = {
                headers: {
                    Authorization: `${this.$cookies.get('auth-token')}`
                }
            }
            axios.get(`${BACK_URL}/info/${config.headers.Authorization}`)
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
            const config = {
                headers: {
                    Authorization: `${this.$cookies.get('auth-token')}`
                }
            }
             axios.get(`${BACK_URL}/account/delete/${config.headers.Authorization}`)
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
    .profile-block {
        height: 50px;
        background-color: lightgrey;
    }
    .profile-0 {
        background-color: lightgrey;
    }
    .profile-1 {
        height: 200px;
        border: solid black;
        background-color: white;
    }
    .profile-2 {
        height: 200px;
        border: solid black;
        background-color: white;
    }
    .profile-3 {
        height: 200px;
        border: solid black;
        background-color: white;
    }
    .intro {
        height: 100px;
        border: solid black;
    }
</style>