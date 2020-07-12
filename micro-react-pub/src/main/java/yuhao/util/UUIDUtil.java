package yuhao.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

/**
 * @author 喻浩
 * @create 2020-02-28-17:36
 * 生成 32位 uuid的操作类
 */
public class UUIDUtil {
    private static String UUID(){
        return UUID.randomUUID().toString();
    }

    /**
     * 获得 32位 uuid
     * @return
     */
    public static String generateUUID(){
        return UUID().replace("-", "");
    }

}
