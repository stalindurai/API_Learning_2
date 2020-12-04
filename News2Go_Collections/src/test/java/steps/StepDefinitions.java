package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.Assert;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class StepDefinitions {

    String token = "__eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzI1NiJ9.eyJfXy5haWQiOjQzMTMwMCwiX18uZW52aXJvbm1lbnQiOiJwcm9kdWN0aW9uIiwiX18uZXhwaXJlc0F0IjoxNjA2OTg2NTgxLCJfXy5zY29wZSI6IiIsIl9fLmNpZCI6Ijk0MDc2X3J5bWpzcUZjaWNfMVNHd3FBd19reFBadWZSX09YTHV1VlFXdSIsIl9fLnJkMSI6MjQwNTAsIl9fLnJkMiI6MjMzMDN9.B4JQghP3aXDSYIp8tLghommBt7UIV0rrW4xMmY_HcrI";
    Response response;
    String resp, lid = "xovgu8fb";
    String  id = "827brmmf";
    String createList = "ListData.json";
    String createSegment = "SegmentData.json";
    String updateSegment = "SegmentData.json";
    static String listID, segmentID;


    @Given("env is set")
    public void setEnv(){

        RestAssured.baseURI = "https://api.newsletter2go.com/";
    }

    @When("get query is made")
    public void getLists(){

        response =  RestAssured.given()
                            .header("Authorization", "Bearer "+token)
                            .pathParam("id", "lists")
                            .get("{id}");

        response.prettyPrint();
    }

    @When("POST list is made")
    public void postCreateList(){

        File file = new File(createList);

        response = RestAssured.given()
                .header("Authorization", "Bearer "+token)
                .pathParam("id", "lists")
                .body(file)
                .post("{id}");

        response.prettyPrint();

        JsonPath js2 = response.jsonPath();
        listID = js2.get("value.id").toString();


    }

    @When("POST segment is made")
    public void postCreateSegment() throws IOException, ParseException {

        File file = new File(createSegment);
        FileReader fileReader = new FileReader(createSegment);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(fileReader);


        jsonObject.put("list_id",id);
        String id = (String) jsonObject.get("list_id");
        System.out.println("id "+id);

        response = RestAssured.given()
                .header("Authorization", "Bearer "+token)
                .pathParam("id", "groups")
                .body(jsonObject)
                .post("{id}");

        response.prettyPrint();

        JsonPath js2 = response.jsonPath();
        segmentID = js2.get("value.id").toString().replaceAll("[^a-zA-Z0-9]+", "");

        System.out.println(segmentID);

    }

    @When("PATCH segment is made")
    public void patchUpdateSegment(){

        File file = new File(updateSegment);

        System.out.println("This is the current segID "+segmentID);

        response = RestAssured.given()
                .header("Authorization", "Bearer "+token)
                .pathParam("id","groups/"+segmentID)
                .body(file)
                .patch("{id}");

        response.prettyPrint();


    }

    @When("delete query is made")
    public void deleteList(){

        response = RestAssured.given()
                .header("Authorization", "Bearer "+token)
                .pathParam("id", "lists/"+id)
                .delete("{id}");

        response.prettyPrint();
    }

    @Then("lists are displayed")
    public void verifyListDisplayed(){

        System.out.println("Need to check the code");

        resp = response.asString();
        JsonPath js = new JsonPath(resp);
        System.out.println(js.get("info.links.href"));
        System.out.println(js.get("value._href"));
        System.out.println(js.get("value.id"));
        System.out.println(id);
        Assert.assertTrue(js.get("value.id").toString().contains(listID));



    }

    @And("status code is \"(.*)\"$")
    public void verifyStatusCode(String code){

        int nCode = Integer.parseInt(code);
        System.out.println(code);

        response.then().assertThat().statusCode(nCode);

    }

}
