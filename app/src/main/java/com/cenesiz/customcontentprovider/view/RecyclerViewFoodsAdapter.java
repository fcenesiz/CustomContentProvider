package com.cenesiz.customcontentprovider.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cenesiz.customcontentprovider.databinding.ItemFoodBinding;
import com.cenesiz.customcontentprovider.model.Food;

import java.util.List;

public class RecyclerViewFoodsAdapter extends RecyclerView.Adapter<RecyclerViewFoodsAdapter.ViewHolderFood>{

    private List<Food> foods;

    public RecyclerViewFoodsAdapter(List<Food> foods) {
        this.foods = foods;
    }

    @NonNull
    @Override
    public ViewHolderFood onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        System.out.println("created");
        return new ViewHolderFood(
                ItemFoodBinding.inflate(
                        LayoutInflater.from(parent.getContext()),
                        parent,
                        false
                )
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolderFood holder, int position) {
        holder.bind(foods.get(position));
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    static class ViewHolderFood extends RecyclerView.ViewHolder{

        ItemFoodBinding binding;

        public ViewHolderFood(ItemFoodBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Food food){
            binding.textViewName.setText(food.getName());
            binding.textViewAmount.setText(food.getAmount());
            binding.textViewCalorie.setText(String.valueOf(food.getCalorie()));
        }
    }
}
