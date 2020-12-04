package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.File;

public class StepDefinitions {

    Response response;
    String token = "sAu6pYN9lhI2bsN6Rqbv9NTm3uanXDz5rMSRa95i5NPHnc8olze5wrk0AfIduyVnP89G68ySjVqgkA5uxdbuh448mi";
    String createChannel = "CreateChannel.json";

    @Given("env is set")
    public void setEnv(){

        RestAssured.baseURI = "https://api.amio.io/v1/channels";

    }

    @When("GET is made")
    public void getChannels(){

        response = RestAssured.given()
                    .header("Authorization", "Bearer "+token)
                    .get();

        response.prettyPrint();

    }

    @Then("response code is \"(.*)\"$")
    public void verifyCode(String code){


        response.then().assertThat().statusCode(Integer.parseInt(code));
    }

    @When("POST for channel is made")
    public void postCreateChannel(){

        File file = new File(createChannel);

        response = RestAssured.given()
                    .header("Authorization", "Bearer "+token)
                    .body(file)
                    .post();

        response.prettyPrint();

    }


}
