package enums;

public enum Region {

    california("California"),
    ohio("Ohio"),
    oregon("Oregon"),
    nvirginia("North Virginia"),
    virginia("Virginia"),
    pasific("Pasific"),
    london("London");

    private final String region;

    Region(String awsRegion) {
        region = awsRegion;
    }
}
