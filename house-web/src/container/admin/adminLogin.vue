<template>
    <div class="login">
        <el-form ref="loginForm" :model="loginForm" :rules="loginRules" label-position="left" label-width="0px"
                 class="login-form">
            <h3 class="title">house后台管理系统</h3>
            <el-form-item prop="username">
                <el-input v-model="loginForm.username" type="text" auto-complete="off" placeholder="账号"/>
            </el-form-item>
            <el-form-item prop="password">
                <el-input v-model="loginForm.password" type="password" auto-complete="off" placeholder="密码"/>
            </el-form-item>
            <el-checkbox v-model="loginForm.rememberMe" style="margin:0 0 25px 0;">记住密码</el-checkbox>
            <el-form-item style="width:100%;">
                <el-button :loading="loading" size="medium" type="primary" style="width:100%;"
                           @click.native.prevent="handleLogin">
                    <span v-if="!loading">登 录</span>
                    <span v-else>登 录 中...</span>
                </el-button>
            </el-form-item>
            <p class="login-tip">系统默认用户名：admin，密码：123456</p>
        </el-form>
    </div>
</template>

<script>
    import {md5} from '@/utils/md5'
    import Cookies from 'js-cookie'

    export default {
        name: "adminLogin",
        data() {
            return {
                md5Pwd: '',
                loginForm: {
                    username: '',
                    password: '',
                    rememberMe: false
                },
                loginRules: {
                    username: [{required: true, trigger: 'blur', message: '用户名不能为空'}],
                    password: [{required: true, trigger: 'blur', message: '密码不能为空'}]
                },
                loading: false,
                redirect: undefined
            }
        },

        created() {
            //从cookie获取记住的账号信息
            this.getCookie()
        },

        methods: {
            getCookie() {
                //从cookie中获取账号信息
                const username = Cookies.get('username');
                const password = Cookies.get('password');
                const rememberMe = Cookies.get('rememberMe');
                // 保存cookie里面的加密后的密码
                this.md5Pwd = password === undefined ? '' : password;
                this.loginForm = {
                    username: username === undefined ? '' : username,
                    password: this.md5Pwd,
                    rememberMe: rememberMe === undefined ? false : Boolean(rememberMe)
                }
            },
            handleLogin() {
                this.$refs.loginForm.validate(valid => {
                    //获取加密后的密码
                    let pass = this.loginForm.password;
                    if (pass !== this.md5Pwd) {
                        pass = md5(pass)
                    }
                    //用户登录信息
                    const user = {
                        username: this.loginForm.username,
                        password: pass,
                        rememberMe: this.loginForm.rememberMe
                    };
                    if (valid) {
                        this.loading = true;
                        //记住密码,将信息存入cookie
                        if (user.rememberMe) {
                            Cookies.set('username', user.username, {expires: 1});
                            Cookies.set('password', user.password, {expires: 1});
                            Cookies.set('rememberMe', user.rememberMe, {expires: 1})
                        } else {
                            Cookies.remove('username');
                            Cookies.remove('password');
                            Cookies.remove('rememberMe')
                        }
                    } else {
                        return false
                    }
                })
            }
        }

        /*watch: {
            $route: {
                handler: function(route) {
                    this.redirect = route.query && route.query.redirect
                },
                immediate: true
            }
        },*/
    }
</script>

<style scoped>
    .login {
        background: url("../../assets/image/admin/admin-login-bg.jpg") no-repeat center top #222;
        display: flex;
        justify-content: center;
        align-items: center;
        height: 100%;
    }

    .login-form {
        border-radius: 6px;
        background: #ffffff;
        width: 365px;
        padding: 25px 25px 5px 25px;
    }

    .title {
        margin: 0 auto 40px auto;
        text-align: center;
        color: #707070;
    }

    input {
        height: 38px;
    }

    .login-tip {
        font-size: 13px;
        text-align: center;
        color: #bfbfbf;
    }
</style>