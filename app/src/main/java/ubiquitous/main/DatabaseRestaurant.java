package ubiquitous.main;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

public class DatabaseRestaurant extends SQLiteOpenHelper {

    public static final String RESTAURANT_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_RESTAURANT_NAME = "RESTAURANT_NAME";
    public static final String COLUMN_RESTAURANT_LOCATION = "RESTAURANT_LOCATION";
    public static final String COLUMN_RESTAURANT_PHONENUMBER = "RESTAURANT_PHONENUMBER";
    public static final String COLUMN_RESTAURANT_RATING = "RESTAURANT_RATING";


    public DatabaseRestaurant(@Nullable Context context) {
        super(context, "restaurant.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + RESTAURANT_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_RESTAURANT_NAME
                + " TEXT, " + COLUMN_RESTAURANT_LOCATION + " TEXT, " + COLUMN_RESTAURANT_PHONENUMBER + " TEXT, " + COLUMN_RESTAURANT_RATING + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(RestaurantModel restaurantModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_RESTAURANT_NAME, restaurantModel.getName());
        cv.put(COLUMN_RESTAURANT_LOCATION, restaurantModel.getLocation());
        cv.put(COLUMN_RESTAURANT_PHONENUMBER, restaurantModel.getPhoneNumber());
        cv.put(COLUMN_RESTAURANT_RATING, restaurantModel.getRating());


        long insert = db.insert(RESTAURANT_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

}
