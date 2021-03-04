package lookup;

import awsobject.base.AwsObject;
import enums.AwsEntityType;
import parser.AwsObjectsDataParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static util.MapUtil.getFromMap;
import static util.MapUtil.awsObjectPropertiesAsMap;
import static util.ResourceFileHelper.getAwsObjectsString;
import static util.StringUtil.cleanupString;

public class ObjectLookup {

    //Look for AwsObjects which mach predicates from the list
    public static List<AwsObject> findAwsObjectsWithPredicates(List<AwsObject> awsObjList, List<Predicate<AwsObject>> predicates) {
        return awsObjList.stream()
                .filter(predicates
                        .stream()
                        .reduce(x -> true, Predicate::and))
                .collect(Collectors.toList());
    }

    //Find AwsObjects by Map of their properties
    public static List<AwsObject> findAwsObjectsWithProperties(List<AwsObject> awsObjList, Map<String, Object> propertyMap) {
        return findAwsObjectsWithPredicates(awsObjList, getPredicatesFromMap(propertyMap));
    }

    //Create one or more predicates which will be used in a filter
    private static List<Predicate<AwsObject>> getPredicatesFromMap(Map<String, Object> propertyMap) {
        List<Predicate<AwsObject>> allPredicates = new ArrayList<>();
        propertyMap.keySet().forEach(key ->
                //Predicate will take a value from AwsObject's properties and compare with value with the same key in a map
                allPredicates.add(awsObject -> getFromMap(awsObjectPropertiesAsMap(awsObject), key)
                        .equals(cleanupString(propertyMap.get(key).toString()))));
        return allPredicates;
    }

    @SuppressWarnings("unchecked")
    //Allow as to get any type of the AwsObject
    public static <T extends AwsObject> List<T> getAwsObjects(AwsEntityType type) {
        //Parse aws object string of required type  from resources
        String objects = getAwsObjectsString(type);
        //Creates list of AwsObjects
        List<AwsObject> list = new AwsObjectsDataParser().getObjectList(type, objects);
        //Returns collection of any subtypes of AwsObject
        return list.stream().map(obj -> (T) obj).collect(Collectors.toList());
    }
}
