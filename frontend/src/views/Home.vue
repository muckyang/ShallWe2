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
        <!-- 섹션1 -->
        <div class="blank-section d-flex align-items-center justify-content-center">
            <h1>섹션1</h1>
        </div>
        <!-- 셀프마진 -->
        <div style="background-color: powderblue; height: 50px"></div>
        <section style="background-color: powderblue;">
            <div class="container">
                <div class="row">
                    <!-- 섹션1 - 아이템1 -->
                    <div class="card col-3" style="width: 18rem;">
                        <img src="https://source.unsplash.com/random" style="height: 300px" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">제목</h5>
                            <p class="card-text">내용</p>
                            <a href="#" class="btn btn-primary">보러 가기</a>
                        </div>
                    </div>
                    <!-- 섹션1 - 아이템2 -->
                    <div class="card col-3" style="width: 18rem;">
                        <img src="https://source.unsplash.com/random" style="height: 300px" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">제목</h5>
                            <p class="card-text">내용</p>
                            <a href="#" class="btn btn-primary">보러 가기</a>
                        </div>
                    </div>
                    <!-- 섹션1 - 아이템3 -->
                    <div class="card col-3" style="width: 18rem;">
                        <img src="https://source.unsplash.com/random" style="height: 300px" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">제목</h5>
                            <p class="card-text">내용</p>
                            <a href="#" class="btn btn-primary">보러 가기</a>
                        </div>
                    </div>
                    <!-- 인기 상품2 -->
                    <div class="card col-3" style="width: 18rem;">
                        <img src="https://source.unsplash.com/random" style="height: 300px" class="card-img-top" alt="...">
                        <div class="card-body">
                            <h5 class="card-title">제목</h5>
                            <p class="card-text">내용</p>
                            <a href="#" class="btn btn-primary">보러 가기</a>
                        </div>
                    </div>
                </div>
            </div>
        </section>
        <!-- 셀프마진 -->
        <div style="background-color: powderblue; height: 50px"></div>
        <!-- 섹션2 -->
        <div class="blank-section d-flex align-items-center justify-content-center">
            <h1>섹션2</h1>
        </div>
        <!-- 셀프마진 -->
        <div style="background-color: powderblue; height: 50px"></div>
        <section style="background-color: powderblue;">
            <div class="container">
            <div class="row">
                <div class="card col-12">
                    <div class="row no-gutters">
                        <div class="col-md-4">
                            <div class="card-body">
                                <h5 class="card-title">섹션2 - Title</h5>
                                <p class="card-text">섹션2 - Content</p>
                                <p class="card-text"><small class="text-muted">섹션3 - Footer</small></p>
                            </div>
                        </div>
                        <div class="col-md-8">
                            <img src="https://source.unsplash.com/random" class="card-img" alt="..." style="height: 400px">
                        </div>
                    </div>
                </div>
            </div>
            </div>
        </section>
        <!-- 셀프마진 -->
        <div style="background-color: powderblue; height: 50px"></div>
        <!-- 마무리 -->
        <div class="blank-section d-flex align-items-center justify-content-center">
            <h1>마무리</h1>
        </div>
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
    .blank-section {
        height: 300px;
    }
</style>