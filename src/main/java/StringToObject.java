import awsobject.AwsInstance;
import awsobject.base.AwsObject;
import enums.AwsEntityType;
import factory.AwsObjectFactory;
import util.StringUtil;

import java.util.*;
import java.util.stream.Collectors;

import static util.ResourceFileHelper.getAwsObjectsString;

public class StringToObject {

    public static List<AwsObject> getAllObjects() {
        List<AwsEntityType> al = new ArrayList<>(Arrays.asList(AwsEntityType.values()));
        List<List<AwsObject>> allObjects = new ArrayList<>();
        al.forEach(awsEntityType -> {
            allObjects.add(getObjectList(awsEntityType, getAwsObjectsString(awsEntityType)));
        });
        return allObjects.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    public static List<AwsObject> getObjectList(AwsEntityType type, String objectsString) {

        List<Map<String, String>> properties = new LinkedList<>();
        List<String> objects = getAwsObjectsList(objectsString);

        objects.forEach(dataString -> properties.add(StringUtil.stringToMap(dataString, ",")));

        AwsObjectFactory awsObjectFactory = new AwsObjectFactory();
        List<AwsObject> objectList = new LinkedList<>();

        properties.forEach(props -> {
            objectList.add(awsObjectFactory.createAwsObject(type, props));
        });
        return objectList;
    }

    private static List<String> getAwsObjectsList(String objectsString){
        return Arrays.asList(objectsString.split("%"));
    }
}
