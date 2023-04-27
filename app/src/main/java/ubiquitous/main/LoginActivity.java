package ubiquitous.main;

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
    CheckBox RemeberMeCheckBox;

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
        RemeberMeCheckBox = findViewById(R.id.RemeberMeCheckBox);

        SignInButton.setOnClickListener(new View.OnClickListener() {
             @Override
            public void onClick(View v) {

                 Client_Model client_model = new Client_Model(-1,LoginUsername.getText().toString(),LoginPassword.getText().toString(),RemeberMeCheckBox.isChecked());

                 Toast.makeText(LoginActivity.this, client_model.toString(),Toast.LENGTH_SHORT).show();
             }
        });
    }
}