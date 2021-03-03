import awsobject.AwsInstance;
import awsobject.base.AwsObject;
import enums.AwsVolumeState;
import lookup.ObjectFilter;

import java.util.List;

import static enums.AwsEntityType.Instance;
import static enums.AwsEntityType.Volume;
import static enums.AwsInstanceState.running;
import static enums.AwsInstanceState.terminated;
import static lookup.ObjectFilter.getAwsVolumesWithConnectedInstance;
import static lookup.ObjectFilter.getInstanceIds;

@SuppressWarnings("unchecked")
public class Runner {

    public static void main(String[] args) {
        System.out.println("Volumes with attached running instances are: \n" + test2());
        System.out.println("Not terminated instances are: \n" + test());
    }

    private static List<AwsInstance> test() {
        List<? extends AwsObject> runningInstances = ObjectFilter.getInstancesNotInState(Volume, AwsVolumeState.available);
        return (List<AwsInstance>) runningInstances;
    }

    private static List<AwsObject> test2() {
        List<AwsInstance> runningInstances = (List<AwsInstance>) ObjectFilter.getInstancesInState(Instance, running);
        List<Integer> instIds = getInstanceIds(runningInstances);
        return getAwsVolumesWithConnectedInstance(instIds);
    }
}
