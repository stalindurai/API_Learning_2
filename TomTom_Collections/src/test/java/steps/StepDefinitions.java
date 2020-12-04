package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;

public class StepDefinitions {

    String apiKey = "cSSCDB73eGRvHlUvT3SusI2ybXzEMAAy";
    public static String adminKey = "M4oXhE9RHhY7IAxR4TWhch9pqHs683eTHaum08NGlcV3WtFr";
//    public String adminKey;
    String addProject = "project?adminKey="+adminKey+"&key="+apiKey;

    Response response;
    File file = new File("data.json");
    File file2 = new File("data2.json");

    @Given("env is set")
    public void setEnv(){

        RestAssured.baseURI = "https://api.tomtom.com/geofencing/1/projects/project";


    }

    @Given("env to regen admin key")
    public void setEnvRegen(){

        RestAssured.baseURI = "https://api.tomtom.com/geofencing/1/regenerateKey";

    }



    @When("post request is made")
    public void postRequest(){


        response = RestAssured.given()
                                .queryParam("key", apiKey)
                                .queryParam("adminKey", adminKey)
                            .header("Content-Type", "application/json")
                            .body(file)
                            .post();

         response.prettyPrint();
    }

    @When("post regen is made")
    public void postRequestRegen(){

         response = RestAssured.given()
                            .queryParam("key", apiKey)
                            .header("Content-Type", "application/json")
                            .body(file2)
                            .post();

         response.prettyPrint();

         JsonPath js = new JsonPath(response.asString());
         adminKey = js.get("adminKey");
    }


    @Then("verify status \"(.*)\"$")
    public void verifyStatusCode(int code){

        response.then().assertThat().statusCode(code);
    }

    @And("verify response type")
    public void verifyResponseType(){

        response.then().assertThat().contentType(ContentType.JSON);
    }

}
