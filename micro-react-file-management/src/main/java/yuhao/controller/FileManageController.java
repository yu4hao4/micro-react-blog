package yuhao.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yuhao.api.RespBean;
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
    public RespBean<Object> fileUpload(@RequestPart("file") MultipartFile file){
        return fileManageService.fileUpload(file);
    }

    /**
     * 上传大文件
     * @param file
     * @return
     */
    @Async
    @PostMapping("/bigFileUpload")
    public RespBean<Object> bigFileUpload(@RequestPart("file") MultipartFile file){
        return fileManageService.bigFileUpload(file);
    }

    @RequestMapping("/test")
    public RespBean test(){
        return RespBean.commonly(201,"hello");
    }

}
