<template>
  <div>

    <div class="container m-5 mx-auto w-75 font-weight-bold">
      <div class="text-center shadow border mb-5 bg-white rounded-lg m-5">
        <form>
          <div class="d-flex inline">
            <p class="w-75 font-weight-bold h4 my-4 text-left ml-5 pl-5">New Article</p>

          </div>
          <hr class="mt-0"><br>
          <div class="mt-2 mb-5 w-75 mx-auto">
            <p class="align-self-center m-1 text-left">Title</p>
            <input id="title" type="text" class="form-control form-control-lg" placeholder="제목을 입력해 주세요." v-model="articleData.title"/>
          </div>
          <div class="mt-2 mb-5 w-75 mx-auto">
            <p class="align-self-center m-1 text-left">Writer</p>
            <input id="title" type="text" class="form-control form-control-lg" placeholder="제목을 입력해 주세요." v-model="articleData.writer"/>
          </div>
          <div class="mt-2 mb-5 w-75 mx-auto">
            <p class="align-self-center m-1 text-left">Content</p>
            <textarea placeholder="내용을 입력해 주세요." class="form-control form-control-lg" v-model="articleData.description" id="content" cols="30" rows="10"></textarea>
          </div>
          <div class="pb-5">
            <button class="font-weight-bold btn btn-dark" type="submit" @click="createArticle" value="Submit">Submit</button>
          </div>
        </form> 
      </div>
    </div>

    <!-- <p>Writer</p>
    <input type="text" id="title" v-model="articleData.writer"> -->
  </div>
</template>

<script>
  const BACK_URL = 'http://127.0.0.1:8080'
  import axios from 'axios'

  export default {
    name: "CreateView",
    data() {
      return {
        articleData: {
          title: null,
          memberAmount : null,
          writer : "asdsad",
          price : null,
          description: null

        }
      };
    },
    methods: {
      createArticle(event) {
        event.preventDefault()
        const config = {
          headers: {
            Authorization: `Token ${this.$cookies.get('auth-token')}`
          }
        }
        axios.post(BACK_URL+'/post/create/',this.articleData, config)
          .then(res => { 
            console.log(res.data) 
            this.$router.push('article')
          })
          .catch(err => console.log(err.response.data))
      },
    },
  }
</script>

<style>

</style>