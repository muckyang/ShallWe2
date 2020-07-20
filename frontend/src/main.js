import Vue from 'vue'
import App from './App.vue'
import router from './router'
import VueCookies from 'vue-cookies'

Vue.use(VueCookies)

/* eslint-disable no-new */
new Vue({
  el: '#app',
  router, 
  components: { App }, 
  template: '<App/>'
})
 