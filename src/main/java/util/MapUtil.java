package util;

import awsobject.base.AwsObject;

import java.util.Map;

import static util.StringUtil.cleanupString;
import static util.StringUtil.stringToMap;

public class MapUtil {
    //Get value from the props map. Empty string if value is absent
    public static String getFromMap(Map<String,String> map, String key){
      return map.getOrDefault(key, "");
    }

    /** Transform AwsObject properties string into Map
     * ex.
     * SourceVolumeID:2100 name:data1_backup region:oregon id:3100
     * to Map{
     *   SourceVolumeID:2100
     *   name:data1_backup
     *   region:oregon
     *   id:3100}
     */
    public static Map<String, String> awsObjectPropertiesAsMap(AwsObject awsObject) {
        //Cleanup AwsObject string to not have whitespaces in keys/values
        String props = cleanupString(awsObject.toString(),"[\\s+]{3}", ",");
        return stringToMap(props);
    }
}
