package io.github.mortenjenne.fridgechef.util;

import io.github.mortenjenne.fridgechef.model.Dish;
import java.util.List;

public class RecipeResponse {
    private List<Dish> results;

    public List<Dish> getResults() {
        return results;
    }

    public void setResults(List<Dish> results) {
        this.results = results;
    }
}
