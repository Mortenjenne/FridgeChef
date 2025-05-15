package io.github.mortenjenne.fridgechef.logic;

public enum View {
    LOGIN("/io/github/mortenjenne/fridgechef/LoginView.fxml"),
    MAIN("/io/github/mortenjenne/fridgechef/MainMenuView.fxml"),
    CREATE("/io/github/mortenjenne/fridgechef/CreateAccountView.fxml"),
    SEARCH("/io/github/mortenjenne/fridgechef/SearchView.fxml"),
    RESULT("/io/github/mortenjenne/fridgechef/ResultView.fxml"),
    RECIPE("/io/github/mortenjenne/fridgechef/RecipeView.fxml"),
    TEST("/io/github/mortenjenne/fridgechef/hello-view.fxml"),
    FRIDGE("/io/github/mortenjenne/fridgechef/FridgeView.fxml"),
    FAVORITES("/io/github/mortenjenne/fridgechef/FavoritesView.fxml");


    private String fileName;

    View(String fileName){
        this.fileName = fileName;
    }

    public String getFileName(){
        return this.fileName;
    }

}
