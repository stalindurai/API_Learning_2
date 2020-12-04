package steps;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import java.io.File;

public class StepDefinitions {

    String createProduct = "/v1/catalogs/products";
    File bodyContent1 = new File("src/test/java/body.json");
    File jsFile;

    Response response;
    String exp="PROD-", actual, resp;

    public String token = "A21AAIFtR-un3mSmNjqXD4dhUG6vetksD5o8ZtbVw2yckCZFTl95stShZv_rYiaCF-_PBat6SzY-kOCEYqsf3K0jkWGQ9-7yg";

    @Given("env is set")
    public void setEnv(){

        RestAssured.baseURI = "https://api.sandbox.paypal.com"+createProduct;

    }

    @When("POST request is made \"(.*)\"$")
    public void postRequest(String bodyContent){

        jsFile = new File(bodyContent);

        response = RestAssured.given().header("Authorization","Bearer "+token)
                .header("Content-Type", "application/json")
                .header("PayPal-Request-Id", "PRODUCT-18062020-001")
                .contentType(ContentType.JSON)
                .body(jsFile)
                .post();

        response.prettyPrint();

    }

    @Then("product is created")
    public void verifyProductID(){

        resp = response.asString();
        JsonPath js = new JsonPath(resp);
        actual = js.get("id");
        Assert.assertTrue(actual.contains(exp));
    }

    @Then("status code is 200")
    public  void verifyStatusCode(){

        response.then().statusCode(200);
    }

    @And("response type is JSON")
    public void verifyContentType(){

        response.then().contentType(ContentType.JSON);
    }


}
