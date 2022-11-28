package com.cenesiz.customcontentprovider.view;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import android.widget.PopupMenu;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cenesiz.customcontentprovider.R;
import com.cenesiz.customcontentprovider.databinding.ItemFoodBinding;
import com.cenesiz.customcontentprovider.model.Food;
import com.cenesiz.customcontentprovider.viewmodel.EditType;
import com.cenesiz.customcontentprovider.viewmodel.FoodProvider;

import java.util.List;

public class RecyclerViewFoodsAdapter extends RecyclerView.Adapter<RecyclerViewFoodsAdapter.ViewHolderFood> {

    private List<Food> foods;
    private PopupMenu popupMenu;
    private ListFoodViewModel listFoodViewModel;

    public RecyclerViewFoodsAdapter(ListFoodViewModel listFoodViewModel, List<Food> foods) {
        this.foods = foods;
        this.listFoodViewModel = listFoodViewModel;
    }

    @NonNull
    @Override
    public ViewHolderFood onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
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
        Context context = holder.itemView.getContext();
        holder.bind(foods.get(position));
        holder.itemView.setOnClickListener(view -> onClickItem(context, holder.itemView, holder.getAdapterPosition()));
        holder.itemView.setOnLongClickListener(view -> {
            onLongClickItem(context, holder.itemView, holder.getAdapterPosition());
            return false;
        });
    }

    @Override
    public int getItemCount() {
        return foods.size();
    }

    private void onClickItem(Context context, View itemView, int position) {
        showPopup(context, itemView, position);
    }

    private void onLongClickItem(Context context, View itemView, int position) {
        showPopup(context, itemView, position);
    }

    private void showPopup(Context context, View view, int position) {
            popupMenu = new PopupMenu(context, view);
            popupMenu.inflate(R.menu.item_popup);

            ContentResolver cr = context.getContentResolver();
            Cursor cursor = cr.query(
                            FoodProvider.CONTENT_URI,
                            new String[]{FoodProvider._ID, FoodProvider.NAME, FoodProvider.AMOUNT, FoodProvider.CALORIE},
                            null,
                            null,
                            null // FoodProvider.ID or FoodProvider.NAME
                    );

            popupMenu.setOnMenuItemClickListener(menuItem -> {
                switch (menuItem.getItemId()){
                    case R.id.update:
                        Intent intent = new Intent(listFoodViewModel.getActivity(), EditFoodActivity.class);
                        intent.putExtra(EditType.EDIT_TYPE, EditType.UPDATE);
                        intent.putExtra("food", foods.get(position));
                        intent.putExtra("update_position", position);
                        listFoodViewModel.getActivity().getLauncher().launch(intent);
                        break;
                    case R.id.delete:
                        String name = foods.get(position).getName();
                        listFoodViewModel.onDeleteFood(position);
                        cr.delete(FoodProvider.CONTENT_URI, "NAME = ?", new String[]{name});
                        break;
                }
                return false;
            });

        popupMenu.show();
    }

    static class ViewHolderFood extends RecyclerView.ViewHolder {

        ItemFoodBinding binding;

        public ViewHolderFood(ItemFoodBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(Food food) {
            binding.textViewName.setText(food.getName());
            binding.textViewAmount.setText(food.getAmount());
            binding.textViewCalorie.setText(String.valueOf(food.getCalorie()));
        }
    }
}
