<template>
  <div>
    <h1>articleList</h1>
    <router-link to="/create">글쓰기</router-link>
    <button @click="initArticles">asdaasdas</button>
    <table>
      <thead>
        <tr>
          <th>Pid</th>
          <th>Title</th>
          <th>Writer</th>
        </tr>
      </thead>
      <tbody>
        <tr v-for="article in articles" :key="article.pid">
          <th>{{ article.pid }}</th>
          <td><router-link :to="{name:'articleDetail',params:{ID:`${article.pid}`}}">{{article.description}}</router-link></td>
          <td>{{ article.description }}</td>
        </tr>
      </tbody>
    </table>
    <!-- Pagenation -->
    <div class="btn-cover d-flex justify-content-center">
      <nav aria-label="Page navigation example">
        <ul class="pagination">
          <li class="page-item click" @click="prevPage"><a class="page-link">Previous</a></li>
          <li class="page-item click" v-for="n in pageCount" :key="n" :pageNum=n><a class="page-link" @click="changePage(n)">{{n}}</a></li>
          <li class="page-item click"><a class="page-link" @click="nextPage">Next</a></li>
        </ul>
      </nav>
    </div>
  </div>
</template>

<script>
const BACK_URL = "http://127.0.0.1:8080"
import axios from "axios"

export default {
  name: 'articleList',
  data:function(){
    return{
      articles:[],
      page: 10,
      pageNum: 0,
      pageSize:10,
    }
  },
  methods:{
    initArticles(){
      console.log("VVVVVVVVVVVVVVV")
      axios.get(`${BACK_URL}/post/read`)
      .then((reaponse)=>{
        console.log(reaponse,"AAAAAAAAAAAAAAa")
        this.articles = reaponse.data.postList
      })
      .catch((err)=>{
        console.error(err)
      })
    },
    loginAuthorization(){
      if (!this.$session.get('jwt')){
        this.$alert("로그인 후 사용하세요");
        this.$router.push('/login')
        }
    },
    nextPage () {
      this.pageNum += 1;
      const n = this.pageCount
      if (this.pageNum>=n){
        this.pageNum=n-1
      }
    },
    prevPage () {
      this.pageNum -= 1;
      if (this.pageNum<0){
        this.pageNum=0
      }
    },
    changePage(n){
      this.pageNum = n-1
    },
  },
  computed: {
    pageCount () {
      let listLeng = this.articles.length,
          listSize = this.pageSize,
          page = Math.floor(listLeng / listSize);
      if (listLeng % listSize > 0) page += 1;
      return page;
    }

  },
  created(){
    this.loginAuthorization()
    this.initArticles()    
  },
  // watch:{
  //   articles(){
  //     this.initArticles()
  //   }
  // },
}
</script>

<style>

</style>