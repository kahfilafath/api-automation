import static io.restassured.RestAssured.given;

import constant.TestingData;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import response.getmessage.GetMessageResponse;
import response.sendmessage.SendMessageResponse;

public class ApiJavaAutomationTest implements TestingData {

  @Test
  public void getMessageWithValidDataShouldReturn200() {
    //specify request
    Response response = given().log().all().baseUri(URL).basePath(BASEPATH)
        .contentType(ContentType.JSON).accept(ContentType.JSON)
         .header("Authorization",AUTH)
            .pathParam("userId","61bec2ba-3cff-4b2a-90f5-e63ba9960b3e")
        .get("/{userId}");

    //deserialize java object to json for response assertion
    ResponseBody responseBody = response.getBody();
    GetMessageResponse body = responseBody.as(GetMessageResponse.class);

    response.getBody().prettyPrint();

    int statusCode = response.getStatusCode();
    //assertion
    Assertions.assertEquals(statusCode, 200);
    for(int i=0;i<body.getData().getChat().size();i++){
      Assertions.assertNotNull(body.getData().getChat().get(i).getId());
      Assertions.assertNotNull(body.getData().getChat().get(i).getMessage());
      Assertions.assertNotNull(body.getData().getChat().get(i).getCreatedAt());
      Assertions.assertNotNull(body.getData().getChat().get(i).getUserSender());
      Assertions.assertNotNull(body.getData().getChat().get(i).getUserReceiver());
      Assertions.assertEquals("sender",body.getData().getChat().get(i).getType());
    }

    System.out.println("The response status is " + statusCode);
  }


  @Test
  public void sendMessageWithValidDataShouldReturn201() {
    //specify request
    String requestBody = "{\n"
        + "    \"user_id\": \"61bec2ba-3cff-4b2a-90f5-e63ba9960b3e\",\n"
        + "    \"message\": \"Privy Technical Test\"\n"
        + "}";
    Response response = given().log().all().baseUri(URL).basePath(BASEPATH)
        .contentType(ContentType.JSON).accept(ContentType.JSON)
            .header("Authorization",AUTH)
            .body(requestBody)
        .post("/send");

    //deserialize java object to json
    ResponseBody responseBody = response.getBody();
    SendMessageResponse body = responseBody.as(SendMessageResponse.class);
    response.getBody().prettyPrint();
    int statusCode = response.getStatusCode();
    //Assertion
    Assertions.assertEquals(201,statusCode);
    Assertions.assertEquals("Success send message",body.getData());
    System.out.println("The response status is " + statusCode);
  }
}
