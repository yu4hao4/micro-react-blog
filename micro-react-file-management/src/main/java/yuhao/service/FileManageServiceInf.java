package yuhao.service;


import org.springframework.web.multipart.MultipartFile;
import yuhao.dto.req.FileReqDTO;
import yuhao.dto.resp.RespDTO;
import yuhao.entity.Image;

import java.util.List;

/**
 * @author 喻浩
 * @create 2020-03-10-14:55
 */
public interface FileManageServiceInf {

    /**
     * 上传文件
     * @param file
     * @return
     */
    RespDTO<Object> fileUpload(MultipartFile file);

    /**
     * 上传大文件
     * @param file
     * @return
     */
    RespDTO<Object> bigFileUpload(MultipartFile file);

    /**
     * 获得文件
     * @author yuhao5
     * @date 2020-07-29
     */
    RespDTO<Object> getImages(FileReqDTO fileReqDTO);

    /**
     * 添加文件
     * @author yuhao5
     * @date 2020-08-05
     */
    RespDTO<Object> addImage(Image image);

    /**
     * 删除图片
     * @author yuhao5
     * @date 2020-08-05
     */
    RespDTO<Object> removeImages(List<Image> images);
}
