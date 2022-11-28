package com.cenesiz.customcontentprovider.view;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.cenesiz.customcontentprovider.databinding.ActivityEditFoodBinding;
import com.cenesiz.customcontentprovider.model.Food;
import com.cenesiz.customcontentprovider.viewmodel.EditType;
import com.cenesiz.customcontentprovider.viewmodel.FoodProvider;

public class EditFoodActivity extends AppCompatActivity implements View.OnClickListener {

    //public static final int REQUEST_CODE_ADD = 0;
    //public static final int REQUEST_CODE_UPDATE = 1;

    private ActivityEditFoodBinding binding;
    private ContentResolver cr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditFoodBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        cr = getContentResolver();
        switch (getIntent().getIntExtra("EDIT_TYPE", EditType.ADD)) {
            case EditType.ADD:
                onAdd();
                break;
            case EditType.UPDATE:
                onUpdate();
                break;
        }
        binding.buttonConfirm.setOnClickListener(this);
    }

    private void onAdd() {
        binding.textViewEdit.setText("Add Food");

    }

    private void onUpdate() {
        binding.textViewEdit.setText("Update Food");
        binding.editTextName.setFocusable(false);
        Food food = getIntent().getParcelableExtra("food");
        binding.editTextName.setText(food.getName());
        binding.editTextAmount.setText(food.getAmount());
        binding.editTextCalorie.setText(String.valueOf(food.getCalorie()));
    }

    @Override
    public void onClick(View view) {
        ContentValues cv = new ContentValues();
        Intent intent = new Intent();

        Food food = new Food(
                binding.editTextName.getText().toString(),
                binding.editTextAmount.getText().toString(),
                Integer.parseInt(binding.editTextCalorie.getText().toString())
        );

        switch (getIntent().getIntExtra("EDIT_TYPE", EditType.ADD)) {
            case EditType.ADD:
                cv.put(FoodProvider.NAME, food.getName());
                cv.put(FoodProvider.AMOUNT, food.getAmount());
                cv.put(FoodProvider.CALORIE, food.getCalorie());
                cr.insert(FoodProvider.CONTENT_URI, cv);


                intent.putExtra(EditType.EDIT_TYPE, EditType.ADD);
                intent.putExtra("food", food);
                break;
            case EditType.UPDATE:
                cv.put(FoodProvider.AMOUNT, food.getAmount());
                cv.put(FoodProvider.CALORIE, food.getCalorie());
                cr.update(FoodProvider.CONTENT_URI, cv, "NAME = ?",
                        new String[]{binding.editTextName.getText().toString()});

                intent.putExtra(EditType.EDIT_TYPE, EditType.UPDATE);
                intent.putExtra("food", food);
                intent.putExtra("update_position", getIntent().getIntExtra("update_position", 0));
                break;
        }
        setResult(RESULT_OK,
                intent);
        finish();
    }

}