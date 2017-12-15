package ca.uoit.csci4100u.mapsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bradg on 2017-12-10.
 */

public class SignUpActivity extends AppCompatActivity{

    UserHelper uh;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_up);

        uh = new UserHelper(this);

    }

    public void sign_up(View view){
        //Check username and email against table
        //if there's a match, show an error message

        EditText userText = (EditText) findViewById(R.id.user_name);
        EditText emailText = (EditText) findViewById(R.id.email);
        EditText passText = (EditText) findViewById(R.id.user_password);
        EditText confirmText = (EditText) findViewById(R.id.user_password_confirm);

        String username = userText.getText().toString();
        String email = emailText.getText().toString();
        String password = passText.getText().toString();
        String confirmPass = confirmText.getText().toString();


        List<User> users = uh.getAllUsers();

        List<String> usernames = new ArrayList<>();

        for (int i = 0; i < users.size(); i++){
            usernames.add(users.get(i).getUsername());
        }

        if (usernames.contains(username)){
            Log.i("Username already exists" , "Try another.");
        }
        else{
            if(password.equals(confirmPass)){
                uh.createUser(username, email, password, "");
                //launch main intent
                Intent intent = new Intent(this, SearchActivity.class);
                intent.putExtra("username",username);
                startActivity(intent);
            }
            else{
                Log.i("Passwords dont match", "please try again");
            }
        }
    }

}
