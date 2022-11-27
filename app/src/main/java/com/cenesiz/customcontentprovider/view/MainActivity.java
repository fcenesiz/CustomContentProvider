package com.cenesiz.customcontentprovider.view;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.cenesiz.customcontentprovider.R;
import com.cenesiz.customcontentprovider.databinding.ActivityMainBinding;
import com.cenesiz.customcontentprovider.model.Food;
import com.cenesiz.customcontentprovider.viewmodel.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    DatabaseHelper databaseHelper;
    ListFoodViewModel listFoodViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase db = databaseHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery(
                "SELECT * FROM " + DatabaseHelper.DATABASE_NAME,
                null
        );

        List<Food> foods = new ArrayList<>();

        while (cursor.moveToNext()){
            foods.add(new Food(
                    cursor.getString(1),
                    cursor.getString(2),
                    cursor.getInt(3)
            ));
        }

        foods.forEach(item ->{
            System.out.println(item);
        });

        listFoodViewModel = new ListFoodViewModel(this, binding, foods);
    }

}