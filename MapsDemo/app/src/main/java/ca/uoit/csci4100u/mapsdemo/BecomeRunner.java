package ca.uoit.csci4100u.mapsdemo;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * Created by Brad on 2017-12-15.
 */

public class BecomeRunner extends Activity {

    TextView txt = (TextView) findViewById(R.id.agreement);


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.become_runner_activity);
        String data = getIntent().getStringExtra("DATA");

        txt.setText(data);

    }

    public void accept(View view){
        finish();
    }
}
