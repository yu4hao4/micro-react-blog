import axios from 'axios'
import { Message } from 'view-design'
// create an axios instance
axios.defaults.timeout = 50000;
// axios.defaults.withCredentials = true
const service = axios.create({
  baseURL: '/api', // url = base url + request url
  // withCredentials: true, // send cookies when cross-domain requests
  timeout: 20000 // request timeout
})

// 添加请求拦截器，在请求头中加token
service.interceptors.request.use(
  config => {
    return config
  },
  error => {
    console.log(error) // for debug
    return Promise.reject(error)
  }
)

// response interceptor
service.interceptors.response.use(resp => {
  if (resp.status && resp.status === 200 && resp.data.status === 500) {
    Message.error(resp.data.msg);
  }else if (resp.data.status === 200 && resp.data.msg) {
    Message.success(resp.data.msg);
  }else if(resp.data.status === 303){
    localStorage.removeItem("admin")
    window.location.href = "/admin/login"
  }
  return resp;
}, error => {
  console.log('err' + error) // for debug
})

export default service
