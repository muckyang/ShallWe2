<template>
  <div>
    <h1>articleCreate</h1>
    <p>Title</p>
    <input type="text" id="title" v-model="articleData.title">
    <p>Writer</p>
    <input type="text" id="title" v-model="articleData.writer">
    <p>Content</p>
    <textarea id="content" rows="10" v-model="articleData.description"></textarea>
    <button type="submit" @click="createArticle" value="Submit">Submit</button>
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