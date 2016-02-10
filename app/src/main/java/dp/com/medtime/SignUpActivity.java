package dp.com.medtime;

import android.content.ContentValues;
import android.content.Intent;
import android.content.SharedPreferences;
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

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{
    EditText username,password,confirmPassword,email;
    FButton signUp;
    MedicineDatabaseHelper helper;
    SQLiteDatabase db;
    SharedPreferences prefs;
    String prefName = "Preferences";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        username=(EditText)findViewById(R.id.username);
        password=(EditText)findViewById(R.id.password);
        confirmPassword=(EditText)findViewById(R.id.confirmPassword);
        email=(EditText)findViewById(R.id.email);
        signUp=(FButton)findViewById(R.id.signUp);
        username.setOnClickListener(this);
        password.setOnClickListener(this);
        confirmPassword.setOnClickListener(this);
        email.setOnClickListener(this);
        signUp.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.username:
                username.setText("");
                break;
            case R.id.password:
                password.setText("");
                password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                break;
            case R.id.confirmPassword:
                confirmPassword.setText("");
                confirmPassword.setTransformationMethod(PasswordTransformationMethod.getInstance());
                break;
            case R.id.email:
                email.setText("");
                break;
            case R.id.signUp:
                if(password.getText().toString().equals(confirmPassword.getText().toString())) {
                    try {
                        helper = new MedicineDatabaseHelper(this);
                        db = helper.getWritableDatabase();
                        ContentValues values=new ContentValues();
                        values.put("USERNAME",username.getText().toString());
                        values.put("PASSWORD",password.getText().toString());
                        values.put("EMAIL",email.getText().toString());
                        db.insert("USERS",null,values);
                        Intent i = new Intent(this, AddMedActivity.class);
                        prefs=getSharedPreferences(prefName,MODE_PRIVATE);
                        SharedPreferences.Editor editor=prefs.edit();
                        editor.putString("user",username.getText().toString());
                        editor.commit();
                        startActivity(i);
                    }catch(SQLiteException e){
                        Toast.makeText(this,"Could not write into database",Toast.LENGTH_LONG).show();
                    }
                }
                else
                    Toast.makeText(this,"Retype password",Toast.LENGTH_LONG).show();
                break;
        }
    }
}
