package lookup;

import awsobject.base.AwsObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import parser.AwsObjectsDataParser;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static lookup.ObjectLookup.findAwsObjectsWithProperties;
import static org.testng.Assert.assertFalse;
import static util.MapUtil.awsObjectPropertiesAsMap;
import static util.MapUtil.getFromMap;
import static util.StringUtil.cleanupString;

public class ObjectLookupTest {

    private final SoftAssert softAssert = new SoftAssert();

    @DataProvider(name = "searchCriteria")
    public Object[][] dataProviderMethod() {
        Map<String, Object> propertyMap = new HashMap<String, Object>() {{
            put("state", "running");
            put("region", "oregon");
        }};
        Map<String, Object> dotInString = new HashMap<String, Object>() {{
            put("region", "n.virginia");
        }};
        Map<String, Object> minusInString = new HashMap<String, Object>() {{
            put("state", "in-use");
        }};
        return new Object[][]{
                {propertyMap},
                {dotInString},
                {minusInString}};
    }

    @Test(dataProvider = "searchCriteria", description = "Lookup with multiple criteria")
    public void testAwsLookup(Map<String, Object> propertyMap) {
        List<AwsObject> allAwsObjects = new AwsObjectsDataParser().getAllAwsObjects();

        List<? extends AwsObject> filteredAwsObjects = findAwsObjectsWithProperties(allAwsObjects, propertyMap);
        assertFalse(filteredAwsObjects.isEmpty(), "No Aws Objects were found for given filter");
        filteredAwsObjects.forEach(awsObject -> {
            System.out.println("testing " + awsObject.getId());
            propertyMap.keySet().forEach(key ->
                    this.softAssert.assertEquals(getFromMap(awsObjectPropertiesAsMap(awsObject), key),
                            cleanupString(propertyMap.get(key).toString())));
        });
        this.softAssert.assertAll();
    }
}