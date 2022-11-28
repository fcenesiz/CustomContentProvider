package com.cenesiz.customcontentprovider.view;
import android.content.Intent;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.cenesiz.customcontentprovider.R;
import com.cenesiz.customcontentprovider.databinding.ActivityMainBinding;
import com.cenesiz.customcontentprovider.model.Food;
import com.cenesiz.customcontentprovider.viewmodel.EditType;

import java.util.List;

public class ListFoodViewModel {

    private List<Food> foods;
    private MainActivity activity;
    private RecyclerViewFoodsAdapter recyclerViewFoodsAdapter;


    public ListFoodViewModel(MainActivity activity, ActivityMainBinding binding, List<Food> foods){
        this.activity = activity;
        this.foods = foods;
        initRecyclerView(binding, foods);

    }

    private void initRecyclerView(ActivityMainBinding binding, List<Food> foods){
        recyclerViewFoodsAdapter = new RecyclerViewFoodsAdapter(this, foods);
        binding.includedListFood.recyclerViewFoods.setHasFixedSize(true);
        binding.includedListFood.recyclerViewFoods.setLayoutManager(new LinearLayoutManager(activity));
        binding.includedListFood.recyclerViewFoods.setAdapter(recyclerViewFoodsAdapter);
    }

    public void onAddNewFood(Food food){
        foods.add(food);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerViewFoodsAdapter.notifyItemInserted(foods.size() + 1);
            }
        });
    }

    public void onUpdateFood(Food food, int position){
        foods.set(position, food);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerViewFoodsAdapter.notifyItemChanged(position);
            }
        });

    }

    public void onDeleteFood(int position){
        foods.remove(position);
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                recyclerViewFoodsAdapter.notifyItemRemoved(position);
            }
        });

    }

    public void onStartForResult(Intent intent){
        int editType = intent.getIntExtra(EditType.EDIT_TYPE, 0);
        Food food = intent.getParcelableExtra("food");
        switch (editType){
            case EditType.ADD:
                onAddNewFood(food);
                break;
            case EditType.UPDATE:
                int updatePosition = intent.getIntExtra("update_position", 0);
                onUpdateFood(food, updatePosition);
                break;
        }
    }

    public MainActivity getActivity() {
        return activity;
    }


}
