package awsobject;

import awsobject.base.AwsObject;
import enums.AwsInstanceState;
import enums.AwsInstanceType;
import enums.Region;

public class AwsInstance extends AwsObject {

    public AwsInstance(int id, Region region, AwsInstanceType type, AwsInstanceState state) {
        super(id, region);
        this.state = state;
        this.type = type;
    }

    private AwsInstanceType type;

    public AwsInstanceState getState() {
        return state;
    }

    private AwsInstanceState state;
}
