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
            <input id="title" type="text" class="form-control form-control-lg" v-model="article.title"/>
          </div>
          <div class="form-group w-75 mb-5 mx-auto">
            <p class="align-self-center m-1 text-left">Price</p>
            <input id="price" type="number" class="form-control form-control-lg" placeholder="가격을 입력해 주세요." v-model="article.price"/>
          </div>
          <div class="form-group w-75 mx-auto">
            <p class="align-self-center m-1 text-left">Content</p>
            <textarea placeholder="내용을 입력해 주세요." class="form-control form-control-lg" v-model="article.description" id="content" cols="30" rows="10"></textarea>
          </div>
      </div>
    </div>
    <button class="btn btn-secondary" type="submit" @click="updateArticle({articleUpdateData,temp:1})">수정</button>
    <button class="ml-1 btn btn-danger" type="submit" @click="deleteItem">삭제</button>
  </div>
</template>

<script>
const BACK_URL = "http://127.0.0.1:8080"
import axios from "axios"

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
          MinPrice:null,
          urlLink:null,
          endTime:null,
        },
      }
    },
    methods: {
        // 임시저장 리스트에서 클릭시 
        getTempItem () {
                  const config = {
          headers: {
            Authorization: `${this.$cookies.get('auth-token')}`
          }
        }
        axios.get(`${BACK_URL}/post/detail/${this.$route.params.ID}/${config.headers.Authorization}`)
          .then((res) => {
            this.temparticle.title = res.data.title
          })
          .catch((err) => {
            console.error(err)
          })
        },
        // 임시저장 버튼 눌렀을때
        saveTempItem () {
            event.preventDefault()
            axios.post(`${BACK_URL}/post/update/${this.tempfalse}`, this.temparticle)
            .then((response) => {
              this.$router.push({name:'articleDetail',params:this.$route.params.ID})
              })
            .catch((err)=>{
              console.error(err)
            })
        },

        saveItem () {
            event.preventDefault()
            axios.post(`${BACK_URL}/post/update/${this.temptrue}`, this.temparticle)
            .then((response) => {
              this.$router.push({name:'articleDetail',params:this.$route.params.ID})
              })
            .catch((err)=>{
              console.error(err)
            })
        },
        
        getItem () {
          const config = {
            headers: {
              Authorization: `${this.$cookies.get('auth-token')}`
            }
          }
          axios.get(`${BACK_URL}/post/detail/${this.$route.params.ID}/${config.headers.Authorization}/`)
          .then((response)=>{
            console.log(response)
            this.article.pid = response.data.pid
            this.article.title = response.data.title
            this.article.price = response.data.price
            this.article.description = response.data.description
          })
          .catch((err)=>{
            console.error(err)
          })
        },

        deleteItem(){
          
        },    
    },
    created: function(){
        this.getItem()
    },
}
</script>
<style>
</style>