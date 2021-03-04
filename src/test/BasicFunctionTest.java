import awsobject.base.AwsObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import parser.AwsObjectsDataParser;

import java.util.List;

public class BasicFunctionTest {

    @Test(description = "Get list of all available AwsObjects and check that it contains them all")
    public void awsObjectsListTest() {
        List<AwsObject> awsObjList = new AwsObjectsDataParser().getAllAwsObjects();
        Assert.assertEquals(awsObjList.size(), 10, "Objects list size is incorrect");
    }
}
