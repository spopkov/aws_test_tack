# aws_test_tack

Run the Runner class to display:

1. List of Volumes with are attached to the running instances
2. All the Instances which are not in terminated state.

Filters can be tested by setting custom parameters in ObjectLookupTest

Properties of each AwsObject instance can be displayed by execution instance.toString() method.

 getObjectList(AwsEntityType type, String objectsString) implementation:
 https://github.com/spopkov/aws_test_tack/blob/d0eab7c752a99a2caf26aa693c12c65f0a1c00e9/src/main/java/parser/AwsObjectsDataParser.java#L32

 findAwsObjectsWithProperties(List<AwsObject> awsObjList, Map<String, Object> propertyMap) implementation:
https://github.com/spopkov/aws_test_tack/blob/d0eab7c752a99a2caf26aa693c12c65f0a1c00e9/src/main/java/lookup/ObjectLookup.java#L30
