package factory;

import awsobject.AwsInstance;
import awsobject.AwsSnapshot;
import awsobject.AwsVolume;
import awsobject.base.AwsObject;
import enums.AwsEntityType;
import enums.AwsInstanceState;
import enums.AwsVolumeState;
import enums.Region;

import java.util.Map;
import java.util.Optional;

public class AwsObjectFactory {

    public AwsObject createAwsObject(AwsEntityType type, Map<String, String> properties) {
        int id = idAsInt(properties.get("id"));
        Region region = getRegion(properties);
        String name = properties.get("name");
        switch (type) {
            case Instance:
                String instanceType = properties.get("type");
                AwsInstanceState awsInstanceState = getAwsInstanceState(properties);
                return new AwsInstance(id, region, instanceType, awsInstanceState);
            case Volume:
                AwsVolumeState awsVolumeState = getAwsVolumeState(properties);
                Optional<Integer> attachedInstanceId = getOptional(properties.get("attached_instance_id"));
                return new AwsVolume(id,name,awsVolumeState, region, attachedInstanceId.orElse(null));
            case Snapshot:
                int sourceVolumeId = idAsInt(properties.get("source_volume_id"));
                return new AwsSnapshot(id, name, region, sourceVolumeId);
            default:
                return null;
        }
    }

    private Region getRegion(Map<String, String> properties) {
        return Region.valueOf(properties.get("region"));
    }

    private AwsInstanceState getAwsInstanceState(Map<String, String> properties) {
        return AwsInstanceState.valueOf(properties.get("state"));
    }
    private AwsVolumeState getAwsVolumeState(Map<String, String> properties) {
        return AwsVolumeState.valueOf(properties.get("state"));
    }

    private Optional<Integer> getOptional(String id) {
       return (id.isEmpty()) ? Optional.empty() : Optional.of(idAsInt(id));
    }

    private int idAsInt(String id) {
        return Integer.parseInt(id);
    }
}