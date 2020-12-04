package hooks;

import io.cucumber.java.Before;
import io.restassured.RestAssured;
import org.testng.annotations.BeforeSuite;

public class CucumberAnnotation {

    @Before
    public void beforeExecution(){

        RestAssured.baseURI = "https://api.sandbox.paypal.com/v1/catalogs/products";
    }
}
