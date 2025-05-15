package io.github.mortenjenne.fridgechef.model;

import java.util.List;

public class Recipe {
    private int id;
    private String title;
    private String image;
    private int readyInMinutes;
    private int servings;
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

    public List<ExtendedIngredient> getExtendedIngredients(){
        return this.extendedIngredients;
    }

    public List<AnalyzedInstruction> getAnalyzedInstructions(){
        return  this.analyzedInstructions;
    }

    @Override
    public String toString() {
        return title + " (" + readyInMinutes + " min, " + servings + " servings)";
    }


}
