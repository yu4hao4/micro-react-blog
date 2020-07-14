import request from './request'

// 获得用户
export function getArticle(query) {
  return request({
    url: '/admin/getArticle',
    method: 'post',
    data: query
  })
}
