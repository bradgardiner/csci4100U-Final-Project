package ca.uoit.csci4100u.mapsdemo;

/**
 * Created by bradg on 2017-12-13.
 */

public class User{
    String username;
    String email;
    String password;
    boolean runner;

    public User(String username, String email, String password, boolean runner){
        this.username = username;
        this.email =  email;
        this.password = password;
        this.runner = runner;
    }


    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public boolean isRunner() {
        return runner;
    }
}
