package ubiquitous.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button MainSignUpButton, MainLoginButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        getSupportActionBar().hide();

        setContentView(R.layout.activity_main);

        MainLoginButton = findViewById(R.id.MainLoginButton);
        MainSignUpButton = findViewById(R.id.MainSignUpButton);

        RestaurantModel restaurantModel = new RestaurantModel(-1, "Nusret","Istanbul", "234567891", "3,0");
        //RestaurantModel restaurantModel1 = new RestaurantModel(-1, "BeerGarden","Funchal, Madeira", "291 530 828", "4,8");

        DatabaseRestaurant databaseRestaurant = new DatabaseRestaurant(MainActivity.this);
        databaseRestaurant.addOne(restaurantModel);
        //databaseRestaurant.addOne(restaurantModel1);

    }

    public void goToLogin(View view) {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void goToSignUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }



}