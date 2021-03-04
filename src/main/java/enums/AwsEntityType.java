package enums;

public enum AwsEntityType {

    Instance("aws_instance"),
    Volume("aws_volume"),
    Snapshot("aws_snapshot");

    private final String entityType;

    AwsEntityType(String awsEntityType) {
        entityType = awsEntityType;
    }

    public String toString() {
        return this.entityType;
    }
}
