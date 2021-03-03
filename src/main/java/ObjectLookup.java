import awsobject.base.AwsObject;

import java.lang.reflect.Field;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static util.MapUtil.getFromMap;
import static util.StringUtil.objStringToMap;

public class ObjectLookup {

    public static void main(String[] args) {
        List<AwsObject> awsObjList = StringToObject.getAllObjects();
        Map<String, String> propertyMap = new HashMap<String, String>() {{
            put("state", "running");
            put("region", "london");
        }};
        System.out.println(awsLookup(awsObjList, propertyMap));
    }

    public static List<AwsObject> awsLookup(List<AwsObject> awsObjList, Map<String, String> propertyMap) {
        List<Predicate<AwsObject>> awsObjectPredicates = getPredicate(propertyMap);
        return awsObjList.stream()
                .filter(awsObjectPredicates
                        .stream()
                        .reduce(x -> true, Predicate::and))
                .collect(Collectors.toList());
    }

    private static List<Predicate<AwsObject>> getPredicate(Map<String, String> propertyMap) {
        List<Predicate<AwsObject>> allPredicates = new ArrayList<>();
        String[] keys = propertyMap.keySet().toArray(new String[0]);

        for (String key : keys) {
            allPredicates.add(awsObject -> getFromMap(propsToMap(awsObject), key).equals(propertyMap.get(key)));
        }
        return allPredicates;
    }

    private static Map<String, String> propsToMap(AwsObject awsObject) {
        String props = awsObject.toString().replace(" ", "");
        return objStringToMap(props);
    }
}
