<template>
    <div>
        <!-- Carousel -->
        <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
            <div class="carousel-inner">
                <div class="carousel-item active">
                <!-- 사진들은 다시 고를 겁니다. 현재는 랜덤 사진입니다. -->
                <img src="https://source.unsplash.com/random" class="d-block w-100" alt="...">
                </div>
                <div class="carousel-item">
                <!-- 사진들은 다시 고를 겁니다. 현재는 랜덤 사진입니다. -->
                <img src="https://source.unsplash.com/random" class="d-block w-100" alt="...">
                </div>
                <div class="carousel-item">
                <!-- 사진들은 다시 고를 겁니다. 현재는 랜덤 사진입니다. -->
                <img src="https://source.unsplash.com/random" class="d-block w-100" alt="...">
                </div>
            </div>
            <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                <span class="sr-only">Previous</span>
            </a>
            <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                <span class="carousel-control-next-icon" aria-hidden="true"></span>
                <span class="sr-only">Next</span>
            </a>
        </div>
        <div class="blank-section"></div>
        <section class="home-section1">
            <h1>섹션1</h1>
        </section>
        <div class="blank-section"></div>
        <section class="home-section2">
            <h1>섹션2</h1>
        </section>
        <div class="blank-section"></div>
        <section class="home-section3">
            <h1>섹션3</h1>
        </section>
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
    .words {
        color: wheat;
    }
    .carousel-item {
        height: 400px;
    }
    .home-section1 {
        height: 400px;
        background-color: skyblue;
    }
    .home-section2 {
        height: 400px;
        background-color: pink;
    }
    .home-section3 {
        height: 400px;
        background-color: lightcoral;
    }
    .blank-section {
        height: 300px;
    }
</style>