package io.github.mortenjenne.frigdechef;

import java.util.List;

public class RecipeResponse {
    private List<Recipe> results;

    public List<Recipe> getResults() {
        return results;
    }

    public void setResults(List<Recipe> results) {
        this.results = results;
    }
}
