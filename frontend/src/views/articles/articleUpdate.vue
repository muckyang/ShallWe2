<template>
  <div>
   <div class="container m-5 mx-auto font-weight-bold">
      <div class="shadow border rounded m-5">
          <div class="form-group mb-5 w-75 mx-auto">
            <p class="align-self-center m-1 mt-3 text-left">Image</p>
            <input type="file" class="form-control form-control-lg" />
          </div>
          <div class="form-group mb-5 w-75 mx-auto">
            <p class="align-self-center m-1 text-left">Title</p>
            <input id="title" type="text" class="form-control form-control-lg" v-model="articleData.title"/>
          </div>
          <div class="form-group w-75 mb-5 mx-auto">
            <p class="align-self-center m-1 text-left">Price</p>
            <input id="minPrice" type="number" class="form-control form-control-lg" placeholder="가격을 입력해 주세요." v-model="articleData.minPrice"/>
          </div>
          <div class="form-group w-75 mx-auto">
            <p class="align-self-center m-1 text-left">Content</p>
            <textarea placeholder="내용을 입력해 주세요." class="form-control form-control-lg" v-model="articleData.description" id="content" cols="30" rows="10"></textarea>
          </div>
      </div>
    </div>
    <button class="btn btn-secondary" type="submit" @click="updateArticle({articleUpdateData,temp:1})">수정</button>
    <button class="ml-1 btn btn-danger" type="submit" @click="deleteArticle(articleUpdateData.articleId)">삭제</button>
  </div>
</template>

<script>
const BACK_URL = "http://127.0.0.1:8080"
import axios from "axios"
import {mapActions,mapState} from 'vuex'

export default {
    name:'articleUpdate',
    data () {
      return {
        articleUpdateData: {
          articleId: this.$route.params.ID,
          categoryId:null,
          title: null,
          address:null,
          description:null,
          minPrice:null,
          urlLink:null,
          image:null,
          endTime:null,
          token:this.$cookies.get('auth-token')
        },
      }
    },
    methods: {
        ...mapActions(['getArticle','deleteArticle','updateArticle']),
    },
    created: function(){
        this.getArticle(this.$route.params.ID)
        this.articleUpdateData=this.articleData
    },
    computed:{
      ...mapState(['articleData'])
    }
}
</script>
<style>
</style>