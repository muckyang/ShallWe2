import Vue from 'vue'
import Router from 'vue-router'

import constants from '../lib/constants'

import Home from '../page/Home.vue'

// 유저
import Login from '../page/user/Login.vue'
import Join from '../page/user/Join.vue'
import editUser from '../page/user/editUser.vue'
import Profile from '../page/user/Profile.vue'
import emailAuthenticate from '../page/user/emailAuthenticate.vue'


// 포스트
import List from '../page/post/List.vue'

Vue.use(Router)

export default new Router({
  routes: [
    //Home
    {
      path:'/',
      name:constants.URL_TYPE.HOME,
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
    // 포스트
    { 
      path: '/list',
      name: constants.URL_TYPE.POST.MAIN,
      component: List
    },
    
  ]
})