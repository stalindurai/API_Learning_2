package steps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class StepDefinitions {

//    String tokenURL = "https://sashinconsulting-dev-ed.my.salesforce.com/services/data/v50.0/sobjects/lead";
    String tokenURL = "https://ap16.salesforce.com/services/data/v50.0/sobjects/lead";

    @Given("env is set")
    public void setEnv(){

//        RestAssured.baseURI = "https://sashinconsulting-dev-ed.my.salesforce.com/services/data/v50.0/sobjects/lead";
//        RestAssured.baseURI = "https://ap16.salesforce.com/services/data/v50.0/sobjects/lead";

    }

    @When("post request is made")
    public void postRequest(){

        Response response =

                given()
                .header("Authorization","3MVG9sh10GGnD4DshF5KKroOU7.INHEW8W3ai0h3RY4MyOXRdVEaybEat4VaD0ap8Eo963JVKUxc.3Gq2a_pn:8928D8A3276CEBFC8AFAA0A12CEFA889549395BDEA805FEDFE9AFD260B9A57B9")
//                .header("Authorization", <client id>:<client secret>)
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type","authorization_code")
                .formParam("redirect_uri","https://sashinconsulting.com")
                .formParam("response_type","code")
//                .formParam("code", "00D4K000004hwZV!AQEAQGyLIMQCD2eCSwcv6VLlxe648OcFErrqUANSl2VaoQBYTnVtP2dVZFibMn61uLGjY9fPo99MnV2_djlA06.FeqNX8f5A")
                .formParam("code", "00D4K000004hwZV!AQEAQPvCmpCaTzOXQ9A48evHM74_KXvPIDcVqjkQgKpmWwhddVW3CeohqvT7M_UOxyzC5yGvHy8mSKSUKDKlXTsGQFyJebAF")
                .formParam("client_id", "3MVG9sh10GGnD4DshF5KKroOU7.INHEW8W3ai0h3RY4MyOXRdVEaybEat4VaD0ap8Eo963JVKUxc.3Gq2a_pn")
                .formParam("client_secret", "8928D8A3276CEBFC8AFAA0A12CEFA889549395BDEA805FEDFE9AFD260B9A57B9")
//                .formParam("code", "eyJraWQiOiIyMjgiLCJ0eXAiOiJKV1QiLCJhbGciOiJSUzI1NiJ9.eyJhdF9oYXNoIjoiQnVUX3BSWDhZSnRPN3ZoMTQ0bFpuQSIsInN1YiI6Imh0dHBzOi8vbG9naW4uc2FsZXNmb3JjZS5jb20vaWQvMDBENEswMDAwMDRod1pWVUFZLzAwNTRLMDAwMDAyU2Y2SFFBUyIsImF1ZCI6IjNNVkc5c2gxMEdHbkQ0RHNoRjVLS3JvT1U3LklOSEVXOFczYWkwaDNSWTRNeU9YUmRWRWF5YkVhdDRWYUQwYXA4RW85NjNKVktVeGMuM0dxMmFfcG4iLCJpc3MiOiJodHRwczovL2xvZ2luLnNhbGVzZm9yY2UuY29tIiwiZXhwIjoxNjA2NDkwMTA5LCJpYXQiOjE2MDY0ODk5ODl9.O57p7-M3o7TQAugNTjx7qJJs_NJjwFwzcWjWnvp1UX0s3tVH8jZu-a-3ksB47ulTjbDfaMupyZ5zBhQxdHNiaiAlOQiDbW17dBHsY6ONmCP58wZEh8Nucuk-RNwaXdsoyrKZQH_IR8c8ugwY-M22LodkPTgvmTy3A9Nf1p1vTK3EtCDGby78S6GUYFKBsMYYw7BZZhHC2WxSry55eQO_KZdC10ZwellW98ov22qbGjP2JzaPj38IjPCkIjjZATAolEv9c4j45E27Ehpc_xy7gDRROmmldIeeVhgyLOXC76oOnkemuM7O8kJCvTs1kNRMwe8GJxFJHAbvWIZTjHJGyKK2VhDlM078Jh5hL4iy6sx-fyVAu6l4i5ol_yQV1wcZfoHr27d4v-JZO65CbgfpEppxxgqcdN9n5uCUESZW9Hc5srgH3B8na6V5az2qFvR8fNsEJc2nGQYa9KlUxVFiREETAJcKen3eVhO8uecf-wjz55L6XPc-zQrH-JZ8VJgi9XJGwhERL1H1S4VB0-l03o2YtTRIfpM5nsfQBIUaR2xzBmwH8XUrvUKVtiMjtnoPdAoNMu3eHmA3uuWyYQw6rC-6Oy25017S9krEpMlNzPQFK6vKDHva8dU4guNcB5QNgTjf-Azf83Cs0rqZbikChM-X4oaCOjGIj0UVNf2LTDg")
//                .get(tokenURL+"oauth2/token");
                .get(tokenURL);

        response.prettyPrint();

                /*
                .header("Authorization",<client id>:<client secret>)
                .contentType("application/x-www-form-urlencoded")
                .formParam("grant_type","authorization_code")
                .formParam("redirect_uri",REDIRECT_URL)
                .formParam("response_type","code")
                .formParam("code", AUTHORIZATION_CODE)
                .formParam("client_id", CLIENT_ID) NA
                .formParam("client_secret", CLIENT_SECRET) NA
                .post(token_URL);
                */
    }
}
