package enums;

public enum AwsInstanceType {

    micro("micro"),
    large1("large1"),
    xlarge3("xlarge3");

    private final String type;

    AwsInstanceType(String awsType) {
        type = awsType;
    }
}
