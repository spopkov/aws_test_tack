package awsobject;

import awsobject.base.AwsObject;
import enums.AwsInstanceState;
import enums.Region;

public class AwsInstance extends AwsObject {

    public String getType() {
        return type;
    }

    public AwsInstanceState getState() {
        return state;
    }

    public AwsInstance(int id, Region region, String type, AwsInstanceState state) {
        super(id, region);
        this.state = state;
        this.type = type;
    }

    private String type;

    private AwsInstanceState state;
}
