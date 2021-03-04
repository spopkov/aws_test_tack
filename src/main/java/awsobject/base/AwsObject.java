package awsobject.base;

import enums.Region;

import java.lang.reflect.Field;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public abstract class AwsObject {

    public AwsObject(int id, Region region) {
        this.id = id;
        this.region = region;
    }

    private Region region;

    private int id;

    public int getId() {
        return id;
    }

    //Override toString() to show all fields with their values Using Reflection API on AwsObject.toString() call
    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        result.append(newLine);

        List<Field> mergedFields = getAllFields();
        for (Field field : mergedFields) {
            result.append("  ");
            try {
                //Print field name
                result.append(field.getName());
                result.append(":");
                //Access private fields
                field.setAccessible(true);
                //Print field value
                result.append(field.get(this));
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
            result.append(newLine);
        }
        return result.toString();
    }
    //Collect current instance fields (own and inherited)
    public List<Field> getAllFields() {
        Field[] fields = this.getClass().getDeclaredFields();
        Field[] superFields = this.getClass().getSuperclass().getDeclaredFields();
        return mergeArrays(fields, superFields);
    }
    //Merge field arrays into one
    private List<Field> mergeArrays(Field[] first, Field[] second) {
        return Stream.of(first, second)
                .flatMap(Stream::of)
                .collect(toList());
    }
}
