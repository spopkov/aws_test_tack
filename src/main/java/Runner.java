import awsobject.AwsInstance;
import awsobject.AwsVolume;
import awsobject.base.AwsObject;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import static enums.AwsEntityType.Instance;
import static enums.AwsEntityType.Volume;
import static enums.AwsInstanceState.running;
import static enums.AwsInstanceState.terminated;
import static java.util.Collections.singletonList;
import static lookup.ObjectLookup.*;

public class Runner {

    public static void main(String[] args) {
        System.out.println("Volumes with attached running instances are: \n" + getAllVolumesWithRunningInstances());
        System.out.println("Not terminated instances are: \n" + getAllNotTerminatedInstances());
    }

    //Using this method we will retrieve the list of volumes which are connected to the instances in the 'running' state
    private static List<AwsObject> getAllVolumesWithRunningInstances() {
        //Create the map of parameters which we want to look for
        Map<String, Object> propertyMap = new HashMap<String, Object>() {{
            put("state", running.toString());
        }};
        //Get all AwsObjects of type Instance
        List<AwsObject> allInstances = getAwsObjects(Instance);
        //Get all AwsObjects witch mach criteria set in map
        List<AwsObject> runningInstances = findAwsObjectsWithProperties(allInstances, propertyMap);
        //Get list of instances id's
        List<Integer> instanceIds = getIds(runningInstances);
       //Create predicate for Volumes filtering(volume should have an id from the list as getAttachedInstanceId)
        Predicate<AwsObject> predicate = awsObject -> instanceIds.contains(((AwsVolume) awsObject).getAttachedInstanceId());
        //Get all AwsObjects of type Volume
        List<AwsObject> allVolumes = getAwsObjects(Volume);
        //Filter the volumes list using predicate
        return findAwsObjectsWithPredicates(allVolumes, singletonList(predicate));
    }

    //Using this method we will retrieve the list of instances which are not in 'terminated' state
    private static List<AwsObject> getAllNotTerminatedInstances() {
        //Get all AwsObjects of type Instance
        List<AwsObject> allInstances = getAwsObjects(Instance);
        //Create predicate for Instances filtering(instance status shouldn't be 'terminated')
        Predicate<AwsObject> predicate = awsObject -> ((AwsInstance) awsObject).getState() != terminated;
        //Filter the instances list using predicate
        return findAwsObjectsWithPredicates(allInstances, singletonList(predicate));
    }

    //Retrieve the list of AwsObject ids
    private static List<Integer> getIds(List<AwsObject> awsObjects) {
        return awsObjects
                .stream()
                .map(AwsObject::getId)
                .collect(Collectors.toList());
    }
}
