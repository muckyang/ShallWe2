<template>
  <div>
    <div class="sub-footer">
        <div class="footer-blank"></div>
        <div>
            <a class="insta-a" href="#"><i class="fab fa-instagram insta"></i></a>
            <p class="text-white mt-3">Team. 욕망의 항아리</p>
        </div>
        <div class="d-flex flex-row justify-content-center">
            <div class="mr-5">
                <h6 class="text-white">Back end</h6>
                <h6 class="text-white">Kim</h6>
                <h6 class="text-white">Kim</h6>
                <h6 class="text-white">Song</h6>
            </div>
            <h1 class="my-board text-white">|</h1>
            <div class="ml-5">
                <h6 class="text-white">Front end</h6>
                <h6 class="text-white">Cha</h6>
                <h6 class="text-white">Lim</h6>
                <h6 class="text-white">Lee</h6>
            </div>
        </div>
    </div>
    <nav class="navbar navbar-light footer">
    </nav>
  </div>
</template>

<script>
export default {
    name: 'Footer',
}
</script>

<style>
    .sub-footer {
        height: 250px;
        background-color: #fec9c9;
    }
    .sub-footer-p {
        color: white;
    }
    .insta {
        color: white;
        font-size: 50px;
    }
    .footer-blank {
        height: 30px;
    }
    .my-board {
        font-stretch: extra-condensed;
    }
    .footer {
        background-color: #fec9c9;
    }
</style>