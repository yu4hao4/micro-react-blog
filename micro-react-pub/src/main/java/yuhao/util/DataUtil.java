package yuhao.util;

import java.lang.reflect.Field;
import java.util.HashMap;

/**
 * @author 喻浩
 * @create 2020-03-31-14:18
 */
public class DataUtil {
    public static  boolean checkObjFieldIsNull(Object obj) throws IllegalAccessException {
        HashMap<Integer,Integer> hash = new HashMap<>();
        boolean flag = false;
        for(Field f : obj.getClass().getDeclaredFields()){
            f.setAccessible(true);
            if(f.get(obj) == null || f.get(obj).equals("")){
                flag = true;
                return flag;
            }
        }
        return flag;

    }
}
