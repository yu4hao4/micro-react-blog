package yuhao.util;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author 喻浩
 * @create 2020-05-23-20:32
 */
public class TimeFormat {
    public static String getCurrentFullTime(){
        return time("y-M-d H:m:ss");
    }

    public static String getCurrentTime(){
        return time("y-M-d H:m:ss");
    }

    private static String time(String reg){
        //使用自定义的格式
        DateTimeFormatter format =DateTimeFormatter.ofPattern(reg);
//        DateTimeFormatter format =DateTimeFormatter.ofPattern("y-M-d H:m:s");
        //使用静态方法now()获取当前时间日期
        LocalDateTime ldt=LocalDateTime.now();
        return format.format(ldt);
    }
}
