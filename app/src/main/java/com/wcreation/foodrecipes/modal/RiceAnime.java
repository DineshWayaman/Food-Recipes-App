package com.wcreation.foodrecipes.modal;

/**
 * Created by Dinesh Wayaman from W Creation on 7/10/2020.
 */

public class RiceAnime {

    private String name;
    private String Ingredients;
    private String Method;
    private String Rating;
    private String image_URI;


    public RiceAnime() {
    }

    public String getName() {
        return name;
    }

    public String getIngredients() {
        return Ingredients;
    }

    public String getMethod() {
        return Method;
    }

    public String getRating() {
        return Rating;
    }

    public String getImage_URI() {
        return image_URI;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setIngredients(String ingredients) {
        Ingredients = ingredients;
    }

    public void setMethod(String method) {
        Method = method;
    }

    public void setRating(String rating) {
        Rating = rating;
    }

    public void setImage_URI(String image_URI) {
        this.image_URI = image_URI;
    }
}
