package util;

import java.util.Map;

public class MapUtil {

    public static String getFromMap(Map<String,String> map, String key){
      return map.getOrDefault(key, "");
    }
}
