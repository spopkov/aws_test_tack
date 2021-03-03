package awsobject;

import awsobject.base.AwsObject;
import enums.AwsVolumeState;
import enums.Region;
import lombok.Getter;

@Getter
public class AwsVolume extends AwsObject {

    public AwsVolume(int id, String name, AwsVolumeState state, Region region, Integer attachedInstanceId) {
        super(id, region);
        this.name = name;
        this.state = state;
        this.attachedInstanceId = attachedInstanceId;
    }

    private String name;

    private AwsVolumeState state;

    private Integer attachedInstanceId;

    public String getName() {
        return name;
    }

    public AwsVolumeState getState() {
        return state;
    }

    public Integer getAttachedInstanceId() {
        return attachedInstanceId;
    }
}
