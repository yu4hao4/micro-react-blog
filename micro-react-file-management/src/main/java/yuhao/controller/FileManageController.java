package yuhao.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import yuhao.dto.req.FileReqDTO;
import yuhao.dto.resp.FileRespDTO;
import yuhao.dto.resp.RespDTO;
import yuhao.entity.Image;
import yuhao.service.FileManageServiceInf;

import java.io.File;
import java.util.List;

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
     * 添加图片
     * @author yuhao5
     * @date 2020-08-05
     */
    @PostMapping("/addImage")
    public RespDTO<Object> addImage(@RequestBody Image image){
        return fileManageService.addImage(image);
    }
    /**
     * 更新图片
     * @author yuhao5
     * @date 2020-08-05
     */
    @PostMapping("/updateImage")
    public RespDTO<Object> updateImage(@RequestBody Image image){
        return fileManageService.updateImage(image);
    }

    /**
     * 删除图片
     * @author yuhao5
     * @date 2020-08-05
     */
    @PostMapping("/removeImages")
    public RespDTO<Object> removeImages(@RequestBody List<Image> images){
        return fileManageService.removeImages(images);
    }

    /**
     * 获得图片
     * @author yuhao5
     * @date 2020-07-29
     */
    @PostMapping("/getImages")
    public RespDTO<Object> getImages(@RequestBody FileReqDTO fileReqDTO){
        return fileManageService.getImages(fileReqDTO);
    }

}
