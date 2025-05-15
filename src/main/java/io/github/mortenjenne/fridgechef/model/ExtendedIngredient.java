package io.github.mortenjenne.fridgechef.model;

public class ExtendedIngredient {
    private int id;
    private String name;
    private double amount;
    private String unit;

    public void setId(int id){
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setAmount(double amount){
        this.amount = amount;
    }

    public void setUnit(String unit){
        this.unit = unit;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public double getAmount() { return amount; }
    public String getUnit() { return unit; }

    @Override
    public String toString() {
        return amount + " " + unit + " " + name;
    }
}
