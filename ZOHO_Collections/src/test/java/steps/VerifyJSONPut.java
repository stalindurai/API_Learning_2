package steps;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.testng.annotations.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class VerifyJSONPut {

    String updateCust = "UpdateCustomer.json";
    String createCust = "CreateCustomer.json";
    String updateCust2 = "UpdateCustomer.json";
//    public static String updateCust = "/Users/stalindurai/Downloads/API_Projects/src/test/java/data/UpdateCustomer.json";


    public static void main(String[] args) {
//        verifyStatus();
    }

    @Test
    public void verifyState() throws IOException, ParseException {


        File file = new File(createCust);
        FileReader reader = new FileReader(updateCust);

        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(reader);

        jsonObject.put("display_name", "Love Games");

    }
}
