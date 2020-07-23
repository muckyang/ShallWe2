<template>
  <div class="container mt-3">
    <div class="container">
      <div class="container">
        <a href="#item-1" class="btn btn-info mb-3" >내용으로 <i class="fas fa-angle-down"></i></a><br>
      </div>
      <div class="container" data-spy="scroll" data-target="#navbar-example3" data-offset="0">
          <img class="MyImage" src="https://source.unsplash.com/random" alt="...">
        <div class="container articleInfo">
          <div class="row_container">
            <div class="title d-inline-block" id="item-1"><p>{{ article.title }}</p></div>
            <div class="d-inline-block float-right"><articleLike  @like-change="likeChange" :isLiked="isLiked" :article="article"/></div>
          </div>
          <!-- <div><p>가격 들어갈 자리</p></div> -->
          <div class="writer"><p>{{ article.price }}</p></div>
          <div class="description" ><p>{{ article.description }}</p></div>
          <!-- <div class="update btn btn-secondary "><router-link class="text-white text-decoration-none d-inline-block" :to="{name:'articleUpdate', params: {ID: `${article.pid}`}}">수정</router-link></div> -->
        
            <div class="dropdown dropleft float-right">
              <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                <i class="fas fa-align-justify"></i>
              </button>
              <div class="dropdown-menu">
                <a class="dropdown-item" href="#"><router-link class="text-decoration-none d-inline-block" :to="{name:'articleUpdate', params: {ID: `${article.pid}`}}">수정</router-link></a>
                <a class="dropdown-item" href="#">Link 2</a>
                <a class="dropdown-item" href="#">Link 3</a>
              </div>
            </div>
        
        </div> 

       
      </div>
      <!-- <p>Updated at {{ udate }}/{{ utime }}</p> -->    
    </div> 
    <commentList/>
  </div>
</template>

<script>
  const BACK_URL = "http://127.0.0.1:8080"
  import axios from "axios"
  import commentList from '@/components/comments/commentList'
  import articleLike from '@/components/articles/articleLike'
  
  export default {
    name:'articleDetail',
    components:{
      commentList,
      articleLike
    },
    data () {
      return {
        article: {
          pid:null,
          title: null,
          price: null,
          description: null,

          },
        isLiked:null,
      }
    },
    methods: {
        // getcurrentuser(){
        //   const axiosConfig = {
        //     headers:{
        //       Authorization : `Token ${this.$cookies.get('auth-token')}`
        //     },
        //   }
        //   axios.get(`${BACK_URL}/post/user/`,axiosConfig)
        //     .then((reaponse)=>{
        //       this.currentuser = reaponse.data.username
        //   if (this.article.user.username===this.currentuser){
        //     this.flag = true
        //   }  
        //     })
        //     .catch((err)=>{
        //       console.error(err)
        //     })
        // },
      getItem(){
        const config = {
          headers: {
            Authorization: `${this.$cookies.get('auth-token')}`
          }
        }
        axios.get(`${BACK_URL}/post/detail/${this.$route.params.ID}/${config.headers.Authorization}`)
        .then((response)=>{
          console.log(response)
          console.log('잘 나옴ㅇㅁㅇㅇㄴㅇㅁㅇ')
          this.article.pid = response.data.pid
          this.article.title = response.data.title
          this.article.price = response.data.price
          this.article.description = response.data.description

          // this.cdate=this.article.created_at.substr(0,10)
          // this.ctime=this.article.created_at.substr(11,8)
        })
        .catch((err)=>{
          console.error(err)
        })
      },
      likeCheck(){
        const config = {
          headers: {
            Authorization: `${this.$cookies.get('auth-token')}`
          }
        }
        console.log(this.$route.params.ID, '아티클넘버')
        axios.post(`${BACK_URL}/likedcheck/${this.$route.params.ID}/${config.headers.Authorization}`)
        .then(res => {
            this.isLiked = res.data.isLiked //true or false로 반환
        })
        .catch(err => console.log(err))
      },
      likeChange() {
        this.getItem()
        this.likeCheck()
      }
    },
    created: function(){
        // this.getcurrentuser()
    },
    mounted: function(){
      this.getItem()
      this.likeCheck()
    },
  }
</script>

<style>
.row_container{
  flex-direction: row;
}
.container{
  align-items: flex-start;
  /* flex-direction: column; */
  flex-direction: column;
}
.MyImage{
  width: 50%;
  height: 500px;
}
.doFlex{
  display: flex;
  padding: 10px 30px 10px 30px;
}
.articleInfo{
  margin-left: 15px;
  text-align: left;
}
/* .update{
  justify-content: bottom;
} */
.title{
  font-size: xx-large;
  font-weight: 600;

}

</style>