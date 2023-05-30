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

        SignInButton.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View v) {
                /*
                 CustomerModel customer_model;
                 try {
                     customer_model = new CustomerModel(-1,LoginUsername.getText().toString(),LoginPassword.getText().toString(),RememberMeCheckBox.isChecked());
                     Toast.makeText(LoginActivity.this, customer_model.toString(),Toast.LENGTH_SHORT).show();
                 }
                 catch (Exception e){
                     Toast.makeText(LoginActivity.this, "ERROR SAVING USER",Toast.LENGTH_SHORT).show();
                     customer_model = new CustomerModel(-1,"error","0",false);
                 }

                 Database database = new Database(LoginActivity.this);
                 boolean success = database.addOne(customer_model);
                 Toast.makeText(LoginActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();*/
             }
        });
    }
}