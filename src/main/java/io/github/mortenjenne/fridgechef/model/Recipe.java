package io.github.mortenjenne.fridgechef.model;

import java.util.List;

public class Recipe {
    private int id;
    private String title;
    private String image;
    private int readyInMinutes;
    private int servings;
    private boolean vegetarian;
    private boolean vegan;
    private String instructions;
    private List<AnalyzedInstruction> analyzedInstructions;
    private List<ExtendedIngredient> extendedIngredients;

    public void setId(int id){
        this.id = id;
    }

    public void setTitle(String title){
        this.title = title;
    }

    public void setImage(String image){
        this.image = image;
    }

    public void setReadyInMinutes(int readyInMinutes){
        this.readyInMinutes = readyInMinutes;
    }

    public void setVegetarian(boolean vegetarian){
        this.vegetarian = vegetarian;
    }

    public void setVegan(boolean vegan){
        this.vegan = vegan;
    }

    public void setServings(int servings){
        this.servings = servings;
    }

    public void setInstructions(){
        this.instructions = instructions;
    }

    public void setAnalyzedInstructions(List<AnalyzedInstruction> analyzedInstructions){
        this.analyzedInstructions = analyzedInstructions;
    }

    public void setExtendedIngredients(List<ExtendedIngredient> extendedIngredients){
        this.extendedIngredients = extendedIngredients;
    }

    public int getId(){
        return this.id;
    }

    public String getTitle(){
        return this.title;
    }

    public String getImage(){
        return this.image;
    }

    public int getReadyInMinutes(){
        return this.readyInMinutes;
    }

    public int getServings(){
        return this.servings;
    }

    public String getInstructions() {
        return this.instructions;
    }

    public boolean IsVegetarian(){
        return this.vegetarian;
    }

    public boolean isVegan(){
        return this.vegan;
    }

    public List<ExtendedIngredient> getExtendedIngredients(){
        return this.extendedIngredients;
    }

    public List<AnalyzedInstruction> getAnalyzedInstructions(){
        return  this.analyzedInstructions;
    }
}