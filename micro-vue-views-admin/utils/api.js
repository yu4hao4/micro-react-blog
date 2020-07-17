import request from './request'

// 获得用户
export function getArticle(query) {
  return request({
    url: '/admin/getArticle',
    method: 'post',
    data: query
  })
}

export function get(query) {
  return request({
    url: 'getInfo',
    method: 'post',
    data: query
  })
}

// 获得所有类型
export function fileUpload(query) {
  return request({
    url: '/fileUpload',
    method: 'post',
    data: query
  })
}
