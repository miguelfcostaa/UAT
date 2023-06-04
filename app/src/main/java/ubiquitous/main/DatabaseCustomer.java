package ubiquitous.main;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class DatabaseCustomer extends SQLiteOpenHelper {


    public static final String CUSTOMER_TABLE = "CUSTOMER_TABLE";
    public static final String COLUMN_CUSTOMER_USERNAME = "CUSTOMER_USERNAME";
    public static final String COLUMN_CUSTOMER_EMAIL = "CUSTOMER_EMAIL";
    public static final String COLUMN_CUSTOMER_PASSWORD = "CUSTOMER_PASSWORD";
    public static final String COLUMN_CUSTOMER_CONFIRM_PASSWORD = "CUSTOMER_CONFIRM_PASSWORD";


    public DatabaseCustomer(@Nullable Context context) {
        super(context, "customer.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTableStatement = "CREATE TABLE " + CUSTOMER_TABLE + " (ID INTEGER PRIMARY KEY AUTOINCREMENT, " + COLUMN_CUSTOMER_USERNAME
                + " TEXT, " + COLUMN_CUSTOMER_EMAIL + " TEXT, " + COLUMN_CUSTOMER_PASSWORD + " TEXT, " + COLUMN_CUSTOMER_CONFIRM_PASSWORD + " TEXT)";
        db.execSQL(createTableStatement);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public boolean addOne(CustomerModel customerModel) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_CUSTOMER_USERNAME, customerModel.getUsername());
        cv.put(COLUMN_CUSTOMER_EMAIL, customerModel.getEmail());
        cv.put(COLUMN_CUSTOMER_PASSWORD, customerModel.getPassword());
        cv.put(COLUMN_CUSTOMER_CONFIRM_PASSWORD, customerModel.getConfirmPassword());


        long insert = db.insert(CUSTOMER_TABLE, null, cv);
        if (insert == -1) {
            return false;
        } else {
            return true;
        }
    }

    public CustomerModel getLoggedInCustomer() {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                CUSTOMER_TABLE,
                new String[]{COLUMN_CUSTOMER_USERNAME, COLUMN_CUSTOMER_EMAIL},
                null,
                null,
                null,
                null,
                null
        );

        CustomerModel loggedInCustomer = null;

        if (cursor.moveToFirst()) {
            int usernameColumnIndex = cursor.getColumnIndex(COLUMN_CUSTOMER_USERNAME);
            int emailColumnIndex = cursor.getColumnIndex(COLUMN_CUSTOMER_EMAIL);

            if (usernameColumnIndex != -1 && emailColumnIndex != -1) {
                String username = cursor.getString(usernameColumnIndex);
                String email = cursor.getString(emailColumnIndex);

                loggedInCustomer = new CustomerModel(-1, username, email, "", "");
            }
        }

        cursor.close();
        db.close();

        return loggedInCustomer;
    }



    public List<CustomerModel> getEveryone() {
        List<CustomerModel> everyoneList = new ArrayList<>();

        String query = "SELECT * FROM " + CUSTOMER_TABLE;

        SQLiteDatabase db = this.getWritableDatabase();

        Cursor cursor = db.rawQuery(query, null);

        if (cursor.moveToFirst()){
            do {
                int customerID = cursor.getInt(0);
                String customerName = cursor.getString(1);
                String customerEmail = cursor.getString(2);
                String customerPassword = cursor.getString(3);
                String customerConfirmPassword = cursor.getString(4);

                CustomerModel newCustomer = new CustomerModel(customerID, customerName, customerEmail, customerPassword, customerConfirmPassword);
                everyoneList.add(newCustomer);
            } while (cursor.moveToNext());
        }
        else {

        }

        cursor.close();
        db.close();
        return everyoneList;
    }
}
