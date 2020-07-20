<template>
  <div>
    <h1>New Recipe</h1>
    <hr class="hrform">
    <div class="aform">
      <form>
        <div class="form-group">
          <label for="title">Title</label>
          <input type="text" class="form-control" id="title" v-model="articleData.title">
        </div>
        <div class="form-group">
          <label for="content">Content</label>
          <textarea class="form-control" id="content" rows="10" v-model="articleData.content"></textarea>
        </div>
        <div class="d-flex">
          <div class="d-flex justify-content-end">
            <button type="submit" class="btn btn-primary" @click="createArticle" value="Submit">Submit</button>
          </div>
        </div>
      </form>
    </div>
  </div>
</template>

<script>
import axios from 'axios'

const BACK_URL = 'http://127.0.0.1:8000'

export default {
  name: "articleCreate",
  data() {
    return {
      articleData: {
        title: null,
        content: null,
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
      axios.post(BACK_URL+'/articles/article-create/',this.articleData,config)
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
.aform{
  margin:0 20%;
}
.hrform{
  margin: 0px 20% 40px 20%;
}
</style>