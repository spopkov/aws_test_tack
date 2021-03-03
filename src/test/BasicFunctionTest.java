import awsobject.base.AwsObject;
import org.testng.Assert;
import org.testng.annotations.Test;
import parser.AwsObjectsDataParser;

import java.util.List;

public class BasicFunctionTest {

    @Test
    public void awsObjectsListTest() {
        List<AwsObject> awsObjList = new AwsObjectsDataParser().getAllAwsObjects();
        Assert.assertEquals(awsObjList.size(), 10, "Objects list size is incorrect");
    }
}
