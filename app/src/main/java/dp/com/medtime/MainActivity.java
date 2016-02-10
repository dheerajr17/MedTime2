package dp.com.medtime;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import info.hoang8f.widget.FButton;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    FButton mainLogin,mainSkip,mainSignUp;
    MedicineDatabaseHelper helper;
    SharedPreferences prefs;
    String prefName = "Preferences";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        helper=new MedicineDatabaseHelper(this);
        mainLogin=(FButton)findViewById(R.id.mainLogin);
        mainSkip=(FButton)findViewById(R.id.mainSkip);
        mainSignUp=(FButton)findViewById(R.id.mainSignUp);
        mainLogin.setOnClickListener(this);
        mainSkip.setOnClickListener(this);
        mainSignUp.setOnClickListener(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.mainLogin:
                Intent i=new Intent(this,LoginActivity.class);
                startActivity(i);
                break;
            case R.id.mainSignUp:
                i = new Intent(this, SignUpActivity.class);
                startActivity(i);
                break;
            case R.id.mainSkip:
                i = new Intent(this, AddMedActivity.class);
                prefs=getSharedPreferences(prefName,MODE_PRIVATE);
                SharedPreferences.Editor editor=prefs.edit();
                editor.putString("user","guest");
                editor.commit();
                startActivity(i);
                break;
        }
    }
}
