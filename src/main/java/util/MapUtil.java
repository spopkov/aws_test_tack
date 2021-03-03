package util;

import awsobject.base.AwsObject;

import java.util.Map;

import static util.StringUtil.objStringToMap;

public class MapUtil {

    public static String getFromMap(Map<String,String> map, String key){
      return map.getOrDefault(key, "");
    }

    public static Map<String, String> propsToMap(AwsObject awsObject) {
        String props = awsObject.toString().replace(" ", "");
        return objStringToMap(props);
    }
}
