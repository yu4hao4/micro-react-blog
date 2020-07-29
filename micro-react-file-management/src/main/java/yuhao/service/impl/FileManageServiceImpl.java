package yuhao.service.impl;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import yuhao.dto.req.FileReqDTO;
import yuhao.dto.resp.RespDTO;
import yuhao.service.FileManageServiceInf;
import yuhao.util.FileManageUtil;
import yuhao.util.RedisUtil;

import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * @author 喻浩
 * @create 2020-03-10-15:08
 */
@Slf4j
@Service
public class FileManageServiceImpl implements FileManageServiceInf {

    @Value("${image.url}")
    private String imageUrl;

    @Value("${file.url}")
    private String fileUrl;
    @Autowired
    private RedisUtil redisUtil;

    /**
     * 上传文件
     * @param file
     * @return
     */
    @Override
    public RespDTO fileUpload(MultipartFile file) {
        if(StringUtils.isEmpty(file.getName())){
            return RespDTO.error("文件不能为空");
        }

        System.out.println("校验文件大小开始");
        // 文件不大于2MB
        boolean checkFileSize = checkFileSize(file.getSize(), 2, "M");
        if ( !checkFileSize ){
            return RespDTO.error("文件太大");
        }
        System.out.println("校验文件大小结束");

        String suffix = Objects.requireNonNull(file.getOriginalFilename()).substring(file.getOriginalFilename().lastIndexOf(".") + 1);
        if( !("JPEG".equals(suffix.toUpperCase())
                || "JPG".equals(suffix.toUpperCase())
                || "PNG".equals(suffix.toUpperCase())) ){
            return RespDTO.error("文件错误");
        }

        return saveFile(imageUrl, file);
    }

    /**
     * 上传大文件
     * @param file
     * @return
     */
    @Override
    public RespDTO<Object> bigFileUpload(MultipartFile file) {
        if(StringUtils.isEmpty(file.getName())){
            return RespDTO.error("文件不能为空");
        }

        System.out.println("校验文件大小开始");
        // 文件不大于200MB
        boolean checkFileSize = checkFileSize(file.getSize(), 200, "M");
        if (!checkFileSize ){
            return RespDTO.error("文件太大");
        }
        System.out.println("校验文件大小结束");

        return saveFile(fileUrl, file);
    }

    /**
     * 保存文件
     * @author yuhao5
     * @date 2020-07-29
     */
    private RespDTO<Object> saveFile(String url, MultipartFile file){
        log.info("正在做上传操作，上传文件为：{}",file.getOriginalFilename());

        String filename = file.getOriginalFilename();
        String filePath = FileManageUtil.getBigFileStorePath() + filename;

        File destinationFile = new File(filePath);

        if (!destinationFile.getParentFile().exists()) {
            boolean result = destinationFile.getParentFile().mkdirs();
            if (!result) {
                log.info("文件存储路径 {} 创建失败",destinationFile.getParentFile());
            }
            log.info("文件存储路径 {} 创建成功",destinationFile.getParentFile());
        }

        //上传文件
        try {
            file.transferTo(destinationFile);
            log.info("文件上传成功，路径为：{}",filePath);
        } catch (IOException e) {
            e.printStackTrace();
            return RespDTO.error("文件错误");
        }

        return RespDTO.commonly(200,"文件上传成功",url+filename);
    }

    /**
     * 判断文件大小
     * @param len 文件长度
     * @param size 限制大小
     * @param unit 限制单位（B,K,M,G）
     * @return
     */
    public static boolean checkFileSize(Long len, int size, String unit) {
        System.out.println(len);
        double fileSize = 0;
        if ("B".equals(unit.toUpperCase())) {
            fileSize = (double) len;
        } else if ("K".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1024;
        } else if ("M".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1048576;
        } else if ("G".equals(unit.toUpperCase())) {
            fileSize = (double) len / 1073741824;
        }
        if (fileSize > size) {
            return false;
        }
        return true;
    }


    /**
     * 获得文件
     *
     * @param fileReqDTO
     * @author yuhao5
     * @date 2020-07-29
     */
    @Override
    public RespDTO<Object> getFiles(FileReqDTO fileReqDTO) {
        String fileStorePath = FileManageUtil.getFileStorePath();
        File file = new File(fileStorePath);
        File[] files = file.listFiles();
        for (File file1 : files) {

        }
        return null;
    }
}
