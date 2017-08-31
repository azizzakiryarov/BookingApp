package se.groupfish.azizzakiryarov.newapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;

public class WorkTableAppActivity extends AppCompatActivity {

    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_work_table_app);

        imageView = (ImageView) findViewById(R.id.calendar);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(WorkTableAppActivity.this, BookingAppActivity.class);
                startActivity(intent);
            }
        });

        ActionBar actionBar = getSupportActionBar();

        if (actionBar != null) {
            actionBar.setTitle("My programms");
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_bar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }

        int id = item.getItemId();

        switch (id) {
            case R.id.about_us:
                Intent intent = new Intent(WorkTableAppActivity.this, AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.settings:
                Intent intent2 = new Intent(WorkTableAppActivity.this, SettingsActivity.class);
                startActivity(intent2);
                break;
            case R.id.contacts:
                Intent intent3 = new Intent(WorkTableAppActivity.this, ContactsActivity.class);
                startActivity(intent3);
                break;
        }

        return super.onOptionsItemSelected(item);
    }
}
