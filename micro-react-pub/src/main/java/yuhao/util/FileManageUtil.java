package yuhao.util;

import java.io.File;
import java.util.Date;

/**
 * @author 喻浩
 * @create 2020-03-10-14:51
 */
public class FileManageUtil {

    /**
     * 获得文件的存储路径(图片)
     * @return
     */
    public static String getFileStorePath(){
        return System.getProperty("user.dir")+ File.separator + "images" + File.separator;
    }

    /**
     * 大文件存储路径
     * @return
     */
    public static String getBigFileStorePath(){
        return System.getProperty("user.dir")+ File.separator + "files" + File.separator;
    }

    /**
     * 获得文件名
     * @param filename
     * @return
     */
    public static String getInitFileName(String filename){
        return new Date().getTime()+"_"+filename;
    }

    /**
     * 获得上传文件的路径
     * @return
     */
    public static String getUploadFilePath(String filename){
        return System.getProperty("user.dir")+ File.separator + new Date().getTime()+"_"+filename;
    }

//    public static void main(String[] args) {
//        System.out.println(getUploadFilePath("123.jpg"));
//    }

}
