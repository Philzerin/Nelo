package Segments.Register;

/*
* User Registration Class
* Enables registration into the application.
* 11:38pm 5/3/2022
* TODO: MONGODB, DELETE, ADD, MODIFY, AND SMS
* */

import Segments.Database.Database;
import org.bson.Document;

import java.util.Scanner;

public class Register {

    private Database database;
    private String username;
    private String password;
    private String email;
    private Scanner scanner = new Scanner(System.in);

    public Register(Database database){ this.database = database; }
    public Register create(String username,
                       String password,
                       String email) {
        this.username = username;
        this.password = password;
        this.email = email;

        return this;
    }
    public Register create(Boolean show) {
        if (show) {
            /* todo: Needs Validation */
            System.out.print("User Registration\n");
            System.out.print("Username: ");
            this.username = scanner.next();
            System.out.print("Password: ");
            this.password = scanner.next();
            System.out.print("Email: ");
            this.email = scanner.next();
        }
        return this;
    }
    public void upload(){
        database.addDocument("Accounts", new Document()
                .append("Username", username)
                .append("Password", password)
                .append("Email", email));
    }
}
