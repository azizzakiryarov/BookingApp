package dbhelper;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import model.Customer;
import repository.Repository;

import static android.provider.BaseColumns._ID;
import static dbhelper.DBContract.UsersEntry.COLUMN_NAME_DATE;
import static dbhelper.DBContract.UsersEntry.COLUMN_NAME_EMAIL;
import static dbhelper.DBContract.UsersEntry.COLUMN_NAME_FIRSTNAME_AND_LASTNAME;
import static dbhelper.DBContract.UsersEntry.COLUMN_NAME_TEL;
import static dbhelper.DBContract.UsersEntry.COLUMN_NAME_TIME;
import static dbhelper.DBContract.UsersEntry.TABLE_NAME;

public class SQLiteDatabaseHelperAndRepository extends SQLiteOpenHelper implements Repository {

    private static final int DATABASE_VERSION = 5;
    private static final String TAG = SQLiteDatabaseHelperAndRepository.class.getSimpleName();
    public static final String DATABASE_NAME = "BookingsContacts";
    private static final String GET_ALL_CONTACTS = "SELECT * FROM " + TABLE_NAME;


    public SQLiteDatabaseHelperAndRepository(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME + " (" + _ID + " INTEGER PRIMARY KEY,"
                + COLUMN_NAME_FIRSTNAME_AND_LASTNAME + " TEXT," + COLUMN_NAME_EMAIL + " TEXT," +
                COLUMN_NAME_TEL + " TEXT," + COLUMN_NAME_DATE + " TEXT," + COLUMN_NAME_TIME + " TEXT" + ");";

        Log.d(TAG, "onCreate: " + CREATE_TABLE);

        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    @Override
    public Customer addCustomer(Customer customer) {
        return null;
    }

    @Override
    public void getGustomerById(int id) {

    }

    @Override
    public List<Customer> getAllCutomers() {

        List<Customer> allCustomers = new ArrayList<>();
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(GET_ALL_CONTACTS, null);


        if (cursor.getCount() > 0 && cursor.moveToFirst()) {
            int intIndex = cursor.getColumnIndex(_ID);
            int nameIndex = cursor.getColumnIndex(COLUMN_NAME_FIRSTNAME_AND_LASTNAME);
            int mailIndex = cursor.getColumnIndex(COLUMN_NAME_EMAIL);
            int telIndex = cursor.getColumnIndex(COLUMN_NAME_TEL);
            int dateIndex = cursor.getColumnIndex(COLUMN_NAME_DATE);
            int timeIndex = cursor.getColumnIndex(COLUMN_NAME_TIME);

            do {
                int id = cursor.getInt(intIndex);
                String name = cursor.getString(nameIndex);
                String mail = cursor.getString(mailIndex);
                String tel = cursor.getString(telIndex);
                String date = cursor.getString(dateIndex);
                String time = cursor.getString(timeIndex);

                Customer customer = new Customer();
                customer.setId(id);
                customer.setName(name);
                customer.setMail(mail);
                customer.setTel(tel);
                customer.setDate(date);
                customer.setTime(time);

                allCustomers.add(customer);

            } while (cursor.moveToNext());
        }
        if (cursor != null) {
            cursor.close();
        }
        return allCustomers;
    }

    @Override
    public void removeCustomer(int id) {

    }
}
