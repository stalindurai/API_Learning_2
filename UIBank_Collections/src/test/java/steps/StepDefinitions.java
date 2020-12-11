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

import java.io.*;
import java.util.Properties;

public class StepDefinitions {

    Response response;
    String token = "XeSv4Na0qVxc5FxlF48OSMkIVZjn5HIC7APtkOQqM22KsnBAZBC3cxxFYMHtKcma";
    String createAcct = "CreateAccount.json";
    String applyLoan = "ApplyLoan.json";
    String transferAmt = "TransferFunds.json";

    String getLoanDetails = "/api/quotes/";
    public static String accountID, quoteID, expTerm, expAmt;
    public static int balance;

    @Given("env is set")
    public void setEnv() throws FileNotFoundException, IOException {

        Properties prop = new Properties();
        prop.load(new FileInputStream(new File("src/test/java/resources/config.properties")));



        RestAssured.baseURI = "https://"+prop.getProperty("server");

    }

    @When("POST request is made")
    public void postRequest(){

        File file = new File(createAcct);

        response = RestAssured.given()
                    .header("Authorization", token)
                    .header("Content-Type", "application/json")
                    .pathParam("id", "/api/accounts")
                    .body(file)
                    .post("{id}");

        response.prettyPrint();

    }

    @When("POST request for transfer is made")
    public void transferFunds() throws IOException, ParseException {

        File file = new File(transferAmt);
        FileReader reader = new FileReader(transferAmt);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

        balance = Integer.parseInt(jsonObject.get("balance").toString());
        balance = balance-5;


        response = RestAssured.given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .pathParam("id", "/api/transactions")
                .body(file)
                .post("{id}");

        response.prettyPrint();


    }

    @When("POST request for new loan is made")
    public void postLoanRequest() throws IOException, ParseException {

        File file = new File(applyLoan);
        FileReader reader = new FileReader(applyLoan);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

        expAmt = jsonObject.get("amount").toString();
        System.out.println("this is expAmt "+expAmt);

        expTerm = jsonObject.get("term").toString();
        System.out.println("this is expTerm "+expTerm);

        response = RestAssured.given()
                .header("Authorization", token)
                .header("Content-Type", "application/json")
                .pathParam("id", "/api/quotes/newquote")
                .body(file)
                .post("{id}");

        response.prettyPrint();

    }

    @When("GET request is made")
    public void getRequest(){

        response = RestAssured.given()
                    .header("Authorization", token)
                    .pathParam("id", "/api/accounts")
                    .queryParam("filter[where][userId]", "5f2f88d271c2160044efb89d")
                    .get("{id}");


        response.prettyPrint();

    }

    @When("GET request for loan details is made")
    public void getLoanDetails(){

        response = RestAssured.given()
                    .header("Authorization", token)
                    .pathParam("id", getLoanDetails)
                    .get("{id}");

        response.prettyPrint();

    }


    @Then("response code is 200")
    public void verifyResponseCode(){

        response.then().assertThat().statusCode(200);


    }

    @And("verify loan details")
    public void verifyLoanDetails(){

        JsonPath js = new JsonPath(response.asString());
        quoteID = js.getString("quoteid");

        response = RestAssured.given()
                    .header("Authorization", token)
                    .pathParam("id", getLoanDetails+quoteID)
                    .get("{id}");

        response.prettyPrint();
        JsonPath js2 = new JsonPath(response.asString());
        String amt = js2.getString("amount");
        String term = js2.getString("term");
        String accept = js2.getString("accepted");

        Assert.assertTrue(amt.equals(expAmt));
        Assert.assertTrue(term.equals(expTerm));
        Assert.assertTrue(accept.equals("true"));


    }

    @And("verify balance in from account")
    public void verifyTranBalance() throws IOException, ParseException {

        JsonPath js = new JsonPath(response.asString());
        String remBalance = js.getString("balance");
        Assert.assertTrue(remBalance.equals(Integer.toString(balance)));

        File file = new File("TransferFunds.json");
        FileReader reader = new FileReader("TransferFunds.json");

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

        jsonObject.put("balance", remBalance);

    }

}
