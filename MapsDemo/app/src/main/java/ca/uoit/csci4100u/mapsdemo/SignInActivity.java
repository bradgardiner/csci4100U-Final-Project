package ca.uoit.csci4100u.mapsdemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.SupportMapFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bradg on 2017-12-10.
 */

public class SignInActivity extends AppCompatActivity {

    UserHelper uh;
    String username;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);

         uh = new UserHelper(this);

         //uh.createUser("admin", "bradgardiner21@gmail.com", "admin", "run" );



    }

    public void launch_sign_up(View view){
        //go to sign up view
        Intent intent = new Intent(this,SignUpActivity.class);

        startActivity(intent);
    }

    public void sign_in(View view){
        //Check username and password against table of users
        //if there is a match, check user type
        //for each user type go to a different view

        EditText userText = (EditText) findViewById(R.id.user_name);
        EditText passwordText = (EditText) findViewById(R.id.user_password);

        username = userText.getText().toString();
        String password = passwordText.getText().toString();

        List<User> users = new ArrayList<>();
        users = uh.getAllUsers();

        User current = null;


        for (int i = 0; i < users.size(); i++){
            Log.i("Current user is: " ,  users.get(i).getUsername());
            Log.i(users.get(i).getPassword() + " = = = = ",  password);

            if(users.get(i).getUsername().equals(username)){
               if(users.get(i).getPassword().equals(password)){
                   Log.i("Password CORRECT:" , "**************");
                   //Start main intent of application
                   Intent intent = new Intent(this,SearchActivity.class);
                   intent.putExtra("username",username);
                   startActivity(intent);
               }
               else{
                   Log.i("Password incorrect", "Please try again");
                   Toast toast= Toast.makeText(getApplicationContext(),"Password or username Not correct",Toast.LENGTH_SHORT);
                   toast.show();


               }
            break;
           }
           else{
                Toast toast= Toast.makeText(getApplicationContext(),"Password or username Not correct",Toast.LENGTH_SHORT);
                toast.show();
            }
        }



    }

}
