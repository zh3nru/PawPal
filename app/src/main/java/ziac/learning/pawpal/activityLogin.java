package ziac.learning.pawpal;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.media.Image;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;


public class activityLogin extends AppCompatActivity {

    private dbHelper DBHelper;
    private EditText inputUserID;
    private EditText inputPassword;
    private ImageButton btnSignIn;
    private TextView btnRegister;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        DBHelper = new dbHelper(this);

        inputUserID = findViewById(R.id.inputuserid);
        inputPassword = findViewById(R.id.inputuserpassword);
        btnSignIn = findViewById(R.id.signinbutton);
        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkCredentials();
            }
        });

        btnRegister = findViewById(R.id.registerpage);
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle the click event
                startActivity(new Intent(activityLogin.this, activityRegister.class));
                overridePendingTransition(R.anim.intent_enter, R.anim.intent_exit);
                finish();
            }
        });
    }

    private void checkCredentials() {
        SQLiteDatabase db = DBHelper.getReadableDatabase();

        // Get the user ID and password from EditText fields
        String userIdToCheck = inputUserID.getText().toString();
        String passwordToCheck = inputPassword.getText().toString();

        // Define the query to check if the user ID and password combination exists
        String selection = dbCredentialsTable.COLUMN_ID + " = ? AND " + dbCredentialsTable.COLUMN_PASSWORD + " = ?";
        String[] selectionArgs = {userIdToCheck, passwordToCheck};

        // Execute the query
        Cursor cursor = db.query(dbCredentialsTable.TABLE_NAME, null, selection, selectionArgs, null, null, null);

        if (cursor != null && cursor.getCount() > 0) {
            // User ID and password combination exists
            // Perform your desired action, such as switching intent
            // For example, start a new activity
            startActivity(new Intent(this, MainActivity.class));
            cursor.close();
            overridePendingTransition(R.anim.intent_enter, R.anim.intent_exit);
            finish();
        } else {
            // User ID and/or password combination does not exist
            Toast.makeText(this, "Invalid credentials", Toast.LENGTH_SHORT).show();
        }
    }
}