<template>
  <div>
    <nav class="navbar navbar-expand-lg navbar-light bg-light">
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>
      <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav">
              <li class="nav-item"><a class="nav-link" href="/"><i class="fas fa-home"></i></a></li>
              <li class="nav-item"><router-link v-bind:to="{name:constants.URL_TYPE.POST.MAIN}" class="nav-link"><i class="far fa-sticky-note"></i></router-link></li>
              <li class="nav-item"><router-link
                  v-bind:to="{name:constants.URL_TYPE.USER.LOGIN}"
                  class="nav-link"
                  data-toggle="modal"
                  data-target="#exampleModal"
              ><i class="fas fa-user-alt"></i></router-link>
              </li>
              <li class="nav-item"><router-link v-bind:to="{name:constants.URL_TYPE.USER.JOIN}" class="nav-link"><i class="fas fa-user-plus"></i></router-link></li>
              <li class="nav-item">
                <a class="nav-link" @click="logout"><i class="fas fa-sign-out-alt"></i></a>
              </li>
              <li class="nav-item">
                <router-link
                  v-bind:to="{name:constants.URL_TYPE.USER.PROFILE}"
                  class="nav-link"
                ><i class="far fa-address-card"></i></router-link>
              </li>
              <li class="nav-item">
                <router-link
                  v-bind:to="{name:constants.URL_TYPE.USER.EDIT}"
                  class="nav-link"
                ><i class="fas fa-user-edit"></i></router-link>
              </li>
        </ul>
      </div>
    </nav>
    <div class="boarder-1"></div>
  </div>
</template>   

<script>
const BACK_URL = "http://127.0.0.1:8080";
import constants from "../../lib/constants";

export default {
  name: "Header",
  components: {},
  props: ["isHeader"],
  computed: {},
  watch: {},
  created() {},
  methods: {
    logout: function() {
      localStorage.removeItem("id");
      var aa = localStorage.getItem("id");
      console.log(aa);
    }
  },
  data: function() {
    return {
      constants,
      keyword: ""
    };
  }
};
</script>

<style scope>
  nav {
    background: #FF8008;  /* fallback for old browsers */
    background: -webkit-linear-gradient(to right, #FFC837, #FF8008);  /* Chrome 10-25, Safari 5.1-6 */
    background: linear-gradient(to right, #FFC837, #FF8008); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
  }
  .boarder-1 {
    height: 0.5px;
    background-color: white;
  }
</style>
