<template>
    <div id='subnav'>
        <img class="mt-5" style="height: 250px" src="../assets/img/logo.png" alt="">
        <p class="mt-3 words">"Food for you~♥"</p>
        <p class="words">자취생들을 위한 요리 추천</p>
        <p class="words">널 위해 준비했어!</p>
    </div>
</template>

<script>
export default {
    name:'Home',
    data: function(){
        return{

        }
    }

}
</script>

<style>
#subnav {
    height: 450px;
    background: #FF8008;  /* fallback for old browsers */
    background: -webkit-linear-gradient(to right, #FFC837, #FF8008);  /* Chrome 10-25, Safari 5.1-6 */
    background: linear-gradient(to right, #FFC837, #FF8008); /* W3C, IE 10+/ Edge, Firefox 16+, Chrome 26+, Opera 12+, Safari 7+ */
    opacity: 0.9;
}
.words {
    color: wheat;
}
</style>