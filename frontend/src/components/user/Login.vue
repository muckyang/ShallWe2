<template>
    <!-- Modal -->
    <div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
        <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">로그인</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
            <span aria-hidden="true">&times;</span>
            </button>
        </div>
        <div class="modal-body">
            <form>
                <div class="form-group">
                    <div class="container">
                        <label for="" class="mr-3 col-3">이메일</label>
                        <input 
                        class="col-6"
                        v-model="loginData.id"
                        id="id" 
                        placeholder="아이디를 입력해주세요"
                        type="text"/>
                    </div>
                </div>
                <div class="form-group">
                    <div class="container">
                        <label for="" class="mr-3 col-3">패스워드</label>
                        <input 
                        class="col-6"
                        v-model="loginData.password" type="password"
                        id="password"
                        placeholder="영문, 숫자 혼용 8자 이상"
                        @keypress.enter="login"/>
                    </div>
                </div>
                
                <div class="modal-footer">
                    <button type="submit" class="btn btn-primary" @click="login(loginData)" data-dismiss="modal">로그인</button>
                    <button class="btn btn-warning text-white">카카오 로그인</button>
                    <router-link v-bind:to="{name:constants.URL_TYPE.USER.JOIN}" class="btn btn-secondary" data-dismiss="modal">회원가입</router-link>
                </div>
            </form>
        </div>
        </div>
    </div>
    </div>
    <!-- Modal -->
</template>

<script>
    const BACK_URL = 'http://127.0.0.1:8080'
    import constants from '../../lib/constants'
    import { mapActions } from 'vuex'

    export default {
        data: () => {
            return {
                constants,
                loginData:{
                    id: '',
                    password: '',
                },
                isLoggedin: false,
            }
        },
        methods: {
            ...mapActions(['login'])
        },
    }
</script>

<style>

</style>