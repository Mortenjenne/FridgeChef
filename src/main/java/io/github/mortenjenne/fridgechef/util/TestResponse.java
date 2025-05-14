package io.github.mortenjenne.fridgechef.util;

import io.github.mortenjenne.fridgechef.model.Recipe;

import java.util.List;

public class TestResponse {
    private List<Recipe> results;

    public List<Recipe> getResults() {
        return results;
    }

    public void setResults(List<Recipe> results) {
        this.results = results;
    }
}
