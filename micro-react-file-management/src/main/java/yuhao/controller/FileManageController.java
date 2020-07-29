package yuhao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yuhao.dto.req.FileReqDTO;
import yuhao.dto.resp.RespDTO;
import yuhao.service.FileManageServiceInf;

/**
 * @author 喻浩
 * @create 2020-03-10-14:54
 */
@CrossOrigin
@RestController
public class FileManageController {

    @Autowired
    private FileManageServiceInf fileManageService;

    @Async
    @PostMapping("/fileUpload")
    public RespDTO<Object> fileUpload(@RequestPart("file") MultipartFile file){
        return fileManageService.fileUpload(file);
    }

    /**
     * 上传大文件
     * @param file
     * @return
     */
    @Async
    @PostMapping("/bigFileUpload")
    public RespDTO<Object> bigFileUpload(@RequestPart("file") MultipartFile file){
        return fileManageService.bigFileUpload(file);
    }

    /**
     * 获得文件
     * @author yuhao5
     * @date 2020-07-29
     */
    @PostMapping("/getFiles")
    public RespDTO<Object> getFiles(@RequestBody FileReqDTO fileReqDTO){
        return fileManageService.getFiles(fileReqDTO);
    }
}
