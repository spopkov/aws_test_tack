package parser;

import awsobject.base.AwsObject;
import enums.AwsEntityType;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

import static enums.AwsEntityType.*;
import static util.ResourceFileHelper.getAwsObjectsString;

public class AwsObjectsDataParserTest {

    @DataProvider(name = "awsEntityType")
    public Object[][] dataProviderMethod() {
        return new Object[][]{{Instance}, {Volume}, {Snapshot}};
    }

    @Test(dataProvider = "awsEntityType", description = "Check that parser returns objects of proper type")
    public void awsObjectsListTest(AwsEntityType awsEntityType) {
        String objects = getAwsObjectsString(awsEntityType);
        List<AwsObject> awsObjList = new AwsObjectsDataParser().getObjectList(awsEntityType, objects);
        awsObjList.forEach(awsObject -> {
            Assert.assertTrue(awsObject.getClass().getName().toLowerCase().endsWith(convertAwsEntityTypeToClassName(awsEntityType)));
        });
        System.out.println(awsObjList);
    }

    private String convertAwsEntityTypeToClassName(AwsEntityType awsEntityType){
        return awsEntityType.toString().replace("_","");
    }
}