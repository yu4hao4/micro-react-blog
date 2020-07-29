package yuhao.service;


import org.springframework.web.multipart.MultipartFile;
import yuhao.dto.req.FileReqDTO;
import yuhao.dto.resp.RespDTO;

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
    RespDTO<Object> getFiles(FileReqDTO fileReqDTO);
}
