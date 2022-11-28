package com.cenesiz.customcontentprovider.viewmodel;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class FoodProvider extends ContentProvider {

    public static final String PROVIDER_NAME = "com.cenesiz.customcontentprovider.viewmodel/FoodProvider";
    public static final String URL = "content://" + PROVIDER_NAME + "/" + DatabaseHelper.DATABASE_NAME;
    public static final Uri CONTENT_URI = Uri.parse(URL);

    public static final String _ID = "_id";
    public static final String NAME = "NAME";
    public static final String AMOUNT = "AMOUNT";
    public static final String CALORIE = "CALORIE";

    public SQLiteDatabase db;

    @Override
    public boolean onCreate() {
        DatabaseHelper helper = new DatabaseHelper(getContext());
        db = helper.getWritableDatabase();
        return db != null;
    }

    @Nullable
    @Override
    public Uri insert(@NonNull Uri uri, @Nullable ContentValues contentValues) {
        db.insert(DatabaseHelper.DATABASE_NAME, null, contentValues);
        notifyChanges(uri);
        return uri;
    }

    @Override
    public int update(@NonNull Uri uri, @Nullable ContentValues contentValues, @Nullable String condition, @Nullable String[] condition_values) {
        int count = db.update(DatabaseHelper.DATABASE_NAME, contentValues, condition,condition_values);
        notifyChanges(uri);
        return count;
    }

    @Override
    public int delete(@NonNull Uri uri, @Nullable String condition, @Nullable String[] condition_values) {
        int count = db.delete(DatabaseHelper.DATABASE_NAME, condition, condition_values);
        notifyChanges(uri);

        return count;
    }

    @Nullable
    @Override
    public Cursor query(@NonNull Uri uri, @Nullable String[] cols, @Nullable String condition, @Nullable String[] condition_values, @Nullable String order) {
        return db.query(
                DatabaseHelper.DATABASE_NAME,
                cols,
                condition,
                condition_values,
                null,
                null,
                order
        );
    }

    @Nullable
    @Override
    public String getType(@NonNull Uri uri) {
        return "vnd.android.cursor.dir/vnd.example.foods";
    }

    private void notifyChanges(Uri uri){
        getContext().getContentResolver().notifyChange(uri, null);
    }


}
