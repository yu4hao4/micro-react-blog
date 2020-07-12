import axios from 'axios'
import { message } from 'ant-design-vue'
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
    //加密
    // if (config.method === 'post'){
    //   let info = config.data;
    //   console.log(JSON.stringify(info))
    //   config.data = {encrypt:Encrypt(JSON.stringify(info))};
    //   console.log(config.data)
    // }

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
    message.error({message: resp.data.msg});
  }else if (resp.data.status === 200 && resp.data.msg) {
    message.success({message: resp.data.msg,duration:500});
  }else if(resp.data.status === 303){
    localStorage.removeItem("admin")
    window.location.href = "/admin/login"
  }
  return resp;
}, error => {
  console.log('err' + error) // for debug
  // Message({
  //   message: error.message,
  //   type: 'error',
  //   duration: 5 * 1000
  // })
})

export default service
