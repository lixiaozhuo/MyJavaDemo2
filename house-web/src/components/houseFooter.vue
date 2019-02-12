<template>
    <div class="houseFooter">
        <div class="wrapper">
            <div class="title">
                <div class="fl">
                    <ul>
                        <router-link tag="li" to="">更多</router-link>
                        <router-link tag="li" to="">联系我</router-link>
                        <router-link tag="li" :to="{name:'adminLogin'}">管理员登录</router-link>
                    </ul>
                </div>
                <div class="fr">官方客服 xxxx-xxxx-xxxx</div>
            </div>
        </div>
        <div class="bottom">
            <div class="copyright">Copyright &copy; 2019. lxz All rights reserved.</div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "houseFooter",
    }

</script>

<style scoped>
    ul, li {
        list-style: none;
        margin: 0;
        padding: 0;
    }

    .fl {
        float: left;
    }

    .fr {
        float: right;
    }

    .houseFooter {
        height: 150px;
        line-height: 150px;
        width: 100%;
        background: #222;
        clear: both;
    }

    .houseFooter  .wrapper {
        width: 1000px;
        margin: 0 auto;
    }

    .houseFooter .wrapper .title {
         height: 75px;
         line-height: 75px;
         border-bottom: 1px solid #393939;
     }

    .houseFooter .wrapper .title .fl li{
        font-size: 14px;
        color: #fff;
        margin-right: 15px;
        float: left;
        cursor: pointer;
    }

    .houseFooter .wrapper .title .fr {
        font-size: 14px;
        color: #7dcfa0;
    }

    .houseFooter .bottom {
        padding-top: 20px;
    }

    .houseFooter .bottom .copyright {
        color: #888c8e;
        font-size: 12px;
        height: 20px;
        line-height: 20px;
        margin-left:40%;
        margin-right:40%;
    }
</style>