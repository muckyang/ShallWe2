<template>
  <div class="mt-3">
    <div class="container">
      <a href="#item-1" class="btn btn-info">내용으로</a>
      <div data-spy="scroll" data-target="#navbar-example3" data-offset="0">
        <img class="MyImage" src="https://source.unsplash.com/random" alt="...">
        <div class="articleInfo">
          <div class="title"><p>{{ article.title }}</p></div>
          <div><p>가격 들어갈 자리</p></div>
          <div class="writer"><p>{{ article.price }}</p></div>
          <div class="description" id="item-1"><p>{{ article.description }}</p></div>
          <div class="update btn btn-secondary"><router-link class="text-white text-decoration-none" :to="{name:'articleUpdate', params: {ID: `${article.pid}`}}">수정</router-link></div>
        </div> 
        <div><articleLike  @like-change="likeChange" :isLiked="isLiked" :article="article"/></div>
        <div class="update"><router-link :to="{name:'articleUpdate', params: {ID: `${article.pid}`}}">수정</router-link></div>

        <a href="javascript:;" @click="shareContent" id="kakao-link">
          <img src="../../assets/img/kakao_btn.png">
        </a>

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
      shareContent(){
        Kakao.Link.createDefaultButton({
        container: '#kakao-link',
        objectType: 'feed',
        content: {
          title: '디저트 사진',
          description: '아메리카노, 빵, 케익',
          imageUrl:
            'http://mud-kage.kakao.co.kr/dn/NTmhS/btqfEUdFAUf/FjKzkZsnoeE4o19klTOVI1/openlink_640x640s.jpg',
          link: {
            mobileWebUrl: 'https://developers.kakao.com',
            androidExecParams: 'test',
          },
        },
        social: {
          likeCount: 10,
          commentCount: 20,
          sharedCount: 30,
        },
        buttons: [
          {
            title: '웹으로 이동',
            link: {
              mobileWebUrl: 'https://developers.kakao.com',
            },
          },
          {
            title: '앱으로 이동',
            link: {
              mobileWebUrl: 'https://developers.kakao.com',
            },
          },
        ],
        success: function(response) {
          console.log(response);
        },
        fail: function(error) {
          console.log(error);
        }
      });
    },
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
        const articleId = this.$route.params.ID
        axios.post(BACK_URL + `/articles/${articleId}/like/check/`, null, { headers: {'Authorization':`Token ${this.$cookies.get('auth-token')}`}})
        .then(res => {
            this.isLiked = res.data.message
        })
        .catch(err => console.log(err.response.data))
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
      // this.likeCheck()
    },
  }
</script>

<style>
.MyImage{
  width: 35%;
  height: 300px;
}
.doFlex{
  display: flex;
  padding: 10px 30px 10px 30px;
}
.articleInfo{
  margin-left: 15px;
  text-align: left;
}
.update{
  justify-content: bottom;
}
.title{
  font-size: xx-large;
  font-weight: 600;

}

</style>