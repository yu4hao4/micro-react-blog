import request from './request'
// const admin = '/admin'
const admin = ''

// 获得首页文章
export function getIndexArticle() {
  return request({
    url: admin+'/getIndexArticle',
    method: 'get'
  })
}

