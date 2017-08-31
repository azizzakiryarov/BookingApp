package se.groupfish.azizzakiryarov.newapp;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import dbhelper.SQLiteDatabaseHelperAndRepository;

import static dbhelper.DBContract.UsersEntry.COLUMN_NAME_DATE;
import static dbhelper.DBContract.UsersEntry.COLUMN_NAME_EMAIL;
import static dbhelper.DBContract.UsersEntry.COLUMN_NAME_FIRSTNAME_AND_LASTNAME;
import static dbhelper.DBContract.UsersEntry.COLUMN_NAME_TEL;
import static dbhelper.DBContract.UsersEntry.COLUMN_NAME_TIME;
import static dbhelper.DBContract.UsersEntry.TABLE_NAME;
import static dbhelper.SQLiteDatabaseHelperAndRepository.DATABASE_NAME;

public final class BookingAppActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "myTags";
    Button saveAndSend, list, delete;
    EditText firstNameAndLastName, email, tel;
    TextView tvDate;
    TextView tvTime;

    SQLiteDatabaseHelperAndRepository SQLiteDatabaseHelperAndRepository;

    int DIALOG_DATE = 1;
    int myYear = 2017;
    int myMonth = 05;
    int myDay = 07;

    int DIALOG_TIME = 2;
    int myHour = 12;
    int myMinute = 00;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_booking_app);

        SQLiteDatabaseHelperAndRepository = new SQLiteDatabaseHelperAndRepository(this);

        firstNameAndLastName = (EditText) findViewById(R.id.edit_firstName_and_lastName);
        email = (EditText) findViewById(R.id.edit_mail);
        tel = (EditText) findViewById(R.id.edit_tel);
        tvDate = (EditText) findViewById(R.id.tvDate);
        tvTime = (EditText) findViewById(R.id.tvTime);

        saveAndSend = (Button) findViewById(R.id.btn_saveAndSend);
        list = (Button) findViewById(R.id.btn_list);
        delete = (Button) findViewById(R.id.btn_delete);

        saveAndSend.setOnClickListener(this);
        list.setOnClickListener(this);
        delete.setOnClickListener(this);
        tvDate.setOnClickListener(this);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Bookning");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public void onClick(View v) {

        String nameAndLastName = firstNameAndLastName.getText().toString();
        String emailText = email.getText().toString();
        String telText = tel.getText().toString();
        String dateText = tvDate.getText().toString();
        String timeText = tvTime.getText().toString();

        SQLiteDatabase database = SQLiteDatabaseHelperAndRepository.getWritableDatabase();
        ContentValues contentValues = new ContentValues();


        switch (v.getId()) {

            case R.id.tvDate:
                showDialog(DIALOG_DATE);
                break;

            case R.id.tvTime:
                showDialog(DIALOG_TIME);
                break;

            case R.id.btn_saveAndSend:

                contentValues.put(COLUMN_NAME_FIRSTNAME_AND_LASTNAME, nameAndLastName);
                contentValues.put(COLUMN_NAME_EMAIL, emailText);
                contentValues.put(COLUMN_NAME_TEL, telText);
                contentValues.put(COLUMN_NAME_DATE, dateText);
                contentValues.put(COLUMN_NAME_TIME, timeText);

                database.insert(TABLE_NAME, null, contentValues);

                Log.d(TAG, "onClick: saved on a database  " + contentValues.toString());

                Toast toast = Toast.makeText(BookingAppActivity.this, "Saving in Database...", Toast.LENGTH_LONG);
                toast.show();

                break;
            case R.id.btn_list:

                Intent intent = new Intent(this, BookingsList.class);
                startActivity(intent);

                toast = Toast.makeText(BookingAppActivity.this, "Show a list from Database... ", Toast.LENGTH_LONG);
                toast.show();

                break;

            case R.id.btn_delete:

                database.delete(TABLE_NAME, null, null);

                Log.d(TAG, "Database: " + DATABASE_NAME + " -> deleted " + " rows: " + contentValues.size());

                toast = Toast.makeText(BookingAppActivity.this, "Database is deleted...", Toast.LENGTH_LONG);
                toast.show();
                break;
        }
        SQLiteDatabaseHelperAndRepository.close();
    }

    protected Dialog onCreateDialog(int id) {
        if (id == DIALOG_DATE) {
            DatePickerDialog tpdDate = new DatePickerDialog(this, myCallBackDate, myYear, myMonth, myDay);
            return tpdDate;
        } else if (id == DIALOG_TIME) {
            TimePickerDialog tpdTime = new TimePickerDialog(this, myCallBackTime, myHour, myMinute, true);
            return tpdTime;
        }
        return super.onCreateDialog(id);
    }

    DatePickerDialog.OnDateSetListener myCallBackDate = new DatePickerDialog.OnDateSetListener() {
        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            myYear = year;
            myMonth = month;
            myDay = dayOfMonth;
            tvDate.setText(myDay + "/" + myMonth + "/" + myYear);
        }
    };

    TimePickerDialog.OnTimeSetListener myCallBackTime = new TimePickerDialog.OnTimeSetListener() {
        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            myHour = hourOfDay;
            myMinute = minute;
            tvTime.setText(myHour + ":" + myMinute);
        }
    };

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}
