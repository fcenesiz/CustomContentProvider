package com.cenesiz.customcontentprovider.viewmodel;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import androidx.annotation.Nullable;
import com.cenesiz.customcontentprovider.model.Food;
import java.util.Arrays;
import java.util.List;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final int VERSION = 1;
    private static final String DATABASE_NAME = "CALORIES";
    private static final String ONE_PEACE = "1 Peace";
    private static final String HUNDRED_GRAM = "100 gr.";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + DATABASE_NAME + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NAME TEXT," +
                "AMOUNT TEXT," +
                "CALORIE INTEGER" +
                ")"
        );

        List<Food> foods = Arrays.asList(
                new Food("Orange", ONE_PEACE, 50),
                new Food("Grapefruit", ONE_PEACE, 60),
                new Food("Pear", ONE_PEACE, 70),
                new Food("Apple", ONE_PEACE, 60),
                new Food("Strayberry", HUNDRED_GRAM, 26),
                new Food("Fresh Figs", HUNDRED_GRAM, 41),
                new Food("Kiwi", ONE_PEACE, 34)
        );

        foods.forEach(food -> {
            db.execSQL("INSERT INTO CALORIES(NAME, AMOUNT, CALORIE) VALUES('" + food.getName() + "','" + food.getAmount() + "'," + food.getCalorie() + ")");
        });

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {

    }
}
