package factory;

import awsobject.AwsInstance;
import awsobject.AwsSnapshot;
import awsobject.AwsVolume;
import awsobject.base.AwsObject;
import enums.*;

import java.util.Map;
import java.util.Optional;

public class AwsObjectFactory {

    //Factory method which creates AwsObjects of given type with provided properties
    public AwsObject createAwsObject(AwsEntityType type, Map<String, String> properties) {
        //Get common values from map
        int id = idAsInt(properties.get("id"));
        Region region = getEnumValue(properties.get("region"), Region.class);
        String name = properties.get("name");
        switch (type) {
            case Instance:
                //Get AwsInstance specific values from map
                AwsInstanceType instanceType = getEnumValue(properties.get("type"), AwsInstanceType.class);
                AwsInstanceState awsInstanceState = getEnumValue(properties.get("state"), AwsInstanceState.class);
                //Create new instance of AwsInstance
                return new AwsInstance(id, region, instanceType, awsInstanceState);
            case Volume:
                //Get AwsVolume specific values from map
                AwsVolumeState awsVolumeState = getEnumValue(properties.get("state"), AwsVolumeState.class);
                Optional<Integer> attachedInstanceId = getOptional(properties.get("attached_instance_id"));
                //Create new instance of AwsVolume
                return new AwsVolume(id, name, awsVolumeState, region, attachedInstanceId.orElse(null));
            case Snapshot:
                //Get AwsSnapshot specific values from map
                int sourceVolumeId = idAsInt(properties.get("source_volume_id"));
                //Create new instance of AwsSnapshot
                return new AwsSnapshot(id, name, region, sourceVolumeId);
            default:
                return null;
        }
    }

    //Get value from enum passed as a parameter
    private <T extends Enum<T>> T getEnumValue(String enumConstant, Class<T> clazz) {
        return T.valueOf(clazz, enumConstant);
    }

    //attachedInstanceId can be null, so get it as Optional
    private Optional<Integer> getOptional(String id) {
        return (id.isEmpty()) ? Optional.empty() : Optional.of(idAsInt(id));
    }
    //Storing id's as Int, so parse them from String which goes from map
    private int idAsInt(String id) {
        return Integer.parseInt(id);
    }
}