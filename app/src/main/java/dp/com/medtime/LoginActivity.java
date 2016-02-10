package dp.com.medtime;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import info.hoang8f.widget.FButton;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    EditText username,password;
    FButton login;
    MedicineDatabaseHelper helper;
    SQLiteDatabase db;
    SharedPreferences prefs;
    String prefName = "Preferences";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login1);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        username=(EditText)findViewById(R.id.logUsername);
        password=(EditText)findViewById(R.id.logPassword);
        login=(FButton)findViewById(R.id.logLogin);
        username.setOnClickListener(this);
        password.setOnClickListener(this);
        login.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.logUsername:
                username.setText("");
                break;
            case R.id.logPassword:
                password.setText("");
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                break;
            case R.id.logLogin:
                try {
                    boolean found=false;
                    helper = new MedicineDatabaseHelper(this);
                    db = helper.getReadableDatabase();
                    Cursor c=db.rawQuery("SELECT * FROM USERS WHERE USERNAME=?",new String[]{username.getText().toString()});
                    c.moveToFirst();
                    while(!c.isAfterLast()){
                        if(c.getString(1).equals(password.getText().toString())){
                            found=true;
                        }

                        c.moveToNext();
                    }
                    c.close();
                    if(found) {
                        Intent i = new Intent(this, AddMedActivity.class);
                        prefs=getSharedPreferences(prefName,MODE_PRIVATE);
                        SharedPreferences.Editor editor=prefs.edit();
                        editor.putString("user",username.getText().toString());
                        editor.commit();
                        startActivity(i);
                    }
                }catch(SQLiteException e){
                    Toast.makeText(this, "Could not write into database", Toast.LENGTH_LONG).show();
                }
                Toast.makeText(this,"You have not registered.Please register.",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
