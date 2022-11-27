package com.cenesiz.customcontentprovider.model;

public class Food {
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
}
