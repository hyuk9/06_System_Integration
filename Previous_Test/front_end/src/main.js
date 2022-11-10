import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

// bootstrap 5.x npm import
import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap/dist/js/bootstrap.bundle.js'

// bootstrap-vue import
import { BootstrapVue } from 'bootstrap-vue'
import 'bootstrap-vue/dist/bootstrap-vue.css'

// bootstrap vue모듈 사용
Vue.use(BootstrapVue)

createApp(App).use(store).use(router).mount('#app')

