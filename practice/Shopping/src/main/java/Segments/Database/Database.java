package Segments.Database;

import Segments.Utils;
import com.mongodb.ConnectionString;
import com.mongodb.MongoException;
import com.mongodb.client.*;
import org.bson.BsonDocument;
import org.bson.Document;
import org.bson.conversions.Bson;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Database {

    MongoClient mongoClient;
    MongoDatabase mongoDatabase;

    private final String dbName;
    private final String dbUser;
    private final String dbPass;

    private ArrayList<String> collection;

    public Database() {
        /* JSON IS CASE SENSITIVE if we input "username" it will not read the file or "database" */
        this.dbName = Utils.readJson("Database", "Name");
        this.dbUser = Utils.readJson("Database", "Username");
        this.dbPass = Utils.readJson("Database", "Password");
    }

    public void connect() {
        ConnectionString connectionString = new ConnectionString("mongodb+srv://" + dbUser + ":" +
                dbPass + "@shopping.buvzb.mongodb.net/" + dbName + "?" +
                "retryWrites=true&w=majority"); // Connection String
        try {
            mongoClient = MongoClients.create(connectionString); // Initializing MongoClient with MongoClient.create(ConnectionString)
            mongoDatabase = mongoClient.getDatabase(dbName); // Picking the database we would like to connect to
            collection = mongoDatabase.listCollectionNames().into(new ArrayList<>());
            if (mongoDatabase.runCommand(new Document("ping", 1)).containsValue(1)) { // Redundant, but nice to have.
                System.out.print("[Connection] Successful\n");
            }
        } catch (MongoException me) {
            /* Self Explanatory */
            System.out.print("[Connection] Failed\n");
            System.out.print("Print Stacktrace? ");
            Scanner scanner = new Scanner(System.in);
            boolean input = scanner.next().equalsIgnoreCase("yes");
            if (input) {
                me.printStackTrace();
            }
        }
    }

    public void createCollection(String collectionName) {
        if (collection.contains(collectionName)) {
            //System.out.println("[Database] A collection with the name '" + collectionName + "' already exists.");
        } else {
            System.out.println("[Database] Successfully created a collection with the name '" + collectionName + "'.");
            mongoDatabase.createCollection(collectionName);
        }
    }

    public void addDocument(String collectionName, Document document){
        if (collection.contains(collectionName)) {
            mongoDatabase.getCollection(collectionName).insertOne(document);
            //System.out.println("[Database] Successfully Inserted '" + document + "' into " + collectionName + ".");
        } else {
            System.out.println("[Database] The collection with the name '" + collectionName + "' does not exist.");
        }
    }

    public void addDocument(String collectionName, List<Document> document){
        if (collection.contains(collectionName)) {
            mongoDatabase.getCollection(collectionName).insertMany(document);
            //System.out.println("[Database] Successfully Inserted '" + document + "' into " + collectionName + ".");
        } else {
            System.out.println("[Database] The collection with the name '" + collectionName + "' does not exist.");
        }
    }

    public void removeDocument(String collectionName, Document document){
        if (collection.contains(collectionName)) {
            mongoDatabase.getCollection(collectionName).deleteOne(document);
            //System.out.println("[Database] Successfully Inserted '" + document + "' into " + collectionName + ".");
        } else {
            System.out.println("[Database] The collection with the name '" + collectionName + "' does not exist.");
        }
    }

    public void removeDocument(String collectionName, Bson document){
        if (collection.contains(collectionName)) {
            mongoDatabase.getCollection(collectionName).deleteMany(document);
            //System.out.println("[Database] Successfully Inserted '" + document + "' into " + collectionName + ".");
        } else {
            System.out.println("[Database] The collection with the name '" + collectionName + "' does not exist.");
        }
    }

    public void findDocument(String collectionName, Document document, String key){
        mongoDatabase.getCollection(collectionName).find(document).filter((Bson) new Document().get(key));
    }
    // not working properly
    public void findDocument(String collectionName, String key, String value){
        FindIterable<Document> test = mongoDatabase.getCollection(collectionName).find().filter(
                BsonDocument.parse("{" + key + ":'" + value + "'}"));
        test.comment("Testing");
    }
}
