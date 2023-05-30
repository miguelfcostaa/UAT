package ubiquitous.main;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {

    Button SignInButton;
    EditText LoginUsername, LoginPassword;
    CheckBox RememberMeCheckBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_login);

        SignInButton = findViewById(R.id.SignInButton);
        LoginUsername = findViewById(R.id.LoginUsername);
        LoginPassword = findViewById(R.id.LoginPassword);
        RememberMeCheckBox = findViewById(R.id.RememberMeCheckBox);

        Database database = new Database(LoginActivity.this);

        SignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String usernameLogin = LoginUsername.getText().toString();
                String passwordLogin = LoginPassword.getText().toString();

                SQLiteDatabase db = database.getReadableDatabase();

                String tableName = "CUSTOMER_TABLE";
                String[] columns = {"CUSTOMER_USERNAME", "CUSTOMER_PASSWORD"};

                // Define the WHERE clause
                String selection = "CUSTOMER_USERNAME = ? AND CUSTOMER_PASSWORD = ?";
                String[] selectionArgs = {usernameLogin, passwordLogin};

                // Perform the query
                Cursor cursor = db.query(tableName, columns, selection, selectionArgs, null, null, null);

                // Check if a matching record was found
                if (cursor.moveToFirst()) {
                    Toast.makeText(LoginActivity.this, "LOGIN WAS SUCCESSFUL", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginActivity.this, PrincipalMenuActivity.class);
                    startActivity(intent);

                } else {
                    Toast.makeText(LoginActivity.this, "Invalid password.", Toast.LENGTH_SHORT).show();
                }

                cursor.close();
                db.close();
            }
        });
    }
}