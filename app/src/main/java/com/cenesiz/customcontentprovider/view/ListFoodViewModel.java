package com.cenesiz.customcontentprovider.view;
import android.app.Activity;
import androidx.recyclerview.widget.LinearLayoutManager;
import com.cenesiz.customcontentprovider.databinding.ActivityMainBinding;
import com.cenesiz.customcontentprovider.model.Food;

import java.util.List;

public class ListFoodViewModel {

    private Activity activity;
    private RecyclerViewFoodsAdapter recyclerViewFoodsAdapter;

    public ListFoodViewModel(Activity activity, ActivityMainBinding binding, List<Food> foods){
        this.activity = activity;
        initRecyclerView(binding, foods);
    }

    private void initRecyclerView(ActivityMainBinding binding, List<Food> foods){
        recyclerViewFoodsAdapter = new RecyclerViewFoodsAdapter(foods);
        binding.includedListFood.recyclerViewFoods.setHasFixedSize(true);
        binding.includedListFood.recyclerViewFoods.setLayoutManager(new LinearLayoutManager(activity));
        binding.includedListFood.recyclerViewFoods.setAdapter(recyclerViewFoodsAdapter);
    }

}
