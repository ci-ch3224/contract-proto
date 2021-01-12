import Vue from 'vue'
import axios, { AxiosStatic } from 'axios'

Vue.prototype.$axios = axios

export default axios.create({
  baseURL: 'http://localhost:8080',
  headers: {
    'Access-Control-Allow-Origin': '*'
  }
})

declare module 'vue/types/vue' {
  interface Vue {
    $axios: AxiosStatic;
  }
}
