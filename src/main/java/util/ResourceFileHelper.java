package util;

import enums.AwsEntityType;
import org.apache.commons.io.IOUtils;
;
import java.io.IOException;
import java.io.InputStream;

public class ResourceFileHelper {

    private static String readStream(String resourcePath) {
        ClassLoader classLoader = ClassLoader.getSystemClassLoader();
        InputStream stream = classLoader.getResourceAsStream(resourcePath);
        try {
            return IOUtils.toString(stream);
        } catch (IOException e) {
           throw new RuntimeException(e);
        }
    }

    public static String getAwsObjectsString(AwsEntityType dataType){
        return readStream(dataType.toString());
    }
}
