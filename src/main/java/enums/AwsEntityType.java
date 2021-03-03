package enums;

public enum AwsEntityType {

    Instance("aws_instance"),
    Volume("aws_volume"),
    Snapshot("aws_snapshot");

    private final String state;

    AwsEntityType(String awsState) {
        state = awsState;
    }

    public String toString() {
        return this.state;
    }
}
