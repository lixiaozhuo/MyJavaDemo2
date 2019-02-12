<template>
    <div class="houseList">
        <div class="search">
            <div class="wrapper">
                <div class="fl search-txt">
                    <el-autocomplete v-model="rentSearch.keywords" :fetch-suggestions="querySearch"
                                     :trigger-on-focus="false"
                                     placeholder="如小区名、地铁站等" clearable size="small">
                        <template slot="prepend">关键词</template>
                    </el-autocomplete>
                    <el-button size="small" type="primary" icon="el-icon-search">搜索</el-button>
                </div>
                <div class="fr map">
                    <el-button size="small" type="primary" plain>地图找房</el-button>
                </div>
            </div>
        </div>
        <div class="main">
            <div class="wrapper">
                <div class="breadcrumb">
                    <el-breadcrumb separator-class="el-icon-arrow-right">
                        <el-breadcrumb-item :to="{ path: '/' }">首页</el-breadcrumb-item>
                        <el-breadcrumb-item>[<span>{{city.cn_name}}</span>]租房</el-breadcrumb-item>
                    </el-breadcrumb>
                </div>
                <div class="condition">
                    <el-tabs type="border-card">
                        <el-tab-pane label="全部租房">
                            <li>
                                <span class="condition-title">区域:</span>
                                <el-radio-group v-model="rentSearch.regionEnName" @change="changeCondition">
                                    <span><el-radio label="*">不限</el-radio></span>
                                    <span v-for="region in supportData.regions" :key="region.id">
                                        <el-radio :label="region.en_name">{{region.cn_name}}</el-radio>
                                    </span>
                                </el-radio-group>
                            </li>
                            <li>
                                <span class="condition-title">租金:</span>
                                <el-radio-group v-model="rentSearch.priceBlock" @change="changeCondition">
                                    <span><el-radio label="*">不限</el-radio></span>
                                    <span v-for="priceBlock in supportData.priceBlocks" :key="priceBlock.key">
                                        <el-radio :label="priceBlock.key">{{formatPriceBlock(priceBlock)}}</el-radio>
                                    </span>
                                </el-radio-group>
                            </li>
                            <li>
                                <span class="condition-title">面积:</span>
                                <el-radio-group v-model="rentSearch.areaBlock" @change="changeCondition">
                                    <span><el-radio label="*">不限</el-radio></span>
                                    <span v-for="areaBlock in supportData.areaBlocks" :key="areaBlock.key">
                                        <el-radio :label="areaBlock.key">{{formatAreaBlock(areaBlock)}}</el-radio>
                                    </span>
                                </el-radio-group>
                            </li>
                            <li>
                                <span class="condition-title">房型:</span>
                                <el-radio-group v-model="rentSearch.room" @change="changeCondition">
                                    <span><el-radio :label="0">不限</el-radio></span>
                                    <span><el-radio :label="1">一室</el-radio></span>
                                    <span><el-radio :label="2">二室</el-radio></span>
                                    <span><el-radio :label="3">三室</el-radio></span>
                                    <span><el-radio :label="4">四室</el-radio></span>
                                    <span><el-radio :label="5">五室及以上</el-radio></span>
                                </el-radio-group>
                            </li>
                            <li>
                                <span class="condition-title">朝向:</span>
                                <el-radio-group v-model="rentSearch.direction" @change="changeCondition">
                                    <span><el-radio :label="0">不限</el-radio></span>
                                    <span><el-radio :label="1">朝东</el-radio></span>
                                    <span><el-radio :label="2">朝南</el-radio></span>
                                    <span><el-radio :label="3">朝西</el-radio></span>
                                    <span><el-radio :label="4">朝北</el-radio></span>
                                </el-radio-group>
                            </li>
                            <li>
                                <span class="condition-title">租赁方式:</span>
                                <el-radio-group v-model="rentSearch.rentWay" @change="changeCondition">
                                    <span><el-radio :label="-1">不限</el-radio></span>
                                    <span><el-radio :label="0">整租</el-radio></span>
                                    <span><el-radio :label="1">合租</el-radio></span>
                                </el-radio-group>
                            </li>
                        </el-tab-pane>
                        <el-tab-pane label="地铁租房">暂未实现</el-tab-pane>
                    </el-tabs>
                    <div class="sort">
                        <span class="sort-title">排序:</span>
                        <el-radio-group v-model="rentSearch.orderBy" @change="changeCondition">
                            <el-radio-button label="lastUpdateTime">默认</el-radio-button>
                            <el-radio-button label="createTime">最新</el-radio-button>
                            <el-radio-button label="price">租金低</el-radio-button>
                            <el-radio-button label="distanceToSubway">地铁距离近</el-radio-button>
                        </el-radio-group>
                    </div>
                </div>
                <div class="houseData">
                    <div class="houseDataTitle">
                        <div class="fl">
                            <span>{{city.cn_name}}</span>
                            <span>房源</span>
                        </div>
                        <div class="fr">
                            <span>共有</span>
                            <span class="houseCount">{{houseList.total}}</span>
                            <span>套在租房源</span>
                        </div>
                    </div>
                    <div class="houseDataList">
                        <div class="houseInfo" @click="houseDetail(house.id)" v-for="house in houseList.data" :key="house.id">
                            <div class="houseImage">
                                <!--<img width="100%" height="100%" :src="house.cover"/>-->
                                <img width="100%" height="100%" src="https://image1.ljcdn.com/110000-inspection/348cbea6-b0f5-441e-adc6-9feb127f6031.jpg.780x439.jpg"/>
                            </div>
                            <div class="houseBase">
                                <div class="houseTitle">
                                    <span> {{house.title}}</span>
                                </div>
                                <div class="houseDetail">
                                    <div class="houseSimple">
                                        <div style="font-size: 18px;font-weight: bold;">
                                            <span>{{house.district}}&nbsp;
                                                {{house.room}}室{{house.parlour}}厅&nbsp;
                                                {{house.area}}平米&nbsp;
                                                朝向{{formatDirection(house.direction)}}</span>
                                        </div>
                                        <div style="font-size: 15px;color: #C0C4CC;">
                                            <span>{{house.floor}}楼(共{{house.total_floor}}层)&nbsp;&nbsp;&nbsp;
                                                /
                                                &nbsp;&nbsp;&nbsp;{{house.build_year}}年底</span>
                                        </div>
                                    </div>
                                    <div class="housePrice">
                                        <div style="color:red;">
                                            <span style="font-size: 25px;font-weight: bold;">{{house.price}}&nbsp;</span>
                                            <span>元/月</span>
                                        </div>
                                        <div style="font-size: 15px;color: #C0C4CC;">
                                            <span>{{formatData(house.last_update_time)}}&nbsp;更新</span>
                                        </div>
                                    </div>
                                    <div class="houseWatchTime">
                                        <div>
                                            <span style="font-size: 18px;font-weight: bold;">{{house.watch_times}}&nbsp;</span>
                                            <span>人</span>
                                        </div>
                                        <div style="font-size: 15px;color: #C0C4CC;">
                                            <span>看过此房</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="houseTag">
                                    <el-tag size="small" type="info">距离地铁
                                        {{house.house_detail.subway_line_name}}
                                        {{house.house_detail.subway_station_name}}站
                                        {{house.distance_to_subway >=0 ? house.distance_to_subway+'米':'较近' }}
                                    </el-tag>
                                    <span v-for="tag in house.tags" :key="tag.key">
                                        <el-tag size="small" type="warning">{{tag}}</el-tag>
                                     </span>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="housePage">
                        <el-pagination
                                background
                                layout="prev, pager, next,jumper"
                                :page-size="rentSearch.size"
                                :total="houseList.total"
                                @current-change="changePage">
                        </el-pagination>
                    </div>
                </div>
            </div>
        </div>
    </div>

