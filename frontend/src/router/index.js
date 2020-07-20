import Vue from 'vue'
import VueRouter from 'vue-router'

import constants from '../lib/constants'

import Home from '../views/Home.vue'

// 유저
import Login from '../components/user/Login.vue'
import Join from '../components/user/Join.vue'
import editUser from '../components/user/editUser.vue'
import Profile from '../components/user/Profile.vue'
import emailAuthenticate from '../components/user/emailAuthenticate.vue'

// 아티클
import articleCreate from '../views/articles/articleCreate.vue'
import articleDetail from '../views/articles/articleDetail.vue'
import articleUpdate from '../views/articles/articleUpdate.vue'
import articleView from '../views/articles/articleView.vue'

//검색
// import search from '../components/article/search.vue';

Vue.use(VueRouter)

  const routes = [
    //Home
    {
      path:'/',
      name:'HOME',
      component:Home,
    },   
    // 로그인
    { 
      path: '/user/login',
      name: constants.URL_TYPE.USER.LOGIN,
      component: Login
    },
    //회원가입
    {
      path: '/user/join',
      name: constants.URL_TYPE.USER.JOIN,
      component: Join
    },
    //회원 정보 조회
    {
      path:'/user/profile',
      name: constants.URL_TYPE.USER.PROFILE,
      component: Profile
    },
    //회원 정보 수정
    {
      path:'/user/edit',
      name: constants.URL_TYPE.USER.EDIT,
      component: editUser
    },
   
    //이메일 인증
    {
      path: '/user/email',
      name: constants.URL_TYPE.USER.EMAIL,
      component: emailAuthenticate
    },
    {
      path: '/create',
      name: 'articleCreate',
      component: articleCreate
    },
    {
      path: '/detail/:ID',
      name: 'articleDetail',
      component: articleDetail
    },
    {
      path: '/update/:ID',
      name: 'articleUpdate',
      component: articleUpdate
    },
    {
      path: '/article',
      name: 'articleView',
      component: articleView
    },
  
  
  ]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
