package steps;


import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.cucumber.messages.internal.com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.path.json.JsonPath;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;

public class StepDefinitions {

    String token = "1000.e7bc6366f17094be7ccd2886546265c1.97d8beb4f177ee09901785d04c01a55e";
    Response response;
    String createCust = "CreateCustomer.json";
    String updateCust = "UpdateCustomer.json";
    public static String newCust;

    @Given("env is set")
    public void setEnv(){

        RestAssured.baseURI = "https://subscriptions.zoho.com/api/v1/";

    }

    @When("POST for Create Customer is made")
    public void createCustomer() throws IOException, ParseException {

        File file = new File(createCust);

        response = RestAssured.given()
                    .header("Authorization", "Bearer "+token)
                    .basePath("customers")
                    .body(file)
                    .post();

        response.prettyPrint();

        JsonPath js = new JsonPath(response.asString());
        newCust = js.get("customer.customer_id");
        System.out.println("This is the new customer "+newCust);

    }

    @When("GET for a Customer is made")
    public void getCustomer(){

        String cust = "customers/"+newCust;
        RestAssured.baseURI =  RestAssured.baseURI+cust;

        response = RestAssured.given()
                    .header("Authorization", "Bearer "+token)
                    .get();

        response.prettyPrint();

    }

    @When("PUT for a specific Customer is made")
    public void updateCustomer(){

        File file = new File(updateCust);

        String cust = "customers/"+newCust;
        RestAssured.baseURI =  RestAssured.baseURI+cust;

        response = RestAssured.given()
                .header("Authorization", "Bearer "+token)
                .body(file)
                .put();

        response.prettyPrint();

    }


    @Then("response is \"(.*)\"$")
    public void verifyResponseCode(String code){

        response.then()
                .assertThat().statusCode(Integer.parseInt(code));
    }

}
