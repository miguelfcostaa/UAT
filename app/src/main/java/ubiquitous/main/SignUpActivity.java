package ubiquitous.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    Button CreateAccountButton, SignInFromSignUpButton;
    EditText CreateUsername, CreateEmail, CreatePassword, ConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_sign_up);

        CreateAccountButton = findViewById(R.id.CreateAccountButton);
        SignInFromSignUpButton = findViewById(R.id.SignInFromSignUpButton);
        CreateUsername = findViewById(R.id.CreateUsername);
        CreateEmail = findViewById(R.id.CreateEmail);
        CreatePassword = findViewById(R.id.CreatePassword);
        ConfirmPassword = findViewById(R.id.ConfirmPassword);

        CreateAccountButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomerModel customer_model;
                try {
                    customer_model = new CustomerModel(-1, CreateUsername.getText().toString(), CreateEmail.getText().toString(),
                            CreatePassword.getText().toString(),ConfirmPassword.getText().toString());
                    Toast.makeText(SignUpActivity.this, customer_model.toString(),Toast.LENGTH_SHORT).show();
                }
                catch (Exception e){
                    Toast.makeText(SignUpActivity.this, "ERROR SAVING USER",Toast.LENGTH_SHORT).show();
                    customer_model = new CustomerModel(-1,"error","error@mail.com","-","-");
                }

                DatabaseCustomer databaseCustomer = new DatabaseCustomer(SignUpActivity.this);
                boolean success = databaseCustomer.addOne(customer_model);
                Toast.makeText(SignUpActivity.this, "SUCCESS", Toast.LENGTH_SHORT).show();
            }
        });



    }

    public void goToSignIn(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}