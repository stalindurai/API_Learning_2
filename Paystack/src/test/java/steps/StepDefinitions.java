package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.config.EncoderConfig;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class StepDefinitions {

    String token = "sk_test_d10560c705c1c402ff1e2406d88135e698261251";
    Response response;

    @Given("env is set")
    public void setEnv(){

        RestAssured.baseURI = "https://api.paystack.co/";

    }

    @When("POST request is made")
    public void postCreatePage(){

        response = RestAssured.given()
                    .header("Authorization", "Bearer "+token)
                    .header("Content-Type", "application/json")
                    .config(RestAssured.config()
                    .encoderConfig(EncoderConfig.encoderConfig()
                    .encodeContentTypeAs("x-www-form-urlencoded", ContentType.URLENC)))
                    .contentType("application/x-www-form-urlencoded; charset=UTF-8")
                    .formParam("name", "Monthly Practices")
                    .formParam("interval", "monthly")
                    .formParam("amount", "650000")
                    .pathParam("id", "page")
                    .post("{id}");

        response.prettyPrint();


    }

    @Then("response code is 200")
    public void verifyResponseCode(){

        response.then().assertThat().statusCode(200);
    }
}
