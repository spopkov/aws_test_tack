package util;

import java.util.Arrays;
import java.util.HashMap;
import java.util.stream.Collectors;

public class StringUtil {

    public static String cleanupString(String inputString) {
        return inputString
                .trim()    //todo use regex
                .replaceAll("\\n", "")
                .toLowerCase()
                .replace(".", "")
                .replace("-", "_");
    }

    public static HashMap<String, String> stringToMap(String awsObjectsString, String delimiter) {
        awsObjectsString = cleanupString(awsObjectsString);
        return (HashMap<String, String>)
                Arrays.stream(awsObjectsString.split(delimiter))
                        .map(s -> s.split(":"))
                        .collect(Collectors.toMap(e -> getValue(e,0), e -> getValue(e,1)));
    }

    //todo refactor duplication
    public static HashMap<String, String> objStringToMap(String awsObjectString) {
        return (HashMap<String, String>)
                Arrays.stream(awsObjectString.split("\\n"))
                        .map(s -> s.split(":"))
                        .collect(Collectors.toMap(e -> getValue(e,0), e -> getValue(e,1)));
    }

    private static String getValue(String[] array, int index) {
        try {
            return array[index];
        } catch (IndexOutOfBoundsException e) {
            return "";
        }
    }
}
