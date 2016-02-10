package dp.com.medtime;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by d on 09-02-2016.
 */
public class MedicineDatabaseHelper extends SQLiteOpenHelper {
    private static String DB_NAME="medTime";
    private static int DB_VERSION=1;
    public MedicineDatabaseHelper(Context context) {
        super(context,DB_NAME,null,DB_VERSION);
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE USERS (" + "_id INTEGER PRIMARY KEY AUTOINCREMENT," + "USERNAME TEXT," + "PASSWORD TEXT,"
                + "EMAIL TEXT);");
        ContentValues values=new ContentValues();
        values.put("USERNAME", "guest");
        values.put("EMAIL", "guest");
        values.put("PASSWORD","guest");
        db.insert("USERS", null, values);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
