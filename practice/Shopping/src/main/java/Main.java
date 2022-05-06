

import Segments.Database;
import Segments.Register.Register;
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
import java.util.Scanner;

public class Main {

    /*
    public static JSONParser jsonParser = new JSONParser();
    public static URL getResource(String fileName){ return Main.class.getClassLoader().getResource(fileName); }
*/
    /*TODO: CLEAN THIS UP*/

    public static void main(String[] args) {
        /* Init */
        Database database = new Database();
        Register register = new Register(database);
        Scanner scanner = new Scanner(System.in);

        /* Database */
        try {
            database.connect();
            database.createCollection("Accounts");
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        System.out.println("Would you like to 'Login' or 'Register' ");
        String answer = scanner.next();

        if ( answer.equalsIgnoreCase("Register") ) {
            register.create(true).upload();
            return;
        }

        if ( answer.equalsIgnoreCase("Login") ) {
            database.findDocument("Accounts", "Username", "Hoar");
        }

        //database.createCollection("Test");
        //database.addDocument("Test", new Document("tEST", "Test"));
        //System.out.println();
    }
}
