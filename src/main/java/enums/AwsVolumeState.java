package enums;

public enum AwsVolumeState {

    in_use("In-use"),
    available("Available");

    private final String state;

    AwsVolumeState(String awsState) {
        state = awsState;
    }
}
