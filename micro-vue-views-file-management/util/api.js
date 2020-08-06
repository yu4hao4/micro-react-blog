import request from './request'
// const fileManagement = '/file-management'
const fileManagement = ''

// 获得图片数据
export function getImages(query) {
  return request({
    url: fileManagement+'/getImages',
    method: 'post',
    data:query
  })
}

// 更新文章
export function updateImage(query) {
  return request({
    url: fileManagement+'/updateImage',
    method: 'post',
    data:query
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
