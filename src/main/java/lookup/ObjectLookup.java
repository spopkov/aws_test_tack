package lookup;

import awsobject.base.AwsObject;
import enums.AwsEntityType;
import parser.AwsObjectsDataParser;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static util.MapUtil.getFromMap;
import static util.MapUtil.propsToMap;
import static util.ResourceFileHelper.getAwsObjectsString;

public class ObjectLookup {

    public List<? extends AwsObject> findAwsObjectsWithProperties(List<? extends AwsObject> awsObjList, Map<String, Object> propertyMap) {
        List<Predicate<AwsObject>> awsObjectPredicates = getPredicate(propertyMap);
        return awsObjList.stream()
                .filter(awsObjectPredicates
                        .stream()
                        .reduce(x -> true, Predicate::and))
                .collect(Collectors.toList());
    }

    private List<Predicate<AwsObject>> getPredicate(Map<String, Object> propertyMap) {
        List<Predicate<AwsObject>> allPredicates = new ArrayList<>();
        propertyMap.keySet().forEach(key ->
                allPredicates.add(awsObject -> getFromMap(propsToMap(awsObject), key).equals(propertyMap.get(key))));
        return allPredicates;
    }

    @SuppressWarnings("unchecked")
    public <T extends AwsObject> List<T> getAwsObjects(AwsEntityType type) {
        String objects = getAwsObjectsString(type);
        List<AwsObject> list = new AwsObjectsDataParser().getObjectList(type, objects);
        return list.stream().map(obj -> (T) obj).collect(Collectors.toList());
    }
}
