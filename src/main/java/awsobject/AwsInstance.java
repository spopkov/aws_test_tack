package awsobject;

import awsobject.base.AwsObject;
import enums.AwsInstanceState;
import enums.Region;

public class AwsInstance extends AwsObject {

    public AwsInstance(int id, Region region, String type, AwsInstanceState state) {
        super(id, region);
        this.state = state;
        this.type = type;
    }

    private String type;

    public AwsInstanceState getState() {
        return state;
    }

    private AwsInstanceState state;
}
