import awsobject.AwsInstance;
import awsobject.base.AwsObject;
import enums.Region;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static util.MapUtil.getFromMap;
import static util.StringUtil.objStringToMap;

public class ObjectLookup {

    public static void main(String[] args) {
        List<AwsObject> awsObjList = StringToObject.getAllObjects();
        Map<String, String> propertyMap = new HashMap<String, String>() {{
            put("state", "running");
            put("region", "oregon");
        }};
        System.out.println(awsLookup(awsObjList, propertyMap));
    }

    public static List<AwsObject> awsLookup(List<AwsObject> awsObjList, Map<String, String> propertyMap) {
        Predicate<AwsObject> p = e -> getFromMap(propsToMap(e),"region").equals(propertyMap.get("region"));
        p = p.and(e -> getFromMap(propsToMap(e),"state").equals(propertyMap.get("state")));

        List<AwsObject> filteredObjects = awsObjList.stream()
                .filter(p).collect(Collectors.toList());
        return filteredObjects;
    }

    private static boolean compareProperties(AwsObject awsObject, Map<String, String> propertyMap) {
        Map<String, String> objectMap = propsToMap(awsObject);
     return  objectMap.containsValue(propertyMap.get("region"));
//        Map<String, Boolean> m = areEqualKeyValues(objectMap, propertyMap);
//        return true;
    }

    private static Map<String, Boolean> areEqualKeyValues(Map<String, String> first, Map<String, String> second) {
        return first.entrySet().stream()
                .collect(Collectors.toMap(e -> e.getKey(),
                        e -> e.getValue().equals(second.get(e.getKey()))));
    }

    private static Map<String, String> propsToMap(AwsObject awsObject) {
        String props = awsObject.toString().replace(" ", "");
        Map<String, String> propsMap = objStringToMap(props);
        return propsMap;
    }
}
