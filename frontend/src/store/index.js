import Vue from 'vue'
import Vuex from 'vuex'

import axios from "axios"
import cookies from 'vue-cookies'
import router from '../router';

const BACK_URL = "http://127.0.0.1:8080"

Vue.use(Vuex)



export default new Vuex.Store({
  state: {
    authToken: cookies.get('auth-token'),
    isLoggedin:false,
    userData:{
      name:'',
      address:'',
      email:'',
      nickname:'',
      id:'',
      password:'',
      birthday:'',
    },

    articles:[],
  },

  getters:{
    
  },

  mutations: {
    //사용자 관리
    SET_TOKEN(state,token){
      state.authToken=token
      cookies.set('auth-token', token)
      state.isLoggedin=true
      alert("login success")
      router.push('/');
    },
    REMOVE_TOKEN(state){
      state.authToken=null
      cookies.remove('auth-token')
      state.isLoggedin=false
    },
    loginCheck(state){
      if (!!state.authToken){
        state.isLoggedin = true
      }else{
        state.isLoggedin = false
      }
    },
    GET_USERDATA(state,userData){
      state.userData.name=userData.name
      state.userData.address=userData.address
      state.userData.email=userData.email
      state.userData.nickname=userData.nickname
      state.userData.id=userData.id
      state.userData.password=userData.password
      state.userData.birthday=userData.birthday
      console.log(state.userData.name)
    },


    //게시글 관리
    GET_ARTICLES(state,articles){
      state.articles=articles
    },
  },

  actions: {
    //사용자 인증
    signUp(context,signUpData){
      if (signUpData.signUpDataForSend.password===signUpData.password2){
        axios.post(`${BACK_URL}/account/signup`, signUpData.signUpDataForSend)
        .then((res) => {
            console.log(res,"COMPLETE")
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
    login({commit},loginData){
      event.preventDefault()
        axios.get(`${BACK_URL}/account/login/${loginData.id}/${loginData.password}`)
        .then(response=>{
          commit('SET_TOKEN',response.data) 
        })
        .catch(err=>{
            console.log(err)
        });
    },
    //profile
    getUserData({state,commit}){
      const config = {
        headers: {
            Authorization: state.authToken
        }
      }
      axios.get(`${BACK_URL}/account/read/${config.headers.Authorization}`)
      .then((response)=>{
        commit('GET_USERDATA',response.data)
        axios.defaults.headers.common['Authorization'] = config.headers.Authorization;
      })
      .catch((err)=>{
          console.error(err)
      })
    },
    deleteUser({state}){
      const config = {
        headers: {
            Authorization: state.authToken
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
    },


    //게시글 관리
    //전체 조회, 임시저장글 조회
    getArticles({state,commit},bool){
      if (bool) {
       axios.get(`${BACK_URL}/post/read/${bool}`)
          .then((response) => {
            commit('GET_ARTICLES',response.data.postList)
          })
          .catch((err) => {
            console.error(err)
          })
      }else{
        const config = {
          headers: {
            Authorization: state.authToken
          }
        }
        axios.get(`${BACK_URL}/post/read/${bool}/${config.headers.Authorization}`)
          .then((response) => {
            commit('GET_ARTICLES',response.data.postList)
          })
          .catch((err) => {
            console.error(err)
          })  
      }
    },
  },

  modules: {
  }
})
