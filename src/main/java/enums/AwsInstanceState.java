package enums;

public enum AwsInstanceState {

    running("running"),
    stopped("stopped"),
    terminated("terminated");

    private final String state;

    AwsInstanceState(String awsState) {
        state = awsState;
    }
}
