package Segments;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Objects;

public class Utils {

    public static URL getResource(String fileName){ return Utils.class.getClassLoader().getResource(fileName); }

    public static String readJson(String main, String name) {
        JSONParser jsonParser = new JSONParser();
        File file = null;
        JSONObject fileContent = null;
        try {
            file = new File(Objects.requireNonNull(Utils.class.getClassLoader().getResource("credentials.json")).toURI());
            Object object = jsonParser.parse(new FileReader(file.getAbsoluteFile()));
            fileContent = (JSONObject) object;
        } catch (URISyntaxException | IOException | ParseException e) {
            throw new RuntimeException(e);
        }
        return (String) ((JSONObject) fileContent.get(main)).get(name);
    }

}
