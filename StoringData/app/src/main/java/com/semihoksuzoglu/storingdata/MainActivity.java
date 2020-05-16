package com.semihoksuzoglu.storingdata;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText Ed;
    TextView Tv;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Ed = findViewById(R.id.editText);
        Tv = findViewById(R.id.textView);


        sharedPreferences = this.getSharedPreferences("com.semihoksuzoglu.storingdata", Context.MODE_PRIVATE);

        int storeAge = sharedPreferences.getInt("storeAge", 0);
        if (storeAge == 0) {
            Tv.setText("Your Age:");
        } else {
            Tv.setText("Your Age:" + storeAge);
            Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_LONG);
        }
    }


    public void save(View view) {
        if (!Ed.getText().toString().matches("")) {
            AlertDialog.Builder alert = new AlertDialog.Builder(this);
            alert.setTitle("Save");
            alert.setMessage("Are you sure..?");
            alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            int age = Integer.parseInt(Ed.getText().toString());
                            Tv.setText("Your age:" + age);
                            sharedPreferences.edit().putInt("storeAge", age).apply();
                            Toast.makeText(MainActivity.this, "Saved", Toast.LENGTH_LONG).show();
                        }
                    }
            );
            alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Toast.makeText(MainActivity.this, "Not Saved", Toast.LENGTH_LONG).show();
                }
            });
            alert.show();
        }
    }

    public void delete(View view) {

        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Delete");
        alert.setMessage("Are you sure..?");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int StoredData = sharedPreferences.getInt("storeAge", 0);

                if (StoredData != 0) {
                    sharedPreferences.edit().remove("storeAge").apply();
                    Tv.setText("Your Age:");
                }
                Toast.makeText(MainActivity.this, "Delete", Toast.LENGTH_LONG).show();
            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Toast.makeText(MainActivity.this, "Not Delete", Toast.LENGTH_LONG).show();
            }
        });
        alert.show();
    }


}
