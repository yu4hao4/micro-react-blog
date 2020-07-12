package yuhao.service;


import org.springframework.web.multipart.MultipartFile;
import yuhao.api.RespBean;

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
    RespBean<Object> fileUpload(MultipartFile file);

    /**
     * 上传大文件
     * @param file
     * @return
     */
    RespBean<Object> bigFileUpload(MultipartFile file);
}
