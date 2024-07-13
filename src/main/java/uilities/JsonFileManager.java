package uilities;

import com.jayway.jsonpath.JsonPath;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.Assert;

import java.io.FileReader;
import java.io.IOException;

public class JsonFileManager {
    String jsonReader;
    String jsonFilePath;

    public JsonFileManager(String jsonFilePath) {
        this.jsonFilePath = jsonFilePath;
        try {
            JSONObject data = (JSONObject) new JSONParser().parse(new FileReader(jsonFilePath));
            jsonReader = data.toJSONString();
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            Assert.fail(e.getMessage());
        }
    }

    public String getTestData(String jsonPath) {

        return JsonPath.read(jsonReader, jsonPath);
    }
}
