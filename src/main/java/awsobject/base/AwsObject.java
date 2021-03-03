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

    public String toString() {
        StringBuilder result = new StringBuilder();
        String newLine = System.getProperty("line.separator");
        List<Field> mergedFields = getAllFields();

        for (Field field : mergedFields) {
            result.append("  ");
            try {
                result.append(field.getName());
                result.append(":");
                field.setAccessible(true);
                result.append(field.get(this));
            } catch (IllegalAccessException ex) {
                ex.printStackTrace();
            }
            result.append(newLine);
        }
        return result.toString();
    }

    public List<Field> getAllFields() {
        Field[] fields = this.getClass().getDeclaredFields();
        Field[] superFields = this.getClass().getSuperclass().getDeclaredFields();
        return mergeArrays(fields, superFields);
    }

    private List<Field> mergeArrays(Field[] first, Field[] second) {
        return Stream.of(first, second)
                .flatMap(Stream::of)
                .collect(toList());
    }
}
