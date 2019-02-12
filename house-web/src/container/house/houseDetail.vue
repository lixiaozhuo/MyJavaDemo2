<template>
    <div class="houseDetail">
        <div class="wrapper">
            <div class="breadcrumb">
                <el-breadcrumb separator-class="el-icon-arrow-right">
                    <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                    <el-breadcrumb-item :to="{ name: 'houseList' }"><!--[<span>{{city.cn_name}}</span>]-->租房
                    </el-breadcrumb-item>
                    <el-breadcrumb-item>房间详情</el-breadcrumb-item>
                </el-breadcrumb>
            </div>
            <div class="houseAbove">
                <div class="houseTitle">
                    <div class="fl">
                        <div style="height:50%; line-height:24px;font-weight: bold;  color: #409EFF; font-size: 22px;">
                            富力城 国贸CBD 时尚休闲 商务办公
                        </div>
                        <div style="height:50%;line-height:24px; text-align:start;font-size: 15px;color: #C0C4CC;">
                            这个经济人很懒,没写核心卖点
                        </div>
                    </div>
                    <div class="fr">
                        <el-button type="primary" plain>预约看房</el-button>
                    </div>

                </div>
                <div class="houseInfo">
                    <div class="image fl">
                        <el-carousel height="300px" indicator-position="none">
                            <el-carousel-item v-for="item in 4" :key="item">
                                <h3>{{ item }}</h3>
                            </el-carousel-item>
                        </el-carousel>
                    </div>
                    <div class="houseData fr">
                        <div class="housePrice">
                            <span style="font-size: 25px;font-weight: bold;">5700</span>
                            <span>元/月</span>
                        </div>
                        <div class="houseSimple">
                            <div class="fl" style="width: 50%">
                                <ul>
                                    <li>
                                        <span class="span1">面积:</span>
                                        <span class="span2">52平米</span>
                                    </li>
                                    <li>
                                        <span class="span1">楼层:</span>
                                        <span class="span2">7层(共20层)</span>
                                    </li>
                                </ul>
                            </div>
                            <div class="fr" style="width: 50%">
                                <ul>
                                    <li>
                                        <span class="span1">房屋户型:</span>
                                        <span class="span2">1室1厅0卫 整租</span>
                                    </li>
                                    <li>
                                        <span class="span1">房屋朝向:</span>
                                        <span class="span2">朝南</span>
                                    </li>
                                </ul>
                            </div>
                            <ul>
                                <li>
                                    <span class="span1">地铁:</span>
                                    <span class="span2">距地铁13号线 北苑站1055米</span>
                                </li>
                                <li>
                                    <span class="span1">小区:</span>
                                    <span class="span2">将府锦苑</span>
                                </li>
                                <li>
                                    <span class="span1">位置:</span>
                                    <span class="span2">海淀区</span>
                                </li>
                                <li>
                                    <span class="span1">发布时间:</span>
                                    <span class="span2">2017-09-25</span>
                                </li>
                            </ul>
                        </div>
                        <div class="houseAdmin">
                            <div class="houseAdminCover fl">
                                <img width="100%" height="100%" src="https://image1.ljcdn.com/110000-inspection/348cbea6-b0f5-441e-adc6-9feb127f6031.jpg.780x439.jpg"/>
                            </div>
                            <div class="houseAdminDetail fl">
                                <ul>
                                    <li><span style="font-size: 16px;font-weight: bold; margin-right:10px;">admin</span>
                                        <el-tag type="info" size="mini">经济人</el-tag>
                                    </li>
                                    <li><span style="font-size: 15px;color: #C0C4CC;">本房被看:  0次</span></li>
                                    <li><span style="font-weight: bold;  color: red; font-size: 18px;">18730291527</span></li>
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
            <div class="houseBelow">

            </div>
        </div>
    </div>
</template>

<script>
    export default {
        name: "houseDetail",
        data() {
            return {};
        },
        method: {},
        created() {

        }

    }
</script>

<style scoped>
    ul, li {
        list-style: none;
        padding: 0;
        margin:0;
    }

    .fl {
        float: left;
    }

    .fr {
        float: right;
    }

    .span1{
        font-size: 15px;color: #C0C4CC;
    }
    .span2{
        font-size: 15px;
    }

    .el-carousel__item:nth-child(2n) {
        background-color: #99a9bf;
    }

    .el-carousel__item:nth-child(2n+1) {
        background-color: #d3dce6;
    }

    .houseDetail {
        clear: both;
        width: 100%;
        height: 1000px;
        background-color: #F2F6FC;
    }

    .wrapper {
        width: 1000px;
        margin: 0 auto;
    }

    .breadcrumb .el-breadcrumb {
        padding-top: 15px;
        padding-bottom: 15px;
    }

    .houseAbove {
        height: 800px;
        background-color: #fff;
        padding: 20px;
    }

    .houseAbove .houseTitle {
        height: 60px;
    }

    .houseAbove .houseTitle .fl {
        height: 100%;
        width: 80%;
    }

    .houseAbove .houseTitle .fr {
        height: 100%;
        line-height: 60px;
        width: 20%;
        text-align: center;
    }

    .houseAbove .houseInfo {
        height: 300px;
        margin-top: 40px;
    }

    .houseAbove .houseInfo .image {
        height: 300px;
        width: 50%;
    }

    .houseAbove .houseInfo .houseData {
        height: 300px;
        width: 45%;
    }

    .houseAbove .houseInfo .houseData .housePrice {
        color:red;
        height: 10%;
    }

    .houseAbove .houseInfo .houseData .houseSimple {
        height: 60%;
        margin-top: 20px;
    }

    .houseAbove .houseInfo .houseData .houseSimple span {
        font-size: 15px;
        margin-right: 20px;
    }

    .houseAbove .houseInfo .houseData .houseAdmin {
        height: 25%;
    }

    .houseAbove .houseInfo .houseData .houseAdmin .houseAdminCover{
        height:100%;
        width:75px;
        margin-right:20px;
    }
    .houseAbove .houseInfo .houseData .houseAdmin .houseAdminDetail{
        height:100%;
        width:70%;
    }

    .houseAbove .houseInfo .houseData .houseAdmin .houseAdminDetail li{
        margin-bottom: 5px;
    }



</style>