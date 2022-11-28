package com.cenesiz.customcontentprovider.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Food implements Parcelable {
    private int id;
    private String name;
    private String amount;
    private int calorie;

    public Food(int id, String name, String amount, int calorie) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.calorie = calorie;
    }

    public Food(String name, String amount, int calorie) {
        this.name = name;
        this.amount = amount;
        this.calorie = calorie;
    }

    protected Food(Parcel in) {
        id = in.readInt();
        name = in.readString();
        amount = in.readString();
        calorie = in.readInt();
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public int getCalorie() {
        return calorie;
    }

    public void setCalorie(int calorie) {
        this.calorie = calorie;
    }

    @Override
    public String toString() {
        return "Food{" +
                "name='" + name + '\'' +
                ", amount='" + amount + '\'' +
                ", calorie=" + calorie +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeString(amount);
        parcel.writeInt(calorie);
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

}
