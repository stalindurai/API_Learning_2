package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;

public class StepDefinitions {

    Response response;
    String token = "b9d82cc85c32c4b7d1b0d292632160fa4f3ed2625aab594332af25c32c561f82";
    String createProj = "CreateProject.json";
    String createClient = "CreateClient.json";
    String createTime = "CreateTimeEntry.json";
    public static String client_id, project_id;

    @Given("env is set")
    public void setEnv(){

        RestAssured.baseURI = "https://api.freshbooks.com/";
    }

    @When("GET request for all Time Entries")
    public void getAllTimeEntries(){

        response = RestAssured.given()
                    .header("Authorization", "Bearer "+token)
                    .basePath("timetracking/business/7004263/time_entries")
                    .get();

        response.prettyPrint();

    }

    @When("POST request for Client is made")
    public void createNewClient(){

        File file = new File(createClient);

        response = RestAssured.given()
                .header("Authorization", "Bearer "+token)
                .basePath("accounting/account/X4kp1Z/users/clients")
                .body(file)
                .post();

        response.prettyPrint();

        JsonPath js = new JsonPath(response.asString());
//        client_id = js.get(Integer.toString(\"response.result.client.id"\"));


    }

    @When("POST request for Projects is made")
    public void createNewProject(){

        File file = new File(createProj);

        RestAssured.baseURI = RestAssured.baseURI+"projects/business/7004263/projects";

        response = RestAssured.given()
                .header("Authorization", "Bearer "+token)
                .body(file)
                .post();

        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        JsonPath js = new JsonPath(response.asString());
        project_id = js.get("project.id");


    }

    @When("POST request for Time Entry is made")
    public void createTimeEntry(){

        File file = new File(createTime);
        RestAssured.baseURI = RestAssured.baseURI+"timetracking/business/7004263/time_entries";

        response = RestAssured.given()
                .header("Authorization", "Bearer "+token)
                .body(file)
                .post();

        response.prettyPrint();

        response.then().assertThat().statusCode(200);

        JsonPath js = new JsonPath(response.asString());
        project_id = js.get("time_entry.id");
        System.out.println(project_id);


    }

    @Then("response code is \"(.*)\"$")
    public void verifyStatusCode(String code){

        response.then().assertThat().statusCode(Integer.parseInt(code));



    }
}
