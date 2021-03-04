package lookup;

import awsobject.base.AwsObject;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import parser.AwsObjectsDataParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lookup.ObjectLookup.findAwsObjectsWithProperties;
import static org.testng.Assert.*;
import static util.MapUtil.getFromMap;
import static util.MapUtil.awsObjectPropertiesAsMap;

public class ObjectLookupTest {

    private final SoftAssert softAssert = new SoftAssert();

    @Test(description = "Lookup with multiple criteria")
    public void testAwsLookup() {
        List<AwsObject> allAwsObjects = new AwsObjectsDataParser().getAllAwsObjects();
        Map<String, Object> propertyMap = new HashMap<String, Object>() {{
            put("state", "running");
            put("region", "oregon");
        }};
        List<? extends AwsObject> filteredAwsObjects = findAwsObjectsWithProperties(allAwsObjects, propertyMap);
        assertFalse(filteredAwsObjects.isEmpty(),"No Aws Objects were found for given filter");
        filteredAwsObjects.forEach(awsObject -> {
            System.out.println("testing " + awsObject.getId());
            propertyMap.keySet().forEach(key ->
                    this.softAssert.assertEquals(getFromMap(awsObjectPropertiesAsMap(awsObject), key), propertyMap.get(key)));
        });
        this.softAssert.assertAll();
    }
}