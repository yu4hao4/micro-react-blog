package yuhao.util;


import java.util.*;

/**
 * @description: 将List里的元素次数统计到Map并排序
 * @author: haku
 * @create: 2020-04-29 22:50
 **/
public class MapUtil {

    public static List<Map.Entry<String, Integer>> getMapSort(List<String> list) {
        if (list == null || list.size() == 0) {
            return null;
        }
        Map<String, Integer> map = new HashMap<String, Integer>();
        for (String temp : list) {
            Integer count = map.get(temp);
            map.put(temp, (count == null) ? 1 : count + 1);
        }
        return sortMap(map);
    }

   public static List<Map.Entry<String, Integer>> sortMap(Map<String, Integer> map) {

       List<Map.Entry<String, Integer>> list = new ArrayList(map.entrySet());
       Collections.sort(list, new Comparator<Map.Entry<String, Integer>>()
       {
           @Override
           public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2)
           {
               return o2.getValue() - o1.getValue();
           }
       });
       map = new HashMap<>();
       for (Map.Entry s : list) {
           map.put(s.getKey().toString(),new Integer(s.getValue().toString()));
//           System.out.println(s.getKey()+"--"+s.getValue());
       }
        return list;
   }
}
