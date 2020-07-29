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
    isTerm:false,
    articleData:{
      articleId:'',
      userId:'',
      categoryId:'',
      title:'',
      writer:'',
      address:'',
      description:'',
      minPrice:'',
      sumPrice:'',
      urlLink:'',
      image:'',
      temp:'',
      endTime:'',
      createTime:'',
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
    termCheck(state){
      
      if(state.isTerm){
        state.isTerm = false
      }else{
        state.isTerm = true
      }
      console.log(state.isTerm)
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
    GET_ARTICLE(state,response){
      console.log(response,"Mutations")
      state.articleData.articleId=response.data.articleId,
      state.articleData.userId=response.data.userId,
      state.articleData.categoryId=response.data.categoryId,
      state.articleData.title = response.data.title,
      state.articleData.writer=response.data.writer,
      state.articleData.address=response.data.address,
      state.articleData.description = response.data.description,
      state.articleData.minPrice = response.data.minPrice,
      state.articleData.sumPrice = response.data.sumPrice,
      state.articleData.urlLink=response.data.urlLink,
      state.articleData.image=response.data.image,
      state.articleData.temp=response.data.temp,
      state.articleData.endTime=response.data.endTime,
      state.articleData.createTime=response.data.createdTime
      console.log(state.articleData,"GET_ARTICLE")
    },

  },

  actions: {
    //사용자 인증
    sendEmail({state},data){
      if(state.isTerm){
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
      }else{
        alert("약관에 동의해주세요")
      }
    },
    signUp({commit},signUpData){
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
      const auth={token:state.authToken}
      axios.post(`${BACK_URL}/account/read`,auth)
      .then((response)=>{
        commit('GET_USERDATA',response.data)
      })
      .catch((err)=>{
          console.error(err)
      })
    },
    editUser({state,commit},editData){
      if(editData.editDataForSend.password===editData.password2){ 
          axios.post(`${BACK_URL}/account/update`,editData.editDataForSend)
            .then(()=>{
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
      const auth={token:state.authToken}
      axios.post(`${BACK_URL}/account/delete`,auth)
        .then(()=>{
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
    getArticles({state,commit},data){
      const auth={token:state.authToken}
      axios.post(`${BACK_URL}/post/read/${data.temp}/${data.categoryId}`,auth)
        .then((response) => {
          commit('GET_ARTICLES',response.data.postList)
        })
        .catch((err) => {
          console.error(err)
        })
    },
    //단일 게시글 조회
    getArticle({state,commit},articleID){
      const auth={token:state.authToken}
      axios.post(`${BACK_URL}/post/detail/${articleID}`,auth)
        .then((response)=>{
          console.log(response)
          commit('GET_ARTICLE',response)
        })
        .catch((err)=>{
          console.error(err)
        })
    },
    //게시글 생성
    createArticle(context,articleData){
      console.log(articleData.articleData)
      axios.post(`${BACK_URL}/post/create/${articleData.temp}` ,articleData.articleData)
        .then(() => { 
          router.push('article')
        })
        .catch(err => console.log(err))
    },
    //게시글 수정하기
    updateArticle({state},updateData){
      axios.post(`${BACK_URL}/post/update/${updateData.temp}`, updateData.articleUpdateData)
      .then((response) => {
        router.push(`/detail/${updateData.articleUpdateData.articleId}`)
      })
      .catch((err)=>{
        console.error(err)
      })
    },
    //게시글 삭제하기
    deleteArticle({state},articleId){
      const auth={token:state.authToken}
      axios.get(`${BACK_URL}/post/delete/${articleId}`,auth)
       .then(()=>{
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
