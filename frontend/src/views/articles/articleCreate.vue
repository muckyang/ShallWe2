<template>
  <div>
    <hr>
    <label for="">카테고리</label>
    <input type="number" v-model="articleData.Temp">
    <hr>
    <label for="">제목</label>
    <input type="text" v-model="articleData.title">
    <label for="">address</label>
    <input type="text" v-model="articleData.address">
    <hr>
    <label for="">내용</label>
    <input type="text" v-model="articleData.description">
    <hr>
    <label for="">최소주문금액</label>
    <input type="number" v-model="articleData.minPrice">
    <label for="">urlLink</label>
    <input type="text" v-model="articleData.urlLink">
    <label for="">Image</label>
    <input type="image" v-model="articleData.Image">
    <label for="">Temp</label>
    <input type="number" v-model="articleData.temp">
    <label for="">category</label>
    <input type="number" v-model="articleData.categoryId">
    <button class="font-weight-bold btn btn-dark" type="submit" @click="createArticle({articleData,temp:1})" value="Submit">Submit</button>
    <button @click="tempSaveArticle({articleData,temp:0})">임시저장</button>



    <!-- <div class="container m-5 mx-auto font-weight-bold">
      <div class="shadow border rounded m-5">
          <div class="d-flex">
            <p class="w-75 font-weight-bold h4 my-4 text-left ml-5 pl-5">New Article</p>
          </div>
          <hr class="mt-0"><br>
          <div class="form-group mb-5 w-75 mx-auto">
            <p class="align-self-center m-1 text-left">Image</p>
            <input ref="imageInput" type="file" hidden @change="imageChange" class="form-control form-control-lg" />
            <img v-if="imageUrl" :src="imageUrl" alt="">
            <p><button class="btn btn-dark mt-4" @click="imageUpload" >이미지 업로드</button></p>
          </div>
          <div class="form-group mb-5 w-75 mx-auto">
            <p class="align-self-center m-1 text-left">Title</p>
            <input id="title" type="text" class="form-control form-control-lg" placeholder="제목을 입력해 주세요." v-model="articleData.title"/>
          </div>
          <div class="form-group w-75 mb-5 mx-auto">
            <p class="align-self-center m-1 text-left">minPrice</p>
            <input id="minPrice" type="number" class="form-control form-control-lg" placeholder="가격을 입력해 주세요." v-model="articleData.minPrice"/>
          </div>
          <div class="form-group w-75 mx-auto">
            <p class="align-self-center m-1 text-left">Content</p>
            <textarea placeholder="내용을 입력해 주세요." class="form-control form-control-lg" v-model="articleData.description" id="content" cols="30" rows="10"></textarea>
          </div>
          <div class="pb-5">
            <button class="font-weight-bold btn btn-dark" type="submit" @click="createArticle({articleData,temp:1})" value="Submit">Submit</button>
            <button @click="tempSaveArticle({articleData,temp:0})">임시저장</button>
          </div>
      </div>
    </div> -->
  </div>
</template>

<script>
  const BACK_URL = 'http://127.0.0.1:8080'
  import axios from 'axios'
    import {mapActions} from 'vuex'


  export default {
    name: "CreateView",
    data() {
      return {
        articleData: {
          categoryId : 0,
          title: null,
          writer: null,
          address: null,
          description: null,
          minPrice: null, 
          sumPrice: null,
          urlLink: null,
          imgae: null,
          temp: null,
          token:this.$cookies.get('auth-token')
        },
        imageUrl: null, //다시 검토
      };
    },
    methods: {
      ...mapActions(['createArticle','tempSaveArticle']),
      imageUpload(){
        this.$refs.imageInput.click()
      },
      imageChange() {
        console.log(e.targetfiles)
        console.log('이미지 등록 됨?')
        const file = e.target.files[0];
        this.imageUrl = URL.createObjectURL(file);
      },
    }
  }
</script>

<style>

</style>