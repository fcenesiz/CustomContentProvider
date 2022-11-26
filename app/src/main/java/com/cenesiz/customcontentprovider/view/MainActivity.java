package com.cenesiz.customcontentprovider.view;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.cenesiz.customcontentprovider.R;
import com.cenesiz.customcontentprovider.viewmodel.DatabaseHelper;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM CALORIES",
                null
        );
        if (cursor.moveToFirst()){
            Toast.makeText(this, cursor.getString(1) + "\n" + cursor.getString(2), Toast.LENGTH_SHORT).show();
        }
    }

}