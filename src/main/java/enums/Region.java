package enums;

public enum Region {

    california("California"),
    ohio("Ohio"),
    oregon("Oregon"),
    n_virginia("North Virginia"),
    virginia("Virginia"),
    pasific("Pasific"),
    london("London");

    private final String region;

    Region(String awsRegion) {
        region = awsRegion;
    }
}
