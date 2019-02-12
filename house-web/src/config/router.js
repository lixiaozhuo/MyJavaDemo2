//引入VueRouter:动态路由
import Vue from 'vue'
import VueRouter from 'vue-router'
import NotFound from '@/container/error/404'
import index from '@/container/index'
import changeCity from '@/container/house/changeCity'
import houseList from '@/container/house/houseList'
import houseDetail from '@/container/house/houseDetail'
import adminLogin from '@/container/admin/adminLogin'
import userLogin from '@/container/user/userLogin'

Vue.use(VueRouter);

// 创建路由
export default new VueRouter({
        linkActiveClass: "active",
        linkExactActiveClass: "currentActive",
        routes: [
            {
                path: "*",
                component: NotFound
            },
            {
                path: "/",
                // 重定向
                redirect: "/house/changeCity"
            },
            {
                path: "/house",
                name: "house", // 跳转
                component: index,
                children: [
                    {
                        path: "changeCity",
                        name: "changeCity",
                        component: changeCity,
                    },
                    {
                        path: "houseList",
                        name: "houseList",
                        component: houseList,
                    },
                    {
                        path: "houseDetail",
                        name: "houseDetail",
                        component: houseDetail,
                    },
                ]
            },
            {
                path: "/adminLogin",
                name: "adminLogin", // 跳转
                component: adminLogin
            },
            {
                path: "/userLogin",
                name: "userLogin", // 跳转
                component: userLogin
            },
        ]
})