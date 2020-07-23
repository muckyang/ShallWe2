<template>
  <div class="like-content">
    <button v-if="!isLiked" @click="like" class="btn-secondary like-review"><i class="fas fa-heart"></i> 찜하기</button>
    <button v-if="isLiked" @click="like" class="btn-secondary like-review"><i class="fas fa-heart-broken"></i> 찜하기 취소</button>
  </div>
</template>

<script>

import axios from 'axios'
const BACK_URL = "http://127.0.0.1:8080"

export default {
    name: "articleLike",
    props: {
        article: Object,
        isLiked: Boolean,
    },
    methods: {
        like(){
          const config = {
          headers: {
            Authorization: `${this.$cookies.get('auth-token')}`
            }
          }
           axios.post(`${BACK_URL}/like/${this.article.pid}/${config.headers.Authorization}`)
           .then(res => { 
                    console.log(res)
                    console.log('좋아요기능 됨 ?')
                    this.$emit('like-change')
            })
            .catch(err => console.log(err.response.data))
            },
    }
}
</script>

<style>
.like-content {
    /* display: inline-block; */
    width: 100%;
    /* margin: 40px 0 0;
    padding: 47px 0 0; */
    font-size: 17px;
    /* border-top: 10px dashed #eee; */
    text-align: center;
}
.like-content .btn-secondary {
    display: block;
    /* margin: 40px auto 0px; */
    text-align: center;
    background: #ed2553;
    border-radius: 3px;
    box-shadow: 0 10px 20px -8px rgb(240, 75, 113);
    padding: 10px 17px;
    font-size: 17px;
    cursor: pointer;
    border: none;
    outline: none;
    color: #ffffff;
    text-decoration: none;
    -webkit-transition: 0.3s ease;
    transition: 0.3s ease;
}
.like-content .btn-secondary:hover {
      transform: translateY(-3px);
}
.like-content .btn-secondary .fa {
      margin-right: 5px;
}
/* .animate-like {
    animation-name: likeAnimation;
    animation-iteration-count: 1;
    animation-fill-mode: forwards;
    animation-duration: 0.65s;
}
@keyframes likeAnimation {
  0%   { transform: scale(30); }
  100% { transform: scale(1); }
} */
</style>