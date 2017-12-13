package ca.uoit.csci4100u.mapsdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.maps.SupportMapFragment;

/**
 * Created by bradg on 2017-12-10.
 */

public class SignInActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign_in);


    }

    public void launch_sign_up(View view){
        //go to sign up view
    }

    public void sign_in(View view){
        //Check username and password against table of users
        //if there is a match, check user type
        //for each user type go to a different view

    }

}
