import Vue from 'vue'
import Vuex from 'vuex'

import axios from "axios"
import cookies from 'vue-cookies'
import router from '../router';

const BACK_URL = "http://127.0.0.1:8080"

Vue.use(Vuex)



export default new Vuex.Store({
  state: {
    //사용자 인증
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
    isSended:false,

    //게시글
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
      router.push('/')
    },
    REMOVE_TOKEN(state){
      state.authToken=null
      cookies.remove('auth-token')
      state.isLoggedin=false
      router.push('/')
    },
    loginCheck(state){
      if (!!state.authToken){
        state.isLoggedin = true
      }else{
        state.isLoggedin = false
      }
    },
    sendCheck(state){
      state.isSended=true
    },
    GET_USERDATA(state,userData){
      state.userData.name=userData.name
      state.userData.address=userData.address
      state.userData.email=userData.email
      state.userData.nickname=userData.nickname
      state.userData.id=userData.id
      state.userData.password=userData.password
      state.userData.birthday=userData.birthday
    },


    //게시글 관리
    GET_ARTICLES(state,articles){
      state.articles=articles
    },
  },

  actions: {
    //사용자 인증
    sendEmail(context,data){
      if (data.signUpDataForSend.password===data.password2){
        console.log(data.signUpDataForSend.email)
        axios.post(`${BACK_URL}/account/sendmail`, data.signUpDataForSend)
        .then((res)=>{
          this.commit('sendCheck')
          alert("메일로 인증 코드가 발송되었습니다.")
        })
        .catch((err)=>{
          console.log(err)
        })
      }else{
        alert("비밀번호를 다시 설정해주세요")
      }
    },
    signUp({commit},signUpData){
      console.log(signUpData.signUpDataForSend)
        axios.post(`${BACK_URL}/account/signup`, signUpData.signUpDataForSend)
        .then((res) => {
            console.log(res,"COMPLETE")
            alert("회원가입이 완료되었습니다.")
            router.push('/');
        })
        .catch((err) => {
          console.log(err)
            alert("인증 코드를 다시 확인해주세요")
        });
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
      axios.get(`${BACK_URL}/account/read`)
      .then((response)=>{
        commit('GET_USERDATA',response.data)
        axios.defaults.headers.common['Authorization'] = config.headers.Authorization;
      })
      .catch((err)=>{
          console.error(err)
      })
    },
    editUser({commit},editData){
      if(editData.editDataForSend.password===editData.password2){ 
          const config = {
              headers: {
                  Authorization: `${cookies.get('auth-token')}`
              }
          }
          axios.post(`${BACK_URL}/account/update`,editData.editDataForSend,config)
          .then(()=>{
            // axios.defaults.headers.common['Authorization'] = config.headers.Authorization;
              alert("수정이 완료되었습니다. 다시 로그인해 주세요")
              commit('REMOVE_TOKEN')
          })
          .catch((err)=>{
              console.error(err)
          })
      }else{
          console.log("else")
          alert("비밀번호를 확인해 주세요")
      }
    },
    deleteUser({state,commit}){
      const config = {
        headers: {
            Authorization: state.authToken
        }
      }
      axios.get(`${BACK_URL}/account/delete`)
        .then(()=>{
            axios.defaults.headers.common['Authorization'] = config.headers.Authorization;
            commit("REMOVE_TOKEN")
            alert("회원 탈퇴가 완료되었습니다.")
            router.push('/');
        })
        .catch((err)=>{
            console.error(err)
        })
    },


    //게시글 관리
    //전체 조회, 임시저장글 조회
    getArticles({state,commit},num){
      if (num===0) {
       axios.get(`${BACK_URL}/post/read/${num}`,null)
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
    //게시글 생성
    createArticle({state,commit},articleData){
      const config = {
        headers: {
          Authorization: state.authToken
        }
      }
      axios.post(`${BACK_URL}/post/create/${articleData.temp}` ,articleData.articleData)
        .then(() => { 
          axios.defaults.headers.common['Authorization'] = config.headers.Authorization;
          router.push('article')
        })
        .catch(err => console.log(err))
    },
    editArticle({state,commit},){
      const config = {
        headers: {
          Authorization: state.authToken
        }
      }
      axios.post(`${BACK_URL}/post/update/${articleData.temp}` ,articleData.articleData)
        .then(() => { 
          axios.defaults.headers.common['Authorization'] = config.headers.Authorization;
          router.push('article')
        })
        .catch(err => console.log(err))
    },
    
    //게시글 수정하기
    updateArticle(context,updateData){
      axios.post(`${BACK_URL}/post/update/${updateData.temp}`, updateData.articleUpdateData)
      .then((response) => {
        this.$router.push({name:'articleDetail',params:this.$route.params.ID})
        })
      .catch((err)=>{
        console.error(err)
      })
    },
    deleteArticle(context,articleId){
      axios.get(`BACK_URL/post/delete/${articleId}`)
       .then(()=>{
          axios.defaults.headers.common['Authorization'] = config.headers.Authorization;
          router.push({name:'articleList'})
       })
       .catch(()=>{
         console.log(err)
       })
    }

  },

  modules: {
  }
})
