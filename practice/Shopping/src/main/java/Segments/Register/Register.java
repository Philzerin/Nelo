package Segments.Register;

/*
* User Registration Class
* Enables registration into the application.
* 11:38pm 5/3/2022
* TODO: MONGODB, DELETE, ADD, MODIFY, AND SMS
* */

public class Register {

    private String username;
    private String password;
    private String email;
    public Register(){}

    public void create(String username,
                       String password,
                       String email) {
        this.username = username;
        this.password = password;
        this.email = email;
    }
    public void delete(){}
}
