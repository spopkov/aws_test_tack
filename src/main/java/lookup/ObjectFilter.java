package lookup;

import awsobject.AwsInstance;
import awsobject.AwsVolume;
import awsobject.base.AwsObject;
import enums.AwsEntityType;
import enums.AwsInstanceState;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static enums.AwsEntityType.Volume;

public class ObjectFilter {

    public static Stream<AwsObject> filter(Predicate<AwsObject> predicate, AwsEntityType awsEntityType) {
        return new ObjectLookup().getAwsObjects(awsEntityType)
                .stream()
                .filter(predicate);
    }

    public static List<? extends AwsObject> getInstancesInState(AwsEntityType type, AwsInstanceState state) {
        Predicate<AwsObject> predicate = awsObject -> ((AwsInstance) awsObject).getState().equals(state);
        return filter(predicate, type).collect(Collectors.toList());
    }

    public static List<? extends AwsObject> getInstancesNotInState(AwsEntityType type, AwsInstanceState state) {
        Predicate<AwsObject> predicate = awsObject -> ((AwsInstance) awsObject).getState() != state;
        return filter(predicate, type).collect(Collectors.toList());
    }

    public static List<AwsObject> getAwsVolumesWithConnectedInstance(List<Integer> instanceIds) {
        Predicate<AwsObject> predicate = awsObject -> instanceIds.contains(((AwsVolume) awsObject).getAttachedInstanceId());
        return ObjectFilter.filter(predicate, Volume)
                .collect(Collectors.toList());
    }

    public static List<Integer> getInstanceIds(List<AwsInstance> awsObjects) {
        return awsObjects
                .stream()
                .map(AwsInstance::getId)
                .collect(Collectors.toList());
    }
}
