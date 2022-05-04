

import Segments.Database;
import Segments.Utils;
import com.mongodb.MongoException;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import javax.xml.crypto.Data;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;

public class Main {

    /*
    public static JSONParser jsonParser = new JSONParser();
    public static URL getResource(String fileName){ return Main.class.getClassLoader().getResource(fileName); }
*/
    /*TODO: CLEAN THIS UP*/

    public static void main(String[] args) {
        Database database = new Database();
        database.connect();
        database.createCollection("Accounts");
        database.createCollection("Test");
        database.addDocument("Test", new Document("tEST", "Test"));
        //System.out.println();
    }
}
