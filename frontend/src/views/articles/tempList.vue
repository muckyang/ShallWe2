<template>
    <div class="mt-5">
    <div class="container">
      <div class="row">
        <!-- Card -->
        <div class="card col-3" style="width: 18rem;" v-for="temp in tempsList" :key="temp.id">
          <!-- 사진들은 다시 고를 겁니다. 현재는 랜덤 사진입니다. -->
          <img src="https://source.unsplash.com/random" class="card-img-top" alt="...">
          <div class="card-body">
            <h5 class="card-title">{{ temp.무엇 }}</h5>
            <p class="card-text">{{ temp.무엇 }}</p>
            <router-link class="btn btn-primary" :to="{name:'articleUpdate',params:{ID:`${temp.id}`}}">수정하기</router-link>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
const BACK_URL = "http://127.0.0.1:8080"
import axios from "axios"

export default {
    name: 'tempList',
    data: function () {
        return {
            tempsList: [],
        }
    },
    methods: {
        getTempsList () {
            axios.get(`${BACK_URL}/post/read/` + false)
            .then((res) => {
                this.tempsList = res.data.result
            })
            .catch((err) => {
                console.log(err)
            })
        },
    },
    created () {
        this.getTempsList()
    }
}
</script>

<style>

</style>