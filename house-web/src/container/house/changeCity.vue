<template>
    <div class="city-change">
        <div class="wrapper">
            <div class="title">
                <span class="selectCity">选择城市</span>
                <span class="bigCity">热门城市:</span>
                <span class="city" @click="gotoCityHouse('bj')" title="北京租房">北京</span>
                <span class="city" @click="gotoCityHouse('sh')" title="上海租房">上海</span>
                <span class="city" @click="gotoCityHouse('gz')" title="广州租房">广州</span>
                <span class="city" @click="gotoCityHouse('sz')" title="深圳租房">深圳</span>
            </div>
            <div class="main">
                <div class="citys-l fl">
                    <ul>
                        <li>
                            <span class="city-title">B</span>
                            <span class="city" @click="gotoCityHouse('bj')" title="北京租房">北京</span>
                        </li>
                        <li>
                            <span class="city-title">C</span>
                            <span class="city" @click="gotoCityHouse('cd')" title="成都租房">成都</span>
                            <span class="city" @click="gotoCityHouse('cq')" title="重庆租房">重庆</span>
                            <span class="city" @click="gotoCityHouse('cs')" title="长沙租房">长沙</span>
                        </li>
                        <li>
                            <span class="city-title">D</span>
                            <span class="city" @click="gotoCityHouse('dl')" title="大连租房">大连</span>
                            <span class="city" @click="gotoCityHouse('dw')" title="东莞租房">东莞</span>
                        </li>
                        <li>
                            <span class="city-title">F</span>
                            <span class="city" @click="gotoCityHouse('fs')" title="佛山租房">佛山</span>
                        </li>
                        <li>
                            <span class="city-title">G</span>
                            <span class="city" @click="gotoCityHouse('gz')" title="广州租房">广州</span>
                        </li>
                        <li>
                            <span class="city-title">H</span>
                            <span class="city" @click="gotoCityHouse('hz')" title="杭州租房">杭州</span>
                            <span class="city" @click="gotoCityHouse('hk')" title="海口租房">海口</span>
                            <span class="city" @click="gotoCityHouse('hf')" title="合肥租房">合肥</span>
                        </li>
                    </ul>
                </div>
                <div class="citys-r fr">
                    <ul>
                        <li>
                            <span class="city-title">L</span>
                            <span class="city" @click="gotoCityHouse('ls')" title="陵水租房">陵水</span>
                            <span class="city" @click="gotoCityHouse('lf')" title="廊坊租房">廊坊</span>
                        </li>
                        <li>
                            <span class="city-title">N</span>
                            <span class="city" @click="gotoCityHouse('nj')" title="南京租房">南京</span>
                        </li>
                        <li>
                            <span class="city-title">Q</span>
                            <span class="city" @click="gotoCityHouse('qd')" title="青岛租房">青岛</span>
                            <span class="city" @click="gotoCityHouse('qh')" title="琼海租房">琼海</span>
                        </li>
                        <li class="clear">
                            <span class="city-title">S</span>
                            <span class="city" @click="gotoCityHouse('sh')" title="上海租房">上海</span>
                            <span class="city" @click="gotoCityHouse('sz')" title="深圳租房">深圳</span>
                            <span class="city" @click="gotoCityHouse('su')" title="苏州租房">苏州</span>
                            <span class="city" @click="gotoCityHouse('sjz')" title="石家庄租房">石家庄</span>
                            <span class="city" @click="gotoCityHouse('sy')" title="沈阳租房">沈阳</span>
                            <span class="city" @click="gotoCityHouse('sy')" title="三亚租房">三亚</span>
                        </li>
                        <li>
                            <span class="city-title">T</span>
                            <span class="city" @click="gotoCityHouse('tj')" title="天津租房">天津</span>
                            <span class="city" @click="gotoCityHouse('ty')" title="太原租房">太原</span>
                        </li>
                        <li>
                            <span class="city-title">W</span>
                            <span class="city" @click="gotoCityHouse('wh')" title="武汉租房">武汉</span>
                            <span class="city" @click="gotoCityHouse('wx')" title="无锡租房">无锡</span>
                            <span class="city" @click="gotoCityHouse('wc')" title="文昌租房">文昌</span>
                            <span class="city" @click="gotoCityHouse('wn')" title="万宁租房">万宁</span>
                        </li>
                        <li>
                            <span class="city-title">X</span>
                            <span class="city" @click="gotoCityHouse('xm')" title="厦门租房">厦门</span>
                            <span class="city" @click="gotoCityHouse('xa')" title="西安租房">西安</span>
                            <span class="city" @click="gotoCityHouse('xsbn')" title="西双版纳租房">西双版纳</span>
                        </li>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</template>

<script>
    import Cookies from 'js-cookie'
    export default {
        name: "changeCity",
        methods: {
            gotoCityHouse(value) {
                //获取选定的城市信息
                this.$axios.get(this.HOST + "api/address/support/city", {
                    params: {
                        city_name: value,
                    }
                }).then(res => {
                    var response = res.data;
                    if(response.code ===200){
                        var city = response.data;
                        //将选定的城市信息写入cookie
                        Cookies.remove('city');
                        Cookies.set('city',city);
                        //跳转到对应城市的页面
                        this.$router.replace({ name: 'houseList'});
                    }else{
                        alert("当前城市未开通服务");
                    }
                }).catch(error => {
                    alert(error);
                })
            }
        }
    }
</script>

<style scoped>
    .fl {
        float: left;
    }

    .fr {
        float: right;
    }

    ul, li {
        list-style: none;
        margin: 0;
        padding: 0;
    }

    .city-change .city {
        margin-left: 15px;
        color: #333333;
        display: inline-block;
        cursor: pointer;
    }

    .city-change .city:hover {
        color: #00b064
    }

    .city-change {
        height: 425px;
        width: 100%;
        background: #FFFFFF;
        clear: both;
    }

    .city-change .wrapper {
        width: 1000px;
        margin: 0 auto;
    }

    .city-change .title {
        height: 50px;
        line-height: 50px;
        border-bottom: 1px solid #eeeeee;
    }

    .city-change .title .selectCity {
        font-size: 22px;
        color: #394043;
        font-weight: bold;
        margin-right: 9px;
    }

    .city-change .title .bigCity {
        font-size: 14px;
        font-weight: normal;
        color: #999999;
        margin-right: 9px;
    }

    .city-change .title .city {
        font-size: 14px;
        font-weight: normal;
        color: #394043;
        margin-left: 7px;
    }

    .city-change .title .city:hover {
        color: #00b064;
    }

    .city-change .main {
        height: 50px;
        line-height: 50px;
        font-size: 14px;
    }

    .city-change .citys-l, .city-change .citys-r {
        width: 50%;
        height: 100%;
    }

    .city-change .city-title {
        font-weight: normal;
        margin-right: 9px;
        color: #999999;
    }
</style>