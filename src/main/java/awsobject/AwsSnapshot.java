package awsobject;

import awsobject.base.AwsObject;
import enums.Region;

public class AwsSnapshot extends AwsObject {

    public AwsSnapshot(int id, String name, Region region, int sourceVolumeID) {
        super(id, region);
        this.name = name;
        this.SourceVolumeID = sourceVolumeID;
    }

    private int SourceVolumeID;
    private String name;
}
