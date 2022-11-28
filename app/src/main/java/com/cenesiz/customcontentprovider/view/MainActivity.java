package com.cenesiz.customcontentprovider.view;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.View;
import android.widget.Toast;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.cenesiz.customcontentprovider.R;
import com.cenesiz.customcontentprovider.databinding.ActivityMainBinding;
import com.cenesiz.customcontentprovider.model.Food;
import com.cenesiz.customcontentprovider.viewmodel.DatabaseHelper;
import com.cenesiz.customcontentprovider.viewmodel.EditType;

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

        listFoodViewModel = new ListFoodViewModel(this, binding, foods);

        binding.buttonAdd.setOnClickListener(view -> {
            Intent intent = new Intent(MainActivity.this, EditFoodActivity.class);
            intent.putExtra(EditType.EDIT_TYPE, EditType.ADD);
            launcher.launch(intent);
        });
    }

    private ActivityResultLauncher launcher =  registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                if (result.getResultCode() == RESULT_OK){
                    assert result.getData() != null;
                    listFoodViewModel.onStartForResult(result.getData());

                }
            });

    public ActivityResultLauncher getLauncher() {
        return launcher;
    }
}