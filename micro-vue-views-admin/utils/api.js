import request from './request'
const fileManagement = '/file-management'
const admin = '/admin'

// 获得首页文章
export function getIndexArticle() {
  return request({
    url: admin+'/getIndexArticle',
    method: 'get'
  })
}

// 更新文章
export function updateArticle(query) {
  return request({
    url: admin+'/updateArticle',
    method: 'post',
    data:query
  })
}

export function get(query) {
  return request({
    url: 'getInfo',
    method: 'post',
    data: query
  })
}

// 文件上传（图片）
export function fileUpload(query) {
  return request({
    url: fileManagement+'/fileUpload',
    method: 'post',
    data: query
  })
}
