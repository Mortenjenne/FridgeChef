package io.github.mortenjenne.fridgechef.model;

public class Ingredient {
    private int id;
    private String name;
    private String image;
    private final String apiURL = "https://spoonacular.com/cdn/ingredients_250x250/";


    public void setId(int id) {
        this.id = id;
    }

    public void setName(String name){
        this.name = name;
    }

    public void setImage(String image){
        this.image = image;
    }

    public int getId() {
        return id; }

    public String getName() {
        return name; }

    public String getImage() {
        return image; }

    public String getApiURL(){
        return this.apiURL + this.image;
    }

    public String toString(){
        return this.name;
    }

}
