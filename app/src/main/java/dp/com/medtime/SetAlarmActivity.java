package dp.com.medtime;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.RadioButton;

import java.util.Calendar;

import info.hoang8f.widget.FButton;

public class SetAlarmActivity extends AppCompatActivity implements View.OnClickListener{
    FButton setAlarm;
    RadioButton morning,afternoon,night;
    EditText medName,medDays;
    EditText dosage1,dosage2,dosage3;
    AlarmManager alarmManager;
    PendingIntent pendingIntent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_alarm);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        setAlarm=(FButton)findViewById(R.id.setAlarm);
        morning=(RadioButton)findViewById(R.id.morning);
        afternoon=(RadioButton)findViewById(R.id.afternoon);
        night=(RadioButton)findViewById(R.id.night);
        medName=(EditText)findViewById(R.id.medName);
        medDays=(EditText)findViewById(R.id.medDays);
        dosage1=(EditText)findViewById(R.id.dosage1);
        dosage2=(EditText)findViewById(R.id.dosage2);
        dosage3=(EditText)findViewById(R.id.dosage3);
        dosage1.setEnabled(false);
        dosage2.setEnabled(false);
        dosage3.setEnabled(false);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.setAlarm:
                alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
                Intent myIntent = new Intent(this, AlarmReceiver.class);
                pendingIntent = PendingIntent.getBroadcast(this, 0, myIntent, 0);
                Calendar calendar = Calendar.getInstance();
                calendar.set(Calendar.HOUR_OF_DAY, 8);
                calendar.set(Calendar.MINUTE, 0);
                alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);
                break;
            case R.id.morning:
                dosage1.setEnabled(true);
                dosage1.setText("");
                break;
            case R.id.afternoon:
                dosage2.setEnabled(true);
                dosage2.setText("");
                break;
            case R.id.night:
                dosage3.setEnabled(true);
                dosage3.setText("");
                break;
            case R.id.medName:
                medName.setText("");
                break;
            case R.id.medDays:
                medDays.setText("");
                break;
        }
    }
}
