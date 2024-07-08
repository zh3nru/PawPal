package ziac.learning.pawpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class activitySplashScreen extends AppCompatActivity {

    private static int SPLASH_SCREEN = 5000;

    // Animation Variables
    Animation animTop, animBot;
    ImageView imageTitleLogo;
    TextView textDescription;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Hooks
        animTop = AnimationUtils.loadAnimation(this,R.anim.splashscreen_topanim);
        animBot = AnimationUtils.loadAnimation(this,R.anim.splashscreen_bottomanim);

        imageTitleLogo = findViewById(R.id.imageTitleLogo);
        textDescription = findViewById(R.id.textDAL);

        imageTitleLogo.setAnimation(animTop);
        textDescription.setAnimation(animBot);

        // Create Instance and Table of Local Database
        dbHelper databaseHelper = new dbHelper(this);
        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(dbCredentialsTable.COLUMN_ID, "69");
        values.put(dbCredentialsTable.COLUMN_EMAIL, "admin@admin.com");
        values.put(dbCredentialsTable.COLUMN_BIRTHDATE, "69/69/69");
        values.put(dbCredentialsTable.COLUMN_PASSWORD, "admin");
        long newRowId = db.insert(dbCredentialsTable.TABLE_NAME, null, values);
        // Check if the insertion was successful
        if (newRowId != -1) {
            // Successful insertion
            Toast.makeText(this, "Data inserted successfully", Toast.LENGTH_SHORT).show();
        } else {
            // Failed insertion
            Toast.makeText(this, "Failed to insert data", Toast.LENGTH_SHORT).show();
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(activitySplashScreen.this, activityLogin.class);
                startActivity(intent);
                overridePendingTransition(R.anim.intent_enter, R.anim.intent_exit);
                finish();
            }
        }, SPLASH_SCREEN);
    }
}