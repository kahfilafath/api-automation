import static io.restassured.RestAssured.given;

import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import response.getmessage.GetMessageResponse;
import response.sendmessage.SendMessageResponse;

public class ApiJavaAutomationTest {

  public final static String URL = "http://pretest-qa.dcidev.id";

  @Test
  public void getMessage() {
    //specify request
    Response response = given().log().all().baseUri(URL).basePath("/api/v1/message")
        .contentType(ContentType.JSON).accept(ContentType.JSON)
         .header("Authorization","d7ef39693fe29257ae89ecde0cd50df7c321d2de56e8d3954806a9e8a96deee5")
            .pathParam("message","61bec2ba-3cff-4b2a-90f5-e63ba9960b3e")
        .get("/{message}");

    //deserialize java object to json
    ResponseBody responseBody = response.getBody();
    GetMessageResponse body = responseBody.as(GetMessageResponse.class);

    response.getBody().prettyPrint();

    int statusCode = response.getStatusCode();
    Assertions.assertEquals(statusCode, 200);
    Assertions.assertEquals("79d3d61f-e7aa-473d-9c8e-fc1f695b9bd2",body.getData().getChat().get(0).getId());
    Assertions.assertEquals("test privy",body.getData().getChat().get(0).getMessage());
    Assertions.assertEquals("sender",body.getData().getChat().get(0).getType());
    Assertions.assertNotNull(body.getData().getChat().get(0).getCreatedAt());
    Assertions.assertNotNull(body.getData().getChat().get(0).getUserSender());
    System.out.println("The response status is " + statusCode);
  }


  @Test
  public void sendMessage() {
    String requestBody = "{\n"
        + "    \"user_id\": \"61bec2ba-3cff-4b2a-90f5-e63ba9960b3e\",\n"
        + "    \"message\": \"Privy Technical Test\"\n"
        + "}";

    Response response = given().log().all().baseUri(URL).basePath("/api/v1/message")
        .contentType(ContentType.JSON).accept(ContentType.JSON)
            .header("Authorization","d7ef39693fe29257ae89ecde0cd50df7c321d2de56e8d3954806a9e8a96deee5")
            .body(requestBody)
        .post("/send");

    //deserialize java object to json
    ResponseBody responseBody = response.getBody();
    SendMessageResponse body = responseBody.as(SendMessageResponse.class);

    response.getBody().prettyPrint();


    int statusCode = response.getStatusCode();
    Assertions.assertEquals(statusCode, 201);
    Assertions.assertEquals(body.getData(),"Success send message");
    System.out.println("The response status is " + statusCode);
  }


  @Test
  public void updateUser() {

    String requestBodyAdd = "{\n"
        + "    \"name\": \"Tri Abror\",\n"
        + "    \"job\": \"SEIT\"\n"
        + "}";

    String requestBodyUpdate = "{\n"
        + "    \"name\": \"Hendri\",\n"
        + "    \"job\": \"SEIT\"\n"
        + "}";

    Response responseAdd = given().log().all().baseUri(URL).basePath("/api")
        .contentType(ContentType.JSON).accept(ContentType.JSON)
        .body(requestBodyAdd)
        .post("/users");

    responseAdd.getBody().prettyPrint();

    Response responseUpdate = given().log().all().baseUri(URL).basePath("/api")
        .contentType(ContentType.JSON).accept(ContentType.JSON)
        .body(requestBodyUpdate)
        .pathParam("userId", responseAdd.path("id"))
        .put("/users/{userId}");

    responseUpdate.getBody().prettyPrint();

    int statusCode = responseUpdate.getStatusCode();
    Assertions.assertEquals(statusCode, 200);
    System.out.println("The responseAdd status is " + statusCode);
  }


  @Test
  public void deleteeUser() {

    String requestBody = "{\n"
        + "    \"name\": \"Tri Abror\",\n"
        + "    \"job\": \"SEIT\"\n"
        + "}";

    Response responseAdd = given().log().all().baseUri(URL).basePath("/api")
        .contentType(ContentType.JSON).accept(ContentType.JSON)
        .body(requestBody)
        .post("/users");

    responseAdd.getBody().prettyPrint();

    Response responseDelete = given().log().all().baseUri(URL).basePath("/api")
        .contentType(ContentType.JSON).accept(ContentType.JSON)
        .pathParam("userId", responseAdd.path("id"))
        .delete("/users/{userId}");

    int statusCode = responseDelete.getStatusCode();
    Assertions.assertEquals(statusCode, 204);
    System.out.println("The response status is " + statusCode);
  }


}
