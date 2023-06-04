package ubiquitous.main;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.widget.Toolbar;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import com.google.android.material.navigation.NavigationView;

import androidx.navigation.Navigation;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;


import java.util.ArrayList;
import java.util.List;

import ubiquitous.main.databinding.ActivityNavigationDrawerBinding;

public class NavigationDrawerActivity extends AppCompatActivity {

    private LinearLayout restaurantContainer;
    private NavController navController;
    private NavigationView navigationView;
    private NavigationView navigationView2;
    private DrawerLayout drawer;

    List<RestaurantModel> restaurantList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        ActivityNavigationDrawerBinding binding = ActivityNavigationDrawerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        setSupportActionBar(binding.appBarNavigationDrawer.toolbar);  // Set the toolbar as the action bar

        NavHostFragment navHostFragment = (NavHostFragment) getSupportFragmentManager().findFragmentById(R.id.mobile_navigation);
        navController = Navigation.findNavController(this, R.id.mobile_navigation);

        DatabaseCustomer databaseCustomer = new DatabaseCustomer(this);
        CustomerModel loggedInCustomer = databaseCustomer.getLoggedInCustomer();

        drawer = findViewById(R.id.drawer_layout);

        EditText search_nav_bar = findViewById(R.id.search_nav_bar);
        Button search_button = findViewById(R.id.search_button);

        View headerView = binding.navView.getHeaderView(0);

        TextView profile_name = headerView.findViewById(R.id.profile_name);
        TextView profile_email = headerView.findViewById(R.id.profile_email);

        Log.d("DEBUG", "profile_name: " + profile_name);
        Log.d("DEBUG", "profile_email: " + profile_email);

        profile_name.setText(loggedInCustomer.getUsername());
        profile_email.setText(loggedInCustomer.getEmail());


        search_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String query = search_nav_bar.getText().toString();
                performSearch(query);
            }
        });

        restaurantContainer = findViewById(R.id.linearLayout);

        restaurantList = getDataFromDatabase();

        for (RestaurantModel restaurant : restaurantList) {
            addRestaurantView(restaurant);
        }

        navigationView = findViewById(R.id.nav_view);

        setupNavigationDrawer();
        setupDestinationChangeListener();

        //-------------------------------------------------------------------------------

        navigationView2 = findViewById(R.id.nav_view2);
        // Set click listener for the second NavigationView
        navigationView2.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                handleMenuItemSelection(item);
                return true;
            }
        });

        ImageView nav_bar_profile = findViewById(R.id.nav_bar);

        nav_bar_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                drawer.openDrawer(GravityCompat.END);
            }
        });
    }


    private void performSearch(String query) {
        restaurantContainer.removeAllViews(); // Clear existing views

        for (RestaurantModel restaurant : restaurantList) {
            if (restaurant.getName().toLowerCase().contains(query.toLowerCase())) {
                addRestaurantView(restaurant);
            }
        }
    }

    private void addRestaurantView(RestaurantModel restaurant) {
        View restaurantView = getLayoutInflater().inflate(R.layout.restaurant_item, null);

        TextView nameTextView = restaurantView.findViewById(R.id.nameTextView);
        TextView locationTextView = restaurantView.findViewById(R.id.locationTextView);
        TextView ratingTextView = restaurantView.findViewById(R.id.ratingTextView);

        nameTextView.setText(restaurant.getName());
        locationTextView.setText(restaurant.getLocation());
        ratingTextView.setText(restaurant.getRating());

        restaurantContainer.addView(restaurantView);
    }


    private List<RestaurantModel> getDataFromDatabase() {
        restaurantList = new ArrayList<>();

        DatabaseRestaurant databaseRestaurant = new DatabaseRestaurant(this);
        SQLiteDatabase db = databaseRestaurant.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseRestaurant.RESTAURANT_TABLE, null);
        if (cursor.moveToFirst()) {
            int nameColumnIndex = cursor.getColumnIndex(DatabaseRestaurant.COLUMN_RESTAURANT_NAME);
            int locationColumnIndex = cursor.getColumnIndex(DatabaseRestaurant.COLUMN_RESTAURANT_LOCATION);
            int phoneNumberColumnIndex = cursor.getColumnIndex(DatabaseRestaurant.COLUMN_RESTAURANT_PHONENUMBER);
            int ratingColumnIndex = cursor.getColumnIndex(DatabaseRestaurant.COLUMN_RESTAURANT_RATING);

            do {
                String name = cursor.getString(nameColumnIndex);
                String location = cursor.getString(locationColumnIndex);
                String phoneNumber = cursor.getString(phoneNumberColumnIndex);
                String rating = cursor.getString(ratingColumnIndex);

                // Check if the restaurant already exists in the list
                boolean isDuplicate = false;
                for (RestaurantModel restaurant : restaurantList) {
                    if (restaurant.getName().equals(name)) {
                        isDuplicate = true;
                        break;
                    }
                }

                // Only add the restaurant to the list if it's not a duplicate
                if (!isDuplicate) {
                    RestaurantModel restaurant = new RestaurantModel(-1, name, location, phoneNumber, rating);
                    restaurantList.add(restaurant);
                }
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();

        return restaurantList;
    }

    private void setupNavigationDrawer() {
        NavigationUI.setupActionBarWithNavController(this, navController, drawer);
        NavigationUI.setupWithNavController(this.navigationView, navController); // Use this.navigationView
        this.navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(MenuItem item) {
                handleMenuItemSelection(item);
                return true;
            }
        });
    }

    private void setupDestinationChangeListener() {
        navController.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(NavController controller, androidx.navigation.NavDestination destination, Bundle arguments) {
                // Hide the content layout when not on the selected destinations
                int destinationId = destination.getId();
                if (destinationId != R.id.nav_favorite && destinationId != R.id.nav_bookmark && destinationId != R.id.nav_settings) {
                    // Hide the content layout here
                } else {
                    // Show the content layout here
                }
            }
        });
    }

    private void handleMenuItemSelection(MenuItem item) {
        int itemId = item.getItemId();
        Log.d("DEBUG", "Menu item selected: " + item.getTitle());
        if (itemId == R.id.nav_favorite) {
            if (navController.getCurrentDestination().getId() == R.id.nav_favorite) {
                drawer.closeDrawer(GravityCompat.START);
                return;
            }
            navController.navigate(R.id.nav_favorite);
        } else if (itemId == R.id.nav_bookmark) {
            navController.navigate(R.id.nav_bookmark);
        } else if (itemId == R.id.nav_settings) {
            navController.navigate(R.id.nav_settings);
        } else if (itemId == R.id.nav_add_to_menu) {
            navController.navigate(R.id.nav_add_to_menu);
        }

        drawer.closeDrawer(GravityCompat.START);
    }

    @Override
    public boolean onSupportNavigateUp() {
        return NavigationUI.navigateUp(navController, drawer);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
