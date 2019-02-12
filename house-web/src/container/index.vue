<template>
    <div>
        <div class="houseHeader">
            <div class="wrapper ">
                <div class="fl">
                    <router-link class="menu fl" tag="li" :to="{name:'changeCity'}" replace>首页</router-link>
                    <router-link class="menu fl"  tag="li" :to="{name:'houseList'}" replace>租房</router-link>
                    <router-link class="menu fl" tag="li" :to="{name:'userLogin'}" >个人中心</router-link>
                </div>
                <div class="fr login">
                    <router-link class="register" tag="li" :to="{name:'userLogin'}">免注册登录</router-link>
                </div>
            </div>
        </div>
        <router-view/>
        <HouseFooter class="footer"/>
    </div>
</template>


<script>
    import HouseFooter from "@/components/houseFooter"

    export default {
        name: "index",
        components: {
            HouseFooter
        },
        data() {
            return {};
        },
        methods: {
        },

    }
</script>

<style scoped>
    ul, li {
        list-style: none;
    }

    .fl {
        float: left;
    }

    .fr {
        float: right;
    }

    .houseHeader {
        height: 60px;
        line-height: 60px;
        background: #0054a5;
        clear: both;
    }

    .houseHeader .wrapper {
        width: 1000px;
        margin: 0 auto;
    }

    .houseHeader .menu{
        color : #fff;
        display: inline-block;
        width: 70px;
        text-align: center;
        cursor: pointer;
    }

    .houseHeader .login li {
        font-size: 16px;
        color: #fff;
        float: left;
        cursor: pointer;
    }

    .active{
        background-color:#008ea5;
    }

</style>