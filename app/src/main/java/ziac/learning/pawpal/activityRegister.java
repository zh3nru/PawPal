package ziac.learning.pawpal;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class activityRegister extends AppCompatActivity {

    private EditText inputUserBirthday;
    private EditText inputUserId;
    private EditText inputUserPassword;
    private dbHelper dbHelper;
    private ImageButton btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        dbHelper = new dbHelper(this);
        inputUserBirthday = findViewById(R.id.inputuserbirthday);
        inputUserId = findViewById(R.id.inputuserid);
        inputUserPassword = findViewById(R.id.inputuserpassword);
        btnSignUp = findViewById(R.id.signupbutton);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                signUp();
            }
        });
    }

    private void signUp() {
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Get the values from the EditText fields
        String userBirthday = inputUserBirthday.getText().toString();
        String userId = inputUserId.getText().toString();
        String userPassword = inputUserPassword.getText().toString();

        // Create a ContentValues object to store the values
        ContentValues values = new ContentValues();
        values.put(dbCredentialsTable.COLUMN_BIRTHDATE, userBirthday);
        values.put(dbCredentialsTable.COLUMN_ID, userId);
        values.put(dbCredentialsTable.COLUMN_PASSWORD, userPassword);

        // Insert the values into the database
        long newRowId = db.insert(dbCredentialsTable.TABLE_NAME, null, values);

        if (newRowId != -1) {
            // Sign-up successful
            Toast.makeText(this, "Sign-up successful", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(activityRegister.this, activityLogin.class));
            overridePendingTransition(R.anim.intent_enter, R.anim.intent_exit);
            finish();
        } else {
            // Sign-up failed
            Toast.makeText(this, "Sign-up failed", Toast.LENGTH_SHORT).show();
        }
    }
}