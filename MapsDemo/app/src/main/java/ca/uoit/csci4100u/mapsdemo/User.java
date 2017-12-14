package ca.uoit.csci4100u.mapsdemo;

/**
 * Created by bradg on 2017-12-13.
 */

public class User{
    String username;
    String email;
    String password;
    String runner;
    private long id;

    public User(String username, String email, String password, String runner){
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

    public String isRunner() {
        return runner;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
