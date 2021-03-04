package util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class StringUtil {

    //Adapt input string to be correctly parsed and transformed into enum constants
    public static String cleanupString(String inputString, String regex, String replacement) {
        return inputString
                .trim()
                .replaceAll(regex,replacement)
                .toLowerCase();
    }

    //Parse the string representation of AwsObject and transform it to Map
    public static HashMap<String, String> stringToMap(String awsObjectsString) {
        awsObjectsString = cleanupString(awsObjectsString,"[.]|[-]", "_");
        return (HashMap<String, String>)
                Arrays.stream(awsObjectsString.split(","))
                        .map(s -> s.split(":"))
                        .collect(Collectors.toMap(e -> getValue(e,0), e -> getValue(e,1)));
    }

    //Set the blank value to map, in case if value is empty in AwsObject string
    private static String getValue(String[] array, int index) {
        try {
            return array[index];
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }
}
