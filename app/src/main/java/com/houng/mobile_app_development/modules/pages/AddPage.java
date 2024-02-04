package com.houng.mobile_app_development.modules.pages;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.TableRow;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.houng.mobile_app_development.R;
import com.houng.mobile_app_development.model.Book_model;

import java.util.Objects;

public class AddPage extends AppCompatActivity {
    public TextInputEditText choose_type_book, title, subtitle, rate, des, story;
    public Button save_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_page);
        save_button = findViewById(R.id.save_book_button);
        title = findViewById(R.id.input_title);
        subtitle = findViewById(R.id.input_subtitle);
        rate = findViewById(R.id.input_rate);
        des = findViewById(R.id.input_des);
        story = findViewById(R.id.input_story);
        Toolbar toolbar = findViewById(R.id.materialToolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Insert New Books");

        int titleTextColor = ContextCompat.getColor(this, R.color.white);
        SpannableString spannableString = new SpannableString(getSupportActionBar().getTitle());
        spannableString.setSpan(new ForegroundColorSpan(titleTextColor), 0, spannableString.length(), 0);
        getSupportActionBar().setTitle(spannableString);


        choose_type_book = findViewById(R.id.choose_type_book);

        choose_type_book.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDialog();
            }
        });
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String editTitle = title.getText().toString();
                String editSubtitle = subtitle.getText().toString();
                String editRate = rate.getText().toString();
                String editDes = des.getText().toString();
                String editStory = story.getText().toString();
                String editCategory = choose_type_book.getText().toString();
                DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference("book");
                String bookId = databaseReference.push().getKey();
                Book_model book = new Book_model(editTitle,editCategory, editSubtitle,"1.png",editRate,editDes,editStory);
                databaseReference.child(bookId).setValue(book);

            }
        });


    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void showDialog() {
        final CharSequence[] items = {"សៀវភៅប្រលោមលោក", "សៀវភៅទូទៅ", "សៀវភៅអក្សរសិល្ប៌", "គម្ពីរធម៌"};
        LayoutInflater inflater = LayoutInflater.from(this);
        View dialogView = inflater.inflate(R.layout.dialog_single_choice, null);
        CheckBox checkBox = dialogView.findViewById(R.id.checkbox_none);
        final int[] selectedItemIndex = {-1};

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("ប្រភេទសៀវភៅ");
        builder.setSingleChoiceItems(items, -1, new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int item) {
                selectedItemIndex[0] = item;
                checkBox.setChecked(false);
            }
        });
        builder.setView(dialogView);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    ListView listView = ((AlertDialog) dialogView.getParent()).getListView();
                    listView.setItemChecked(selectedItemIndex[0], false);
                    selectedItemIndex[0] = -1;
                }
            }
        });
        builder.setPositiveButton("យល់ព្រម", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                if (selectedItemIndex[0] != -1) {
                } else {
                    checkBox.isChecked();
                }
            }
        });
        builder.setNegativeButton("បោះបង់", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {}
        });
        AlertDialog dialog = builder.create();
        dialog.show();
    }
}