</template>

<script>
    import Cookies from 'js-cookie'

    export default {
        name: "houseList",
        data() {
            return {
                supportData: {
                    regions: [],
                    priceBlocks: [],
                    areaBlocks: [],
                },

                city: {
                    belong_to: "",
                    en_name: "",
                    cn_name: "",
                    level: "",
                    map_longitude: "",
                    map_latitude: "",
                },
                rentSearch: {
                    cityEnName: "",
                    regionEnName: "*",
                    priceBlock: "*",
                    areaBlock: "*",
                    room: 0,
                    direction: 0,
                    keywords: "",
                    rentWay: -1,
                    orderBy: "lastUpdateTime",
                    orderDirection: "desc",
                    start: 0,
                    size: 5,
                },
                houseList: {
                    data: [],
                    total: 0,
                },
            };
        },
        methods: {
            querySearch(queryString, cb) {
                var results = [{"value": "提示文字"}];
                cb(results);
            },
            changePage(val) {
                this.rentSearch.start = val;
                this.changeCondition();
            },
            changeCondition() {
                this.$axios.get(this.HOST + "api/rent/house", {
                    params: {
                        cityEnName: this.rentSearch.cityEnName,
                        regionEnName: this.rentSearch.regionEnName,
                        priceBlock: this.rentSearch.priceBlock,
                        areaBlock: this.rentSearch.areaBlock,
                        room: this.rentSearch.room,
                        direction: this.rentSearch.direction,
                        keywords: this.rentSearch.keywords,
                        rentWay: this.rentSearch.rentWay,
                        orderBy: this.rentSearch.orderBy,
                        orderDirection: this.rentSearch.orderDirection,
                        start: this.rentSearch.start,
                        size: this.rentSearch.size,
                    }
                }).then(res => {
                    var response = res.data;
                    if (response.code === 200) {
                        this.houseList.data = response.data;
                        this.houseList.total = response.total;
                        console.log(this.houseList.data)
                    }
                }).catch(error => {
                    alert(error);
                });
            },
            formatPriceBlock(priceBlock) {
                var min = priceBlock.min;
                var max = priceBlock.max;
                if (min < 0) {
                    return max + "元以下";
                }
                if (max < 0) {
                    return min + "元以上";
                }
                return min + "-" + max + "元";
            }
            ,
            formatAreaBlock(areaBlock) {
                var min = areaBlock.min;
                var max = areaBlock.max;
                if (min < 0) {
                    return max + "平以下";
                }
                if (max < 0) {
                    return min + "平以上";
                }
                return min + "-" + max + "平";
            },
            //   时间格式化
            formatData(time) {
                var date = new Date(time);
                var year = date.getFullYear();
                var month = date.getMonth() + 1 < 10 ? "0" + (date.getMonth() + 1) : date.getMonth() + 1;
                var day = date.getDate() < 10 ? "0" + date.getDate() : date.getDate();
                // 拼接
                return year + "." + month + "." + day;
            },
            //   时间格式化
            formatDirection(direction) {
               switch(direction){
                   case 1:
                       return "东";
                   case 2:
                       return "西";
                   case 3:
                       return "南";
                   case 4:
                       return "北";
               }
            },
            houseDetail(houseId){
                this.$router.push({ name: 'houseDetail'});
            }
        },
        created() {
            //获取当前选择的城市
            const city = Cookies.get('city');
            if (city === undefined || city === "") {
                alert("请先选择城市");
                //跳转到选择城市界面
                this.$router.replace({name: 'changeCity'});
            } else {
                this.city = JSON.parse(city);
                this.rentSearch.cityEnName = this.city.en_name;
            }

            //补全数据
            //获取城市所有支持区域
            this.$axios.get(this.HOST + "api/address/support/regions", {
                params: {
                    city_name: this.city.en_name,
                }
            }).then(res => {
                var response = res.data;
                if (response.code === 200) {
                    this.supportData.regions = response.data;
                }
            }).catch(error => {
                alert(error);
            });
            //面积数据块
            this.$axios.get(this.HOST + "api/search/support/block/area").then(res => {
                var response = res.data;
                if (response.code === 200) {
                    this.supportData.areaBlocks = response.data;
                }
            }).catch(error => {
                alert(error);
            });
            //价格数据块
            this.$axios.get(this.HOST + "api/search/support/block/price").then(res => {
                var response = res.data;
                if (response.code === 200) {
                    this.supportData.priceBlocks = response.data;
                }
            }).catch(error => {
                alert(error);
            });
            //请求数据
            this.changeCondition();
        }
        ,

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

    .houseList {
        clear: both;
        width: 100%;
        line-height: 1000px;
    }

    .search {
        height: 40px;
        line-height: 40px;
        width: 100%;
        clear: both;
        margin-top: 5px;
        margin-bottom: 10px;
    }

    .search .wrapper {
        width: 1000px;
        margin: 0 auto;
    }

    .search-txt .el-autocomplete {
        width: 500px;
    }

    .search-txt .el-button {
        margin-left: 10px;
    }

    .main {
        width: 100%;
        background-color: #F2F6FC;
        clear: both;
    }

    .main .wrapper {
        width: 1000px;
        margin: 0 auto;
    }

    .breadcrumb .el-breadcrumb {
        padding-top: 15px;
        padding-bottom: 15px;
    }

    .condition {
        line-height: 40px;
    }

    .condition .condition-title {
        margin-right: 40px;
    }

    .condition li {
        line-height: 20px;
        margin-bottom: 15px;
    }

    .condition .el-tabs .el-tab-pane .el-radio-group span {
        margin-left: 30px;
    }

    .condition .sort {
        height: 50px;
        line-height: 50px;
        width: 100%;
        background-color: #fff;
        border: 1px solid #DCDFE6;
    }

    .condition .sort .sort-title {
        height: 40px;
        width: 60px;
        margin-left: 20px;
        display: inline-block;
    }

    .houseData {
        margin-top: 20px;
        background-color: #fff;
    }

    .houseData .houseDataTitle {
        height: 40px;
        line-height: 40px;
        font-size: 15px;
        border: 1px solid #DCDFE6;
    }

    .houseData .houseDataTitle .fl {
        color: #7dcfa0;
        margin-left: 40px;
    }

    .houseData .houseDataTitle .fr {
        margin-right: 40px;
    }

    .houseData .houseDataTitle .fr .houseCount {
        color: #F56C6C;
    }

    .houseData .houseDataList {
        height: 1000px;
        line-height: 1000px;
        background-color: #fff;
        border-right: 1px solid #DCDFE6;
        border-left: 1px solid #DCDFE6;
        border-top: 1px solid #DCDFE6;
    }

    .houseData .houseDataList .houseInfo {
        height: 200px;
        line-height: 200px;
        cursor: pointer;
    }

    .houseData .houseDataList .houseInfo .houseImage {
        height: 160px;
        width: 180px;
        line-height: 160px;
        margin: 20px;
        float: left;
    }

    .houseData .houseDataList .houseInfo .houseBase {
        height: 160px;
        width: 700px;
        line-height: 160px;
        margin: 20px;
        float: left;
    }

    .houseData .houseDataList .houseInfo .houseBase .houseTitle {
        height: 25%;
        line-height: 40px;
        font-weight: bold;
        color: #409EFF;
        font-size: 22px;
    }

    .houseData .houseDataList .houseInfo .houseBase .houseDetail {
        height: 50%;
        line-height: 80px;
    }

    .houseData .houseDataList .houseInfo .houseBase .houseDetail div {
        height: 50%;
        line-height: 40px;

    }


    .houseData .houseDataList .houseInfo .houseBase .houseDetail .houseSimple {
        height: 100%;
        width: 50%;
        float: left;
    }


    .houseData .houseDataList .houseInfo .houseBase .houseDetail .housePrice {
        height: 100%;
        width: 20%;
        float: left;
        text-align: right;
    }

    .houseData .houseDataList .houseInfo .houseBase .houseDetail .houseWatchTime {
        height: 100%;
        width: 30%;
        float: left;
        text-align: center;
    }


    .houseData .houseDataList .houseInfo .houseBase .houseTag {
        height: 25%;
        line-height: 40px;
    }

    .houseData .houseDataList .houseInfo .houseBase .houseTag .el-tag {
        margin-right: 10px;
    }

    .houseData .housePage {
        height: 60px;
        line-height: 60px;
        text-align: right;
        border-right: 1px solid #DCDFE6;
        border-left: 1px solid #DCDFE6;
    }

    .houseData .housePage .el-pagination {
        margin-right: 40px;
    }


</style>