import Segments.Database.Database;
import Segments.Register.Register;
import java.util.Scanner;

public class Main {
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
            database.findDocument("Accounts", "Username", "Hoar"); // not working properly
        }

        //database.createCollection("Test");
        //database.addDocument("Test", new Document("tEST", "Test"));
        //System.out.println();
    }
}
