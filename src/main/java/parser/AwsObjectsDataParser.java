package parser;

import awsobject.base.AwsObject;
import enums.AwsEntityType;
import factory.AwsObjectFactory;
import util.StringUtil;

import java.util.*;
import java.util.stream.Collectors;

import static util.ResourceFileHelper.getAwsObjectsString;

public class AwsObjectsDataParser {

    //Used mostly for tests, allows to get all aws objects which are mentioned in AwsEntityType enum
    public List<AwsObject> getAllAwsObjects() {
        //Create list of types
        List<AwsEntityType> awsEntityTypes = Arrays.asList(AwsEntityType.values());
        //Create empty list which will work as a consumer
        List<List<AwsObject>> allObjects = new LinkedList<>();
        //For each entity type parse corresponding string and create lists of AwsObjects
        awsEntityTypes.forEach(awsEntityType -> {
            allObjects.add(getObjectList(awsEntityType, getAwsObjectsString(awsEntityType)));
        });
        //Using flatMap to transform list of AwsObject lists to the list of AwsObjects
        return allObjects.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
    }

    //Returns a list of AwsObjects of provided type
    public List<AwsObject> getObjectList(AwsEntityType type, String objectsString) {
        //Consumer for Map AwsObject properties
        List<Map<String, String>> properties = new LinkedList<>();
        //Create a list of AwsObject strings
        List<String> objects = getAwsObjectsList(objectsString);
        //Create properties map for each of the AwsObjects and put it in List
        objects.forEach(dataString -> properties.add(StringUtil.stringToMap(dataString)));
       //Create AwsObjects with awsObjectFactory
        AwsObjectFactory awsObjectFactory = new AwsObjectFactory();
        List<AwsObject> objectList = new LinkedList<>();
        properties.forEach(props -> {
            //For each properties map create an AwsObject of provided type
            objectList.add(awsObjectFactory.createAwsObject(type, props));
        });
        return objectList;
    }

    private List<String> getAwsObjectsList(String objectsString){
        //Split AwsObject properties strings
        return Arrays.asList(objectsString.split("%"));
    }
}
