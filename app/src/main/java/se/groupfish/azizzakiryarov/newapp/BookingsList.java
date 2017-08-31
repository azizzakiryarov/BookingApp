package se.groupfish.azizzakiryarov.newapp;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

import dbhelper.SQLiteDatabaseHelperAndRepository;
import model.Customer;

public final class BookingsList extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bookings_list);

        ListView listView = (ListView) findViewById(R.id.bookings_list);

        SQLiteDatabaseHelperAndRepository SQLiteDatabaseHelperAndRepository = new SQLiteDatabaseHelperAndRepository(this);

        List<Customer> contacts = SQLiteDatabaseHelperAndRepository.getAllCutomers();

        ArrayAdapter<Customer> adapter = new ArrayAdapter<>(BookingsList.this, android.R.layout.simple_list_item_1, contacts);

        listView.setAdapter(adapter);

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("Bookning's list");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
        }
        return true;
    }
}
