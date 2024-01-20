package com.houng.mobile_app_development.modules.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.houng.mobile_app_development.R;

public class AddPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_page);

        //implement action bar.
        Toolbar toolbar = findViewById(R.id.materialToolbar);
        setSupportActionBar(toolbar);
        // Set a custom title.
        getSupportActionBar().setTitle("Insert New Books");
        // Set the title text color (example: set to red)
        int titleTextColor = ContextCompat.getColor(this, R.color.white); // Replace with your color resource

        // Create a SpannableString with the desired color
        SpannableString spannableString = new SpannableString(getSupportActionBar().getTitle());
        spannableString.setSpan(new ForegroundColorSpan(titleTextColor), 0, spannableString.length(), 0);

        // Set the SpannableString as the title
        getSupportActionBar().setTitle(spannableString);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                // Handle the navigation icon click
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}