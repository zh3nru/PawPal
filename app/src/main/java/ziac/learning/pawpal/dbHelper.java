package ziac.learning.pawpal;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class dbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "UserCredentials.db";
    private static final int DATABASE_VERSION = 1;

    public dbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create the tables when the database is created
        db.execSQL(dbCredentialsTable.CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Perform any database schema changes or data migration here if needed
        // This method will be called when DATABASE_VERSION is increased
    }
}

