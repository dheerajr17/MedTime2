package dp.com.medtime;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import info.hoang8f.widget.FButton;

public class AddMedActivity extends AppCompatActivity implements View.OnClickListener{
    FButton addMed,skipMed;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_med);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        addMed=(FButton)findViewById(R.id.addMed);
        skipMed=(FButton)findViewById(R.id.skipMed);
        skipMed.setOnClickListener(this);
        addMed.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.skipMed:
                Intent i=new Intent(this,BaseActivity.class);
                startActivity(i);
                break;
            case R.id.addMed:
                i=new Intent(this,SetAlarmActivity.class);
                startActivity(i);
                break;
        }
    }

}
