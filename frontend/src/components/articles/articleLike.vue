<template>
  <div>
    <button v-if="!isLiked" @click="like" class="btn btn-ouline-danger p-0 mb-5"><i class="fas fa-heart"></i> 좋아요</button>
    <button v-if="isLiked" @click="like" class="btn btn-outline-info"><i class="far fa-heart"></i>좋아요 취소</button>
  </div>
</template>

<script>
export default {
    name: "articleLike",
    props: {
        article: Object,
        isLiked: Boolean,
    },
    methods: {
        like(){
            axios.post(SERVER.BACK_URL + `/articles/${this.article.article.id}/like/`, null, { headers: {'Authorization':`Token ${this.$cookies.get('auth-token')}`}})
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

</style>