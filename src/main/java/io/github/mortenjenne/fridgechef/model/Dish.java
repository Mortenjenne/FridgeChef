package io.github.mortenjenne.fridgechef.model;

public class Dish {
    private int id;
    private String title;
    private String image;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setImage(String imageUrl){
        this.image = imageUrl;
    }

    public String getImageUrl(){
        return this.image;
    }

    public String toString(){return this.title;}
}
