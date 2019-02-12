import Vue from 'vue'
import App from './App.vue'
import './plugins/element.js'
import router from "./config/router.js"
import axios from "./config/axios.js"
Vue.prototype.$axios = axios;

new Vue({
  render: h => h(App),
  router
}).$mount('#app')