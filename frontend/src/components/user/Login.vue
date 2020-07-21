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
                        <input v-model="id"
                        id="id" 
                        placeholder="아이디를 입력해주세요"
                        type="text"/>

                </div>
                <div class="form-group">
                                        <input v-model="password" type="password"
                        id="password"
                        placeholder="영문, 숫자 혼용 8자 이상"
                        @keypress.enter="login"/>

                </div>
                <button type="submit" class="btn btn-primary" @click="login" data-dismiss="modal">로그인</button>
            </form>
        </div>
        <div class="modal-footer">
            <router-link v-bind:to="{name:constants.URL_TYPE.USER.JOIN}" class="btn btn-secondary" data-dismiss="modal">회원가입</router-link>
        </div>
        </div>
    </div>
    </div>
    <!-- Modal -->
</template>

<script>
    const BACK_URL = 'http://127.0.0.1:8080'
    import constants from '../../lib/constants'
    import axios from 'axios'
    export default {
        components: {
        },
        created(){
        },
        watch: {
        },
        methods: {
            login:function(){
                event.preventDefault()
                axios.get(`${BACK_URL}/account/login/${this.id}/${this.password}`)
                .then(response=>{
                    alert("login success");
                    this.$cookies.set('auth-token', response.data.key);
                    localStorage.setItem('id', this.id);
                    var id = localStorage.getItem('id');
                    console.log(id)
                    this.isLoggedin = true
                    this.$emit('loginDone')
                    this.$router.push("/home");
                })
                .catch(err=>{
                    console.log(err)
                });
                
            }
        },
        data: () => {
            return {
                constants,
                id: '',
                password: '',
                isLoggedin: false,

            }
        }
    }
</script